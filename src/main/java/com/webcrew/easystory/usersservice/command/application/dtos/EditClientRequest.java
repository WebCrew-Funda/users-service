package com.webcrew.easystory.usersservice.command.application.dtos;

import lombok.Data;

@Data
public class EditClientRequest {
    private String id;
    private String userName;
    private String email;
    private String password;
    private String phone;
}
