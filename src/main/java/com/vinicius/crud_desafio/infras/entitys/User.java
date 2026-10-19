package com.vinicius.crud_desafio.infras.entitys;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "tb_user")
@JsonPropertyOrder({"id", "name", "email"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String nome;

    @Column(length = 70, nullable = false, unique = true)
    private String email;

    @Column(length = 40, nullable = false)
    private String password;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Cartao> cartoes;

    public User(String name, String email, String password) {
        this.nome = name;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setNome(String name) {
        this.nome = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

