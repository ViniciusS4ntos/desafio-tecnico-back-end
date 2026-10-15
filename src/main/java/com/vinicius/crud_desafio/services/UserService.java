package com.vinicius.crud_desafio.services;

import com.vinicius.crud_desafio.infras.entitys.User;
import com.vinicius.crud_desafio.infras.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User adicionarUsuario(User usuario) {
        emailExistente(usuario.getEmail());
        return userRepository.save(usuario);
    }

    public void excluirUsuarioByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email nao encontrado! : " + email)
        );

        userRepository.delete(user);
    }


    private void emailExistente(String email){
        if (userRepository.existsByEmail(email)){
            throw new RuntimeException("Email ja existente no banco! : "  + email);
        }
    }


}
