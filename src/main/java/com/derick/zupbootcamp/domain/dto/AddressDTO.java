package com.derick.zupbootcamp.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class AddressDTO implements BaseDTO, Serializable {

    private Integer id;

    @NotNull(message = "Client can't be empty")
    private Integer clientId;

    @NotEmpty(message = "Zip code can't be empty")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid pattern. The zip code must respect tha pattern XXXXX-XXX")
    private String zipCode;

    @NotEmpty(message = "Street code can't be empty")
    private String street;

    @NotEmpty(message = "District code can't be empty")
    private String district;

    @NotEmpty(message = "Complement code can't be empty")
    private String complement;

    @NotEmpty(message = "City code can't be empty")
    private String city;

    @NotEmpty(message = "State code can't be empty")
    private String state;

    public AddressDTO() {
    }

    public AddressDTO(Integer id, Integer clientId, String zipCode, String street, String district, String complement, String city, String state) {
        this.id = id;
        this.clientId = clientId;
        this.zipCode = zipCode;
        this.street = street;
        this.district = district;
        this.complement = complement;
        this.city = city;
        this.state = state;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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
}
