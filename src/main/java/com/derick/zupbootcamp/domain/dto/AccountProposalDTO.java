package com.derick.zupbootcamp.domain.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AccountProposalDTO implements BaseDTO, Serializable {

    private Integer id;

    @NotNull
    private Integer clientId;

    private Integer status;

    public AccountProposalDTO(){}

    public AccountProposalDTO(Integer id, Integer clientId, Integer status) {
        this.id = id;
        this.clientId = clientId;
        this.status = status;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
