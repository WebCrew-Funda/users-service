package com.webcrew.easystory.usersservice.command.application.validators;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.webcrew.easystory.usersservice.command.application.dtos.RegisterClientRequest;
import com.webcrew.easystory.usersservice.command.application.notification.Notification;
import com.webcrew.easystory.usersservice.command.domain.entities.Client;
import com.webcrew.easystory.usersservice.command.infrastructure.repositories.ClientRepository;

@Component
public class RegisterClientValidator {
    private final ClientRepository clientRepository;

    public RegisterClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Notification validate(RegisterClientRequest registerClientRequest) {
        Notification notification = new Notification();
        String userName = registerClientRequest.getUserName() != null ? registerClientRequest.getUserName().trim() : "";
        if (userName.isEmpty()) {
            notification.addError("Client UserName is required");
        }
        String email = registerClientRequest.getEmail() != null ? registerClientRequest.getEmail().trim() : "";
        if (email.isEmpty()) {
            notification.addError("Client Email is required");
        }
        String password = registerClientRequest.getPassword() != null ? registerClientRequest.getPassword().trim() : "";
        if (password.isEmpty()) {
            notification.addError("Client Password is required");
        }
        String phone = registerClientRequest.getPhone() != null ? registerClientRequest.getPhone().trim() : "";
        if (phone.isEmpty()) {
            notification.addError("Client Phone is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        Optional<Client> clientOptional = clientRepository.findByEmailValue(email);
        if (clientOptional.isPresent()) {
            notification.addError("Client with email " + email + " already exists");
        }
        return notification;
    }
}
