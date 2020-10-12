package com.derick.zupbootcamp.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ClientCPF extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_cpf_id", referencedColumnName = "id")
    @JsonBackReference
    private Client client;

    private String driveFileId;

    public ClientCPF() {
    }

    public ClientCPF(Integer id, Client client, String driveFileId) {
        this.id = id;
        this.client = client;
        this.driveFileId = driveFileId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDriveFileId() {
        return driveFileId;
    }

    public void setDriveFileId(String driveFileId) {
        this.driveFileId = driveFileId;
    }
}
