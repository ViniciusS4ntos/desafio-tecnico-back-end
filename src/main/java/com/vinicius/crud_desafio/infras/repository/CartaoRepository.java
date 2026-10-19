package com.vinicius.crud_desafio.infras.repository;

import com.vinicius.crud_desafio.infras.entitys.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
}
