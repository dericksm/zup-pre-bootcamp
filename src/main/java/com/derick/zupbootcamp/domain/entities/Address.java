package com.derick.zupbootcamp.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Address extends BaseEntity implements Serializable {

    private String zipCode;
    private String street;
    private String district;
    private String complement;
    private String city;
    private String state;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonBackReference
    private Client client;

    public Address() {
    }

    public Address(Integer id, String zipCode, String street, String district, String complement, String city, String state, Client client) {
        this.id = id;
        this.zipCode = zipCode;
        this.street = street;
        this.district = district;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.client = client;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Address{");
        sb.append("zipCode='").append(zipCode).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", complement='").append(complement).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", client=").append(client);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
