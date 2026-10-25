package com.vinicius.crud_desafio.controller;

import com.vinicius.crud_desafio.controller.dtos.CartaoDTO;
import com.vinicius.crud_desafio.infras.entitys.Cartao;
import com.vinicius.crud_desafio.infras.enums.TipoCartao;
import com.vinicius.crud_desafio.services.CartaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@CrossOrigin(origins = "*")
public class CartaoController {

    private CartaoService cartaoService;

    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping()
    public ResponseEntity<CartaoDTO> criarCartao(@RequestParam("id") Long id,
                                                 @RequestBody CartaoDTO CartaoDTO) {

        return ResponseEntity.ok(
                cartaoService.criarCartao(id, CartaoDTO)
        );
    }

    @GetMapping()
    public ResponseEntity<List<CartaoDTO>> consultarTodosCartoes(){
        return ResponseEntity.ok(
                cartaoService.listarTodosCartoes()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CartaoDTO>> consultarCartoes(@PathVariable Long id) {
        return ResponseEntity.ok(
                cartaoService.visualizarCartoes(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoDTO> atualizarCartao(@PathVariable  Long id,
                                                  @RequestBody CartaoDTO cartaoAtualizado){
        return ResponseEntity.ok(
                cartaoService.atualizarCartao(id,cartaoAtualizado)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CartaoDTO> desativarAtivarCartao(@PathVariable  Long id){
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
