package com.derick.zupbootcamp.domain.dto;

import com.derick.zupbootcamp.domain.entities.Address;
import com.derick.zupbootcamp.domain.entities.ClientCPF;
import com.derick.zupbootcamp.services.validation.ClientInsert;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.ZonedDateTime;

@ClientInsert
public class ClientDTO implements BaseDTO, Serializable {

    private Integer id;

    @NotEmpty(message = "First name can't be empty")
    @Length(min = 3, max = 20, message = "Size needs to be between 3 and 20 characters")
    private String firstName;

    @NotEmpty(message = "Last name can't be empty")
    @Length(min = 5, max = 20, message = "Size needs to be between 5 and 20 characters")
    private String lastName;

    @Length(min = 5, max = 90, message = "Size needs to be between 5 and 90 characters")
    @Email(message = "Invalid email")
    private String email;

    @Column(unique = true)
    private String cpf;

    @Past(message = "The birth date should be in the past")
    private ZonedDateTime birthDate;

    private Address address;

    public ClientDTO() {
    }

    public ClientDTO(Integer id, String firstName, String lastName, String email, String cpf, ZonedDateTime birthDate, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.address = address;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
