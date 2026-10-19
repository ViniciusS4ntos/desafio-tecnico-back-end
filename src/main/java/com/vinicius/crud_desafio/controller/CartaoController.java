package com.vinicius.crud_desafio.controller;

import com.vinicius.crud_desafio.infras.entitys.Cartao;
import com.vinicius.crud_desafio.infras.enums.TipoCartao;
import com.vinicius.crud_desafio.services.CartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CartaoController {

    private CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping()
    public ResponseEntity<Cartao> criarCartao(@RequestParam("id") Long id,
                                              // corrigir a escolha quando for criar o cartao
                                              @RequestBody TipoCartao tipoCartao) {

        return ResponseEntity.ok(
                cartaoService.criarCartao(id, tipoCartao)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Cartao>> consultarCartoes(@PathVariable Long id) {
        return ResponseEntity.ok(
                cartaoService.visualizarCartoes(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cartao> atualizarCartao(@PathVariable  Long id,
                                                  @RequestBody Cartao cartaoAtualizado){
        return ResponseEntity.ok(
                cartaoService.atualizarCartao(id,cartaoAtualizado)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cartao> desativarAtivarCartao(@PathVariable  Long id){
        return ResponseEntity.ok(
                cartaoService.atualizarStatusCartao(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCartao(@PathVariable Long id){
        cartaoService.deletarCartao(id);
        return ResponseEntity.noContent().build();
    }

}
