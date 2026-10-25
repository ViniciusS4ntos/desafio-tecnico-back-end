package com.vinicius.crud_desafio.services;

import com.vinicius.crud_desafio.controller.dtos.UserDTO;
import com.vinicius.crud_desafio.infras.entitys.User;
import com.vinicius.crud_desafio.infras.entitys.UserCardSearch;
import com.vinicius.crud_desafio.infras.exceptions.ExistingObjectException;
import com.vinicius.crud_desafio.infras.exceptions.ObjectNotFoundException;
import com.vinicius.crud_desafio.infras.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        String encrypassword = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(encrypassword);

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

    public List<UserCardSearch> usuariosComCartoes(){
        return userRepository.buscarUsuariosComCartoes();
    }

    public UserDTO atualizarUsuario(Long id, UserDTO user){
        User userBanco = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado! id : " + id));

        if (userBanco.getEmail().equalsIgnoreCase(user.getEmail())){
            throw new ExistingObjectException("A sua conta ja usar o email desejado!");
        }

        emailExistente(user.getEmail());

        return converteEntity(
                userRepository.save(
                        (UsuarioAtualizar(userBanco, converteDTO(user))
        )));
    }

    public void excluirUsuarioByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new ObjectNotFoundException("Email nao encontrado! : " + email)
        );

        userRepository.delete(user);
    }


    private void emailExistente(String email){
        if (userRepository.existsByEmail(email)){
            throw new ExistingObjectException("Email ja existente no banco! : "  + email);
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
                userAtualizdo.getPassword() != null ?
                        new BCryptPasswordEncoder().encode(userAtualizdo.getPassword()) :
                        new BCryptPasswordEncoder().encode(userBanco.getPassword())
        );

        return userBanco;
    }

    // Nao usar modelMapper
    public User converteDTO(UserDTO userDTO){

        return new User(
                userDTO.getId(),
                userDTO.getNome(),
                userDTO.getEmail(),
                userDTO.getSenha()
        );
    }

    public UserDTO converteEntity(User user){

        return new UserDTO(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getPassword()
        );
    }


}
