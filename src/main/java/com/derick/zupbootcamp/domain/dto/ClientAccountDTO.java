package com.derick.zupbootcamp.domain.dto;

import com.derick.zupbootcamp.domain.entities.Client;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class ClientAccountDTO implements BaseDTO, Serializable {

    private Integer id;

    private Client client;

    private Integer agency;

    private Integer accountNumber;

    private Integer bankNumber;

    public ClientAccountDTO(){}

    public ClientAccountDTO(Integer id, Client client, Integer agency, Integer accountNumber, Integer bankNumber) {
        this.id = id;
        this.client = client;
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.bankNumber = bankNumber;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getAgency() {
        return agency;
    }

    public void setAgency(Integer agency) {
        this.agency = agency;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(Integer bankNumber) {
        this.bankNumber = bankNumber;
    }
}
