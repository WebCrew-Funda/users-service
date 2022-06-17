package com.webcrew.easystory.usersservice.command.application.validators;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.webcrew.easystory.usersservice.command.application.dtos.EditClientRequest;
import com.webcrew.easystory.usersservice.command.application.notification.Notification;
import com.webcrew.easystory.usersservice.command.domain.entities.Client;
import com.webcrew.easystory.usersservice.command.infrastructure.repositories.ClientRepository;

@Component
public class EditClientValidator {
    private final ClientRepository clientRepository;

    public EditClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Notification validate(EditClientRequest editClientRequest) {
        Notification notification = new Notification();
        String clientId = editClientRequest.getId().trim();
        if (clientId.isEmpty()) {
            notification.addError("Client Id is required");
        }
        Optional<Client> clientOptional = clientRepository.findById(UUID.fromString(clientId));
        if (clientOptional.isPresent()) {
            notification.addError("Client not found");
            return notification;
        }
        String userName = editClientRequest.getUserName().trim();
        if (userName.isEmpty()) {
            notification.addError("Client UserName is required");
        }
        String email = editClientRequest.getEmail().trim();
        if (email.isEmpty()) {
            notification.addError("Client Email is required");
        }
        String password = editClientRequest.getPassword().trim();
        if (password.isEmpty()) {
            notification.addError("Client Password is required");
        }
        String phone = editClientRequest.getPhone().trim();
        if (phone.isEmpty()) {
            notification.addError("Client Phone is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        clientOptional = clientRepository.findByEmailValue(email);
        if (clientOptional.isPresent()) {
            notification.addError("Client with email " + email + " already exists");
        }
        return notification;
    }
}
