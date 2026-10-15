package com.vinicius.crud_desafio.services;

import com.vinicius.crud_desafio.infras.entitys.User;
import com.vinicius.crud_desafio.infras.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UserRepository userRepository;

    public User adicionarUsuario(User usuario) {
        userRepository.save(usuario);
        return usuario;
    }


}
