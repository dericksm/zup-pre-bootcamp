package com.derick.zupbootcamp.domain.dto;

import com.derick.zupbootcamp.services.validation.PaymentValidation;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@PaymentValidation
public class PaymentDTO implements BaseDTO, Serializable {

    @NotNull
    private Integer id;

    @Min(value = 1)
    private Double value;

    @NotNull
    @PastOrPresent
    private ZonedDateTime paymentDate;

    @NotNull
    private String originCPF;

    @Pattern(regexp="[\\d]{3}", message = "Bank number must have 3 digits")
    private String originBank;

    @Pattern(regexp="[\\d]{8}", message = "Origin account must have 8 digits")
    private String originAccount;

    @Pattern(regexp="[\\d]{4}", message = "Origin agency must have 4 digits")
    private String originAgency;

    @Pattern(regexp="[\\d]{8}", message = "Destination account must have 8 digits")
    private String destinationAccount;

    @Pattern(regexp="[\\d]{4}", message = "Destination agency must have 4 digits")
    private String destinationAgency;

    public PaymentDTO(){}

    public PaymentDTO(Integer id, Double value, ZonedDateTime paymentDate, String originCPF, String originBank, String originAccount, String originAgency, String destinationAccount, String destinationAgency) {
        this.id = id;
        this.value = value;
        this.paymentDate = paymentDate;
        this.originCPF = originCPF;
        this.originBank = originBank;
        this.originAccount = originAccount;
        this.originAgency = originAgency;
        this.destinationAccount = destinationAccount;
        this.destinationAgency = destinationAgency;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ZonedDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(ZonedDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getOriginCPF() {
        return originCPF;
    }

    public void setOriginCPF(String originCPF) {
        this.originCPF = originCPF;
    }

    public String getOriginBank() {
        return originBank;
    }

    public void setOriginBank(String originBank) {
        this.originBank = originBank;
    }

    public String getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(String originAccount) {
        this.originAccount = originAccount;
    }

    public String getOriginAgency() {
        return originAgency;
    }

    public void setOriginAgency(String originAgency) {
        this.originAgency = originAgency;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public String getDestinationAgency() {
        return destinationAgency;
    }

    public void setDestinationAgency(String destinationAgency) {
        this.destinationAgency = destinationAgency;
    }
}
