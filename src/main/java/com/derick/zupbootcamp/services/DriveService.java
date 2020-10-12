package com.derick.zupbootcamp.services;

import com.derick.zupbootcamp.domain.dto.ClientCPFDTO;

import java.io.ByteArrayOutputStream;

public interface DriveService {

	public void uploadFile(ClientCPFDTO clientCPFDTO) throws Exception;

	public ByteArrayOutputStream getFile(String fileId) throws Exception;
}
