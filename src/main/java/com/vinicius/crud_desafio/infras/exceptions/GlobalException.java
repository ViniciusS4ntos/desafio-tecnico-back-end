package com.vinicius.crud_desafio.infras.exceptions;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e,
                                                                 HttpServletRequest request) {
        StandardError se = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
    }

    @ExceptionHandler(ExistingObjectException.class)
    public ResponseEntity<StandardError> existingObjectException(ExistingObjectException  e,
                                                                 HttpServletRequest request
                                                                 ){
        StandardError se =  new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(se);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<StandardError> jwtCreationException(ExistingObjectException  e,
                                                                 HttpServletRequest request
    ){
        StandardError se =  new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(se);
    }


}
