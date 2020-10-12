package com.derick.zupbootcamp.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ClientCPFDTO implements BaseDTO, Serializable {

    private Integer id;

    @NotNull(message = "File can't be empty")
    private MultipartFile file;

    public ClientCPFDTO() {
    }

    public ClientCPFDTO(Integer id, MultipartFile file) {
        this.id = id;
        this.file = file;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
