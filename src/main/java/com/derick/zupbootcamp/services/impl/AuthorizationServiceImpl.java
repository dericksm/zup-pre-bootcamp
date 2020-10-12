package com.derick.zupbootcamp.services.impl;

import com.derick.zupbootcamp.configs.ApplicationConfig;
import com.derick.zupbootcamp.configs.DriveConfig;
import com.derick.zupbootcamp.services.AuthorizationService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.util.store.FileDataStoreFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Code Adaptation from https://github.com/munsif3/oauth-2.0-app
 */

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private DriveConfig driveConfig;

    private Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
    private GoogleAuthorizationCodeFlow flow;
    private FileDataStoreFactory dataStoreFactory;

    @Autowired
    private ApplicationConfig config;

    @PostConstruct
    public void init() throws Exception {
        InputStreamReader reader = new InputStreamReader(driveConfig.getDriveSecretKeys().getInputStream());
        dataStoreFactory = new FileDataStoreFactory(driveConfig.getCredentialsFolder().getFile());

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(driveConfig.JSON_FACTORY, reader);
        flow = new GoogleAuthorizationCodeFlow.Builder(driveConfig.HTTP_TRANSPORT, driveConfig.JSON_FACTORY, clientSecrets,
                driveConfig.SCOPES).setDataStoreFactory(dataStoreFactory).build();
    }

    @Override
    public boolean isUserAuthenticated() throws Exception {
        Credential credential = getCredentials();
        if (credential != null) {
            boolean isTokenValid = credential.refreshToken();
            logger.debug("isTokenValid, " + isTokenValid);
            return isTokenValid;
        }
        return false;
    }

    @Override
    public Credential getCredentials() throws IOException {
        return flow.loadCredential(driveConfig.USER_IDENTIFIER_KEY);
    }

    @Override
    public String authenticateUserViaGoogle() throws Exception {
        GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        String redirectUrl = url.setRedirectUri(driveConfig.getCALLBACK_URI()).setAccessType("offline").build();
        logger.debug("redirectUrl, " + redirectUrl);
        return redirectUrl;
    }

    @Override
    public void exchangeCodeForTokens(String code) throws Exception {
        GoogleTokenResponse tokenResponse = flow.newTokenRequest(code).setRedirectUri(driveConfig.getCALLBACK_URI()).execute();
        flow.createAndStoreCredential(tokenResponse, driveConfig.USER_IDENTIFIER_KEY);
    }

    @Override
    public void removeUserSession(HttpServletRequest request) throws Exception {
        dataStoreFactory.getDataStore(driveConfig.getCredentialsFolder().getFilename()).clear();
    }

}
