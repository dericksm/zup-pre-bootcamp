package com.derick.zupbootcamp.configs;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.DriveScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.Collections;
import java.util.List;

@Configuration
public class DriveConfig {

    public static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    public static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    public static final String USER_IDENTIFIER_KEY = "Administrator";
    public static final String APPLICATION_NAME = "Drive File Upload";
    public static final String PARENT_FOLDER_NAME = "Drive File Upload";

    @Value("${google.oauth.callback.uri}")
    private String CALLBACK_URI;

    @Value("${google.secret.key.path}")
    private Resource driveSecretKeys;

    @Value("${google.credentials.folder.path}")
    private Resource credentialsFolder;

    @Value("${temp.path}")
    private String temporaryFolder;

    public static HttpTransport getHttpTransport() {
        return HTTP_TRANSPORT;
    }

    public static void setHttpTransport(HttpTransport httpTransport) {
        HTTP_TRANSPORT = httpTransport;
    }

    public static JsonFactory getJsonFactory() {
        return JSON_FACTORY;
    }

    public static void setJsonFactory(JsonFactory jsonFactory) {
        JSON_FACTORY = jsonFactory;
    }

    public static List<String> getSCOPES() {
        return SCOPES;
    }

    public static String getUserIdentifierKey() {
        return USER_IDENTIFIER_KEY;
    }

    public static String getApplicationName() {
        return APPLICATION_NAME;
    }

    public static String getParentFolderName() {
        return PARENT_FOLDER_NAME;
    }

    public String getCALLBACK_URI() {
        return CALLBACK_URI;
    }

    public void setCALLBACK_URI(String CALLBACK_URI) {
        this.CALLBACK_URI = CALLBACK_URI;
    }

    public Resource getDriveSecretKeys() {
        return driveSecretKeys;
    }

    public void setDriveSecretKeys(Resource driveSecretKeys) {
        this.driveSecretKeys = driveSecretKeys;
    }

    public Resource getCredentialsFolder() {
        return credentialsFolder;
    }

    public void setCredentialsFolder(Resource credentialsFolder) {
        this.credentialsFolder = credentialsFolder;
    }

    public String getTemporaryFolder() {
        return temporaryFolder;
    }

    public void setTemporaryFolder(String temporaryFolder) {
        this.temporaryFolder = temporaryFolder;
    }
}
