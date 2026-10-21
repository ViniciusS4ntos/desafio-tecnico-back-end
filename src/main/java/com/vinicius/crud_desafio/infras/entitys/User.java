package com.vinicius.crud_desafio.infras.entitys;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.vinicius.crud_desafio.infras.enums.UserRole;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_user")
@JsonPropertyOrder({"id", "name", "email"})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String nome;

    @Column(length = 70, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Cartao> cartoes;

    public User(Long id, String name, String email, String senha) {
        this.id = id;
        this.nome = name;
        this.email = email;
        this.senha = senha;
        this.role = UserRole.USER;
    }

    public User() {
        this.role = UserRole.USER;
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

    public UserRole getRole(){
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role  == UserRole.ADMIN){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else  {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    public String getPassword(){
        return senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    // Setters
    public void setNome(String name) {
        this.nome = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.senha = password;
    }

    public void setRole(UserRole role){
        this.role = role;
    }

}

