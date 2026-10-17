package com.vinicius.crud_desafio.controller.dtos.in;

import jakarta.persistence.*;

// entrada
public class UserDTO {

    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 70, nullable = false, unique = true)
    private String email;

    @Column(length = 40, nullable = false)
    private String password;


    public UserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UserDTO() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
