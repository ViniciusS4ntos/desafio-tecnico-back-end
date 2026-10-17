package com.vinicius.crud_desafio.services;

import com.vinicius.crud_desafio.controller.dtos.in.UserDTO;
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

    public UserDTO adicionarUsuario(UserDTO usuario) {
        emailExistente(usuario.getEmail());

        return converteEntity(
            userRepository.save(
                    converteDTO(usuario)
            )
        );


    }

    public List<UserDTO> buscarTodos(){
        List<User> list = userRepository.findAll();
        return list.stream().map(l -> converteEntity(l)).toList();
    }

    public UserDTO atualizarUsuario(Long id, UserDTO user){
        User userBanco = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado! id : " + id));

        if (userBanco.getEmail().equalsIgnoreCase(user.getEmail())){
            throw new RuntimeException("A sua conta ja usar o email desejado!");
        }

        emailExistente(user.getEmail());

        return converteEntity(
                userRepository.save(
                        (UsuarioAtualizar(userBanco, converteDTO(user))
        )));
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
        userBanco.setNome(
                userAtualizdo.getNome() != null ? userAtualizdo.getNome() : userBanco.getNome()
        );

        userBanco.setEmail(
                userAtualizdo.getEmail() != null ? userAtualizdo.getEmail() : userBanco.getEmail()
        );

        userBanco.setPassword(
                userAtualizdo.getPassword() != null ? userAtualizdo.getPassword() : userBanco.getPassword()
        );

        return userBanco;
    }

    // Nao usar modelMapper
    private User converteDTO(UserDTO userDTO){

        return new User(
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword()
        );
    }

    private UserDTO converteEntity(User user){

        return new UserDTO(
                user.getNome(),
                user.getEmail(),
                user.getPassword()
        );
    }


}
