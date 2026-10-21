package com.vinicius.crud_desafio.controller;

import com.vinicius.crud_desafio.controller.dtos.UserDTO;
import com.vinicius.crud_desafio.infras.entitys.UserCardSearch;
import com.vinicius.crud_desafio.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserDTO> adicionarUsuario(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.adicionarUsuario(user));

    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> buscarTodos() {
        return ResponseEntity.ok(userService.buscarTodos());
    }

    @GetMapping("/card")
    public ResponseEntity<List<UserCardSearch>> consultarUsersCard(){
        return ResponseEntity.ok(userService.usuariosComCartoes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> atualizarUsuario(@PathVariable Long id,
                                                    @RequestBody UserDTO userAtualizado) {
        return ResponseEntity.ok(userService.atualizarUsuario(id, userAtualizado));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarUsuario(@RequestParam("email") String email) {
        userService.excluirUsuarioByEmail(email);
        return ResponseEntity.noContent().build();
    }


}
