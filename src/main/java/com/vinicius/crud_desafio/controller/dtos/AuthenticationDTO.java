package com.vinicius.crud_desafio.controller.dtos;

import com.vinicius.crud_desafio.infras.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class AuthenticationDTO {

    private  String email;
    private  String senha;

    public AuthenticationDTO(){}

    public AuthenticationDTO(String email, String senha, UserRole userRole){
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
