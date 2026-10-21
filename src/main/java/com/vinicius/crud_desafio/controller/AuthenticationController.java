package com.vinicius.crud_desafio.controller;

import com.vinicius.crud_desafio.controller.dtos.AuthenticationDTO;
import com.vinicius.crud_desafio.controller.dtos.RegisterDTO;
import com.vinicius.crud_desafio.controller.dtos.UserDTO;
import com.vinicius.crud_desafio.services.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthorizationService authorizationService;


    public AuthenticationController(AuthenticationManager authenticationManager, AuthorizationService authorizationService){

        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO dto){
        return ResponseEntity.ok(authorizationService.login(dto));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO dto){
        authorizationService.register(dto);
        return ResponseEntity.ok().build();
    }

}
