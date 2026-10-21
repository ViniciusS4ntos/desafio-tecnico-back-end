package com.vinicius.crud_desafio.controller.dtos;

import com.vinicius.crud_desafio.infras.enums.UserRole;

public class RegisterDTO {

    private String nome;
    private String email;
    private String senha;
    private UserRole userRole;

    public RegisterDTO() {

    }

    public RegisterDTO(String nome,String email, String senha, UserRole userRole) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.userRole = userRole;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }


    public UserRole getUserRole() {
        return userRole;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
