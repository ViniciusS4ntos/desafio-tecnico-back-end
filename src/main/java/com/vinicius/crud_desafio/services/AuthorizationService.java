package com.vinicius.crud_desafio.services;

import com.vinicius.crud_desafio.controller.dtos.AuthenticationDTO;
import com.vinicius.crud_desafio.controller.dtos.RegisterDTO;
import com.vinicius.crud_desafio.controller.dtos.UserDTO;
import com.vinicius.crud_desafio.infras.entitys.User;
import com.vinicius.crud_desafio.infras.enums.UserRole;
import com.vinicius.crud_desafio.infras.exceptions.ExistingObjectException;
import com.vinicius.crud_desafio.infras.exceptions.ObjectNotFoundException;
import com.vinicius.crud_desafio.infras.repository.UserRepository;
import com.vinicius.crud_desafio.infras.security.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private UserRepository userRepository;
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;


    public AuthorizationService(UserRepository userRepository,
                                UserService userService,
                                @Lazy AuthenticationManager authenticationManager,
                                TokenService tokenService
    ){
        this.userRepository = userRepository;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;

    }

    public String login(AuthenticationDTO dto){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                dto.getEmail(), dto.getSenha()
        );


        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        String token = tokenService.geradorToken((User) auth.getPrincipal());
        return token;
    }

    public UserDTO register(RegisterDTO dto){
        if (userRepository.existsByEmail(dto.getEmail())){
            throw new ExistingObjectException("Email ja existente no banco! : "  + dto.getEmail());
        }

        String encrypassword = new BCryptPasswordEncoder().encode(dto.getSenha());
        User userNew = new User();

        userNew.setNome(dto.getNome());
        userNew.setEmail(dto.getEmail());
        userNew.setPassword(encrypassword);
        userNew.setRole(UserRole.ADMIN);

        return userService.converteEntity(
                userRepository.save(userNew)
        );

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado! email : " + username));
    }



}
