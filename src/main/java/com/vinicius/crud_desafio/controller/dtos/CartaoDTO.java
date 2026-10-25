package com.vinicius.crud_desafio.controller.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vinicius.crud_desafio.infras.enums.TipoCartao;

public class CartaoDTO {

    private Long numeroCartao;

    private String nome;

    private Boolean status;

    private TipoCartao tipoCartao;

    public CartaoDTO() {
        this.status = true;
        this.tipoCartao = TipoCartao.COMUM;

    }

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

    public void setNumeroCartao(Long numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

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
