package com.derick.zupbootcamp.domain.entities;

import com.derick.zupbootcamp.domain.enums.PaymentStatus;
import org.springframework.security.core.parameters.P;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
public class Payment extends BaseEntity implements Serializable {

    private Double value;

    private ZonedDateTime paymentDate;

    private String originCPF;

    private Integer originBank;

    private Integer originAccount;

    private Integer originAgency;

    private Integer destinationAccount;

    private Integer destinationAgency;

    private Integer paymentStatus = PaymentStatus.RECEIVED.getValue();

    public Payment(){}

    public Payment(Integer id, Double value, ZonedDateTime paymentDate, String originCPF, Integer originBank, Integer originAccount, Integer originAgency, Integer destinationAccount, Integer destinationAgency, PaymentStatus paymentStatus) {
        this.id = id;
        this.value = value;
        this.paymentDate = paymentDate;
        this.originCPF = originCPF;
        this.originBank = originBank;
        this.originAccount = originAccount;
        this.originAgency = originAgency;
        this.destinationAccount = destinationAccount;
        this.destinationAgency = destinationAgency;
        this.paymentStatus = (paymentStatus == null) ? null : paymentStatus.getValue();;
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

    public Integer getOriginBank() {
        return originBank;
    }

    public void setOriginBank(Integer originBank) {
        this.originBank = originBank;
    }

    public Integer getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Integer originAccount) {
        this.originAccount = originAccount;
    }

    public Integer getOriginAgency() {
        return originAgency;
    }

    public void setOriginAgency(Integer originAgency) {
        this.originAgency = originAgency;
    }

    public Integer getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Integer destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public Integer getDestinationAgency() {
        return destinationAgency;
    }

    public void setDestinationAgency(Integer destinationAgency) {
        this.destinationAgency = destinationAgency;
    }

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus.toEnum(this.paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus.getValue();
    }
}
