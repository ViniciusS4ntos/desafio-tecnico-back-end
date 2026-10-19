package com.vinicius.crud_desafio.controller.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinicius.crud_desafio.infras.enums.TipoCartao;

public class CartaoDTO {

    private String nome;

    private Boolean status;

    private TipoCartao tipoCartao;


    public CartaoDTO() {
        this.status = true;
        this.tipoCartao = TipoCartao.COMUM;

    }

    public String getNome() {
        return nome;
    }

    @JsonIgnore
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
