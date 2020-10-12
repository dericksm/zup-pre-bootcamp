package com.derick.zupbootcamp.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
public class ClientAccount extends BaseEntity implements Serializable {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountProposal proposal;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Max(value = 9999)
    @Min(value = 0000)
    private Integer agency;

    @Max(value = 99999999)
    private Integer accountNumber;

    @Max(value = 999)
    private Integer bankNumber;

    //balance shouldn't be serialized for everyone
    @JsonIgnore
    private Double balance = 0D;

    @JsonIgnore
    private String password;

    public ClientAccount(){}

    public ClientAccount(Integer id, Integer agency, Integer accountNumber, Integer bankNumber, Double balance, String password) {
        this.id = id;
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.bankNumber = bankNumber;
        this.balance = balance;
        this.password = password;
    }

    public ClientAccount(Integer id, Integer agency, Integer accountNumber, Integer bankNumber, Double balance, String password, AccountProposal accountProposal) {
        this.id = id;
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.bankNumber = bankNumber;
        this.balance = balance;
        this.proposal = accountProposal;
        this.password = password;
        this.client = accountProposal.getClient();
    }

    public AccountProposal getProposal() {
        return proposal;
    }

    public void setProposal(AccountProposal proposal) {
        this.proposal = proposal;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ClientAccount{");
        sb.append("agency=").append(agency);
        sb.append(", accountNumber=").append(accountNumber);
        sb.append(", bankNumber=").append(bankNumber);
        sb.append(", balance=").append(balance);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
