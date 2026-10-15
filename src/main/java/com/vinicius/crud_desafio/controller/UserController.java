package com.vinicius.crud_desafio.controller;

import com.vinicius.crud_desafio.infras.entitys.User;
import com.vinicius.crud_desafio.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<User> adicionarUsuario(@RequestBody User user){
        return ResponseEntity.ok(userService.adicionarUsuario(user));

    }

    @GetMapping()
    public ResponseEntity<List<User>> buscarTodos(){
        return ResponseEntity.ok(userService.buscarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizarUsuario(@PathVariable Long id,
                                                 @RequestBody User  userAtualizado){
        return ResponseEntity.ok(userService.atualizarUsuario(id, userAtualizado));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarUsuario(@RequestParam("email") String email){
        userService.excluirUsuarioByEmail(email);
        return ResponseEntity.noContent().build();
    }

}
