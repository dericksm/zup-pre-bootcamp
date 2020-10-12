package com.derick.zupbootcamp.services.impl;

import com.derick.zupbootcamp.configs.DriveConfig;
import com.derick.zupbootcamp.domain.dto.ClientCPFDTO;
import com.derick.zupbootcamp.domain.entities.Client;
import com.derick.zupbootcamp.domain.entities.ClientCPF;
import com.derick.zupbootcamp.services.AuthorizationService;
import com.derick.zupbootcamp.services.ClientCPFService;
import com.derick.zupbootcamp.services.ClientService;
import com.derick.zupbootcamp.services.DriveService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Code Adaptation from https://github.com/munsif3/oauth-2.0-app
 */


@Service
public class DriveServiceImpl implements DriveService {


	@Autowired
	AuthorizationService authorizationService;

	@Autowired
	DriveConfig driveConfig;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientCPFService clientCPFService;

	private Drive driveService;

	@PostConstruct
	public void init() throws Exception {
		Credential credential = authorizationService.getCredentials();
		driveService = new Drive.Builder(driveConfig.HTTP_TRANSPORT, driveConfig.JSON_FACTORY, credential)
				.setApplicationName(driveConfig.APPLICATION_NAME).build();
	}

	@Override
	public void uploadFile(ClientCPFDTO clientCPFDTO) throws Exception {

		Client client = clientService.findById(clientCPFDTO.getId());
		ClientCPF clientCPF = new ClientCPF();
		clientCPF.setClient(client);

		MultipartFile multipartFile = clientCPFDTO.getFile();

		String path = driveConfig.getTemporaryFolder();
		String fileName = multipartFile.getOriginalFilename();

		java.io.File transferedFile = new java.io.File(path, fileName);
		multipartFile.transferTo(transferedFile);

		File fileMetadata = new File();
		fileMetadata.setName(multipartFile.getOriginalFilename());
		
		FileContent mediaContent = new FileContent(multipartFile.getContentType(), transferedFile);
		File file = driveService.files().create(fileMetadata, mediaContent).setFields("id").execute();

		clientCPF.setDriveFileId(file.getId());
		clientCPFService.insert(clientCPF);

		client.setClientCPF(clientCPF);
		clientService.update(client);

		//delete temp file
		transferedFile.delete();
	}

	@Override
	public ByteArrayOutputStream getFile(String fileId) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		driveService.files().get(fileId).executeMediaAndDownloadTo(outputStream);
		return outputStream;
	}
}
