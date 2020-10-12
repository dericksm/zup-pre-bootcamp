package com.derick.zupbootcamp.domain.entities;

import com.derick.zupbootcamp.utils.TokenUtils;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
public class ConfirmationToken extends BaseEntity implements Serializable {

    @OneToOne(targetEntity = ClientAccount.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "account_id")
    private ClientAccount user;

    private String confirmationToken;

    private ZonedDateTime createdDate;

    private boolean used;

    public ConfirmationToken(){}

    public ConfirmationToken(ClientAccount clientAccount) {
        this.user = clientAccount;
        this.createdDate = ZonedDateTime.now();
        this.confirmationToken = TokenUtils.generateToken(6);
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ClientAccount getUser() {
        return user;
    }

    public void setUser(ClientAccount user) {
        this.user = user;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}