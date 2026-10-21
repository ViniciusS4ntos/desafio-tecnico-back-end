package com.vinicius.crud_desafio.infras.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.vinicius.crud_desafio.infras.entitys.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.secret.key}")
    private String secret;

    public String geradorToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("api-vem")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expiradorTempoToken())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException e){
            throw new JWTCreationException("Erro de geracao de token!", e.getCause());
        }
    }

    public String validadorToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api-vem")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e){
            return "";
        }
    }

    private Instant expiradorTempoToken(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
