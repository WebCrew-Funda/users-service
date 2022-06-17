package com.webcrew.easystory.usersservice.command.application.dtos;

import lombok.Value;

@Value
public class RegisterClientRequest {
    private String userName;
    private String email;
    private String password;
    private String phone;
}
