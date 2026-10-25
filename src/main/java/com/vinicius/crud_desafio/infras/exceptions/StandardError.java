package com.vinicius.crud_desafio.infras.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class StandardError {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private final LocalDateTime timestamp;
    private final Integer statusHttp;
    private final String error;
    private final String path;

    public StandardError(
            LocalDateTime localDateTime,
            Integer statusHttp,
            String error,
            String path
    ){
        this.timestamp = localDateTime;
        this.statusHttp  = statusHttp;
        this.error = error;
        this.path = path;
    }

    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }

    public Integer getStatusHttp(){
        return this.statusHttp;
    }

    public String getError(){
        return this.error;
    }

    public String getPath(){
        return this.path;
    }


}
