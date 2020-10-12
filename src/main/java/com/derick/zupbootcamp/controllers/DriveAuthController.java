package com.derick.zupbootcamp.controllers;

import com.derick.zupbootcamp.domain.dto.ClientCPFDTO;
import com.derick.zupbootcamp.services.AuthorizationService;
import com.derick.zupbootcamp.services.DriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/drive-auth")
public class DriveAuthController {

    @Autowired
    private DriveService driveService;

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadFile(HttpServletRequest request, @Valid ClientCPFDTO clientCPFDTO) throws Exception {
        driveService.uploadFile(clientCPFDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) throws Exception {
        authorizationService.removeUserSession(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/auth-uri")
    public ResponseEntity<String> getAuthUri() throws Exception {
        String authUri = authorizationService.authenticateUserViaGoogle();
        return ResponseEntity.ok().body(authUri);
    }


    @GetMapping("/oauth/callback")
    public ResponseEntity<Void> saveAuthorizationCode(HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");
        if (code != null) {
            authorizationService.exchangeCodeForTokens(code);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
