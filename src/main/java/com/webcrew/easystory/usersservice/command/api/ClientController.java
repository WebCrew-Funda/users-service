package com.webcrew.easystory.usersservice.command.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webcrew.easystory.usersservice.command.application.dtos.RegisterClientRequest;
import com.webcrew.easystory.usersservice.command.application.dtos.RegisterClientResponse;
import com.webcrew.easystory.usersservice.command.application.notification.Notification;
import com.webcrew.easystory.usersservice.command.application.notification.Result;
import com.webcrew.easystory.usersservice.command.application.services.ClientApplicationService;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientApplicationService clientApplicationService;

    public ClientController(ClientApplicationService clientApplicationService) {
        this.clientApplicationService = clientApplicationService;
    }

    public ResponseEntity<Object> registerClient(@RequestBody RegisterClientRequest registerClientRequest) {
        try {
            Result<RegisterClientResponse, Notification> result = clientApplicationService
                    .registerClient(registerClientRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (Exception e) {
            return ApiController.serverError();
        }
    }

}
