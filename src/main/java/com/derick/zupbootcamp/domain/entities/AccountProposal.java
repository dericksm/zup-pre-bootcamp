package com.derick.zupbootcamp.domain.entities;

import com.derick.zupbootcamp.domain.enums.ProposalStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class AccountProposal extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Client client;

    private Integer status = ProposalStatus.WAITING.getValue();

    public AccountProposal() {
    }

    public AccountProposal(Integer id, Client client, ProposalStatus status) {
        this.id = id;
        this.client = client;
        this.status = (status == null) ? null : status.getValue();
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ProposalStatus getStatus() {
        return ProposalStatus.toEnum(this.status);
    }

    public void setStatus(ProposalStatus status) {
        this.status = status.getValue();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AccountProposal{");
        sb.append("client=").append(client);
        sb.append(", status=").append(status);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
