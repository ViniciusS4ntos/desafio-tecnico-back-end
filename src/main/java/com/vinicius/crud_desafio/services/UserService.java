package com.vinicius.crud_desafio.services;

import com.vinicius.crud_desafio.infras.entitys.User;
import com.vinicius.crud_desafio.infras.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> buscarTodos(){
        return userRepository.findAll();
    }

    public User atualizarUsuario(Long id, User user){
        User userBanco = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado! id : " + id));

        if (userBanco.getEmail().equalsIgnoreCase(user.getEmail())){
            throw new RuntimeException("A sua conta ja usar o email desejado!");
        }

        emailExistente(user.getEmail());

        return userRepository.save(
                UsuarioAtualizar(userBanco, user)
        );
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

    private User UsuarioAtualizar(User userBanco, User userAtualizdo){
        userBanco.setName(
                userAtualizdo.getName() != null ? userAtualizdo.getName() : userBanco.getName()
        );

        userBanco.setEmail(
                userAtualizdo.getEmail() != null ? userAtualizdo.getEmail() : userBanco.getEmail()
        );

        userBanco.setPassword(
                userAtualizdo.getPassword() != null ? userAtualizdo.getPassword() : userBanco.getPassword()
        );

        return userBanco;
    }


}
