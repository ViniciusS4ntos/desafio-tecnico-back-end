package com.vinicius.crud_desafio.infras.repository;

import com.vinicius.crud_desafio.infras.entitys.User;
import com.vinicius.crud_desafio.infras.entitys.UserCardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query(value = """
            SELECT u.nome, 
                   u.email, 
                   c.numero_cartao AS numeroCartao, 
                   c.status, 
                   c.tipo_cartao AS tipoCartao 
            FROM TB_USER u
            INNER JOIN TB_CARD c ON c.user_id = u.id
            """, nativeQuery = true)
    List<UserCardSearch> buscarUsuariosComCartoes();

}
