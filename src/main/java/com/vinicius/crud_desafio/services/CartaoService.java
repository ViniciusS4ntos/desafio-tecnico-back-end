package com.vinicius.crud_desafio.services;

import com.vinicius.crud_desafio.controller.dtos.CartaoDTO;
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

    public CartaoDTO criarCartao(Long id, TipoCartao tipoCartao) {

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

        return converteDTO(cartaoRepository.save(cartao));
    }

    public List<CartaoDTO> visualizarCartoes(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario nao encontrado! id " + id)
        );

        return user.getCartoes().stream().map(
                c -> converteDTO(c)
        ).toList();
    }

    public CartaoDTO atualizarCartao(Long id, CartaoDTO cartaoAtualizado) {
        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartao nao encontrado id:" + id));

        cartao.setTipoCartao(
                cartaoAtualizado.getTipoCartao() != null ?
                        cartaoAtualizado.getTipoCartao() : cartao.getTipoCartao()
        );

        cartao.setNome(
                cartaoAtualizado.getNome() != null ?
                        cartaoAtualizado.getNome() : cartao.getNome()
        );

        return converteDTO(cartaoRepository.save(cartao));
    }

    public CartaoDTO atualizarStatusCartao(Long id) {

        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartao nao encontrado id:" + id));

        if (cartao.getStatus() == true) {
            cartao.setStatus(false);
            return converteDTO(cartaoRepository.save(cartao));
        } else {
            cartao.setStatus(true);
            return converteDTO(cartaoRepository.save(cartao));
        }
    }

    public void deletarCartao(Long id) {
        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartao nao encontrado id:" + id));

        cartaoRepository.delete(cartao);
    }

    private Cartao converteEntity(CartaoDTO dto) {
        Cartao entity = new Cartao();
        entity.setNome(dto.getNome());
        entity.setTipoCartao(dto.getTipoCartao());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    private CartaoDTO converteDTO(Cartao cartao) {
        CartaoDTO dto = new CartaoDTO();
        dto.setNome(cartao.getNome());
        dto.setTipoCartao(cartao.getTipoCartao());
        dto.setStatus(cartao.getStatus());
        return dto;
    }


}
