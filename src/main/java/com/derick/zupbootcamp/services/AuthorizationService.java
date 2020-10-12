package com.derick.zupbootcamp.services;

import com.google.api.client.auth.oauth2.Credential;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface AuthorizationService {

	public boolean isUserAuthenticated() throws Exception;

	public Credential getCredentials() throws IOException;

	public String authenticateUserViaGoogle() throws Exception;

	public void exchangeCodeForTokens(String code) throws Exception;
	
	public void removeUserSession(HttpServletRequest request) throws Exception;
}
