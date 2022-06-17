package com.webcrew.easystory.usersservice.command.application.dtos;

import lombok.Value;

@Value
public class RegisterClientResponse {
    private String id;
    private String userName;
    private String email;
    private String password;
    private String phone;
}
