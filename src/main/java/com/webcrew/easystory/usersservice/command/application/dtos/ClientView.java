package com.webcrew.easystory.usersservice.command.application.dtos;

import lombok.Data;

@Data
public class ClientView {
    private String clientId;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private String createdAt;
}
