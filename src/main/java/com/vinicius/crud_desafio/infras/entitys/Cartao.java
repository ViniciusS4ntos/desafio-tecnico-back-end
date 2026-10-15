package com.vinicius.crud_desafio.infras.entitys;

import com.vinicius.crud_desafio.infras.enums.TipoCartao;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_card")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long numeroCartao;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private Boolean status;

    @Column(length = 20, nullable = false)
    private TipoCartao tipoCartao;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User usuario;

    public Cartao(String nome, TipoCartao tipoCartao){
        this.nome = nome;
        this.status = true;
        this.tipoCartao = tipoCartao;
    }

    public Cartao(){}

    public Long getNumeroCartao() {
        return numeroCartao;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getStatus() {
        return status;
    }

    public TipoCartao getTipoCartao() {
        return tipoCartao;
    }

    // Setters


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setTipoCartao(TipoCartao tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

}
