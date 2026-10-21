package com.vinicius.crud_desafio.infras.enums;

import com.vinicius.crud_desafio.infras.entitys.User;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private  String role;

     UserRole(String role){
        this.role = role;
    }

    public String getRole(){
         return role;
    }

}
