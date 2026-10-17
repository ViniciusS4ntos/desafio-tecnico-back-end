package com.vinicius.crud_desafio.services;

import com.vinicius.crud_desafio.infras.entitys.Cartao;
import com.vinicius.crud_desafio.infras.entitys.User;
import com.vinicius.crud_desafio.infras.enums.TipoCartao;
import com.vinicius.crud_desafio.infras.repository.CartaoRepository;
import com.vinicius.crud_desafio.infras.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoService {

    private CartaoRepository cartaoRepository;
    private UserRepository userRepository;


    public CartaoService(CartaoRepository cartaoRepository, UserRepository userRepository){
        this.cartaoRepository = cartaoRepository;
        this.userRepository = userRepository;
    }

    public Cartao criarCartao(Long id, TipoCartao tipoCartao){

        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado! id " + id )
        );

        for (Cartao cartao : user.getCartoes()) {
            if (cartao.getTipoCartao().equals(tipoCartao)) {
                throw new IllegalArgumentException("Cartao ja existente com esse tipo: " + tipoCartao);
            }
        }

        Cartao cartao = new Cartao();
        cartao.setTipoCartao(tipoCartao);
        cartao.setNome("cartao " + user.getNome());
        cartao.setUsuario(user);


        return cartaoRepository.save(cartao);
    }

    public List<Cartao> visualizarCartoes(Long id){

        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado! id " + id )
        );

        return user.getCartoes();
    }

}
