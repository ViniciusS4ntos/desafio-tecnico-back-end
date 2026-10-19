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


    public CartaoService(CartaoRepository cartaoRepository, UserRepository userRepository) {
        this.cartaoRepository = cartaoRepository;
        this.userRepository = userRepository;
    }

    public Cartao criarCartao(Long id, TipoCartao tipoCartao) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado! id " + id)
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

    public List<Cartao> visualizarCartoes(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado! id " + id)
        );

        return user.getCartoes();
    }

    public Cartao atualizarCartao(Long id, Cartao cartaoAtualizado){
        Cartao  cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartao nao encontrado id:" + id));

        cartao.setTipoCartao(
                cartaoAtualizado.getTipoCartao() != null ?
                        cartaoAtualizado.getTipoCartao() : cartao.getTipoCartao()
        );

        cartao.setNome(
                cartaoAtualizado.getNome() != null ?
                        cartaoAtualizado.getNome() : cartao.getNome()
                );

        return cartaoRepository.save(cartao);
    }

    public Cartao atualizarStatusCartao(Long id){

        Cartao  cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartao nao encontrado id:" + id));

        if (cartao.getStatus() == true){
            cartao.setStatus(false);
            return cartaoRepository.save(cartao);
        } else {
            cartao.setStatus(true);
            return cartaoRepository.save(cartao);
        }
    }

    public void deletarCartao(Long id){
        Cartao  cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartao nao encontrado id:" + id));

        cartaoRepository.delete(cartao);
    }

}
