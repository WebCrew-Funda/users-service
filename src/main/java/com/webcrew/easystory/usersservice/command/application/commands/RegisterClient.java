package com.webcrew.easystory.usersservice.command.application.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class RegisterClient {
    @TargetAggregateIdentifier
    private String id;
    private String userName;
    private String email;
    private String password;
    private String phone;

}
