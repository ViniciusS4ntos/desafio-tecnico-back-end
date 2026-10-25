package com.vinicius.crud_desafio.infras.security;

import com.vinicius.crud_desafio.infras.exceptions.ObjectNotFoundException;
import com.vinicius.crud_desafio.infras.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    private TokenService tokenService;
    private UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository){
        this.tokenService = tokenService;
        this.userRepository  = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null){
            String login = tokenService.validadorToken(token);
            UserDetails user = userRepository.findByEmail(login)
                    .orElseThrow(() -> new ObjectNotFoundException("Email nao encontrado! : " + login));

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){

        var autHeader = request.getHeader("Authorization");
        if (autHeader == null){
            return null;
        }
        return autHeader.replace("Bearer ", "");
    }
}
