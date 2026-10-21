package com.vinicius.crud_desafio.controller.dtos;

// entrada
public class UserDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;


    public UserDTO(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
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

    // Setters


    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = this.nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
