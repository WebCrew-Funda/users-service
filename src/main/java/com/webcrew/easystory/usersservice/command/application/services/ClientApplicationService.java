package com.webcrew.easystory.usersservice.command.application.services;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;

import com.webcrew.easystory.usersservice.command.application.commands.RegisterClient;
import com.webcrew.easystory.usersservice.command.application.dtos.ClientView;
import com.webcrew.easystory.usersservice.command.application.dtos.RegisterClientRequest;
import com.webcrew.easystory.usersservice.command.application.dtos.RegisterClientResponse;
import com.webcrew.easystory.usersservice.command.application.enums.ResultType;
import com.webcrew.easystory.usersservice.command.application.notification.Notification;
import com.webcrew.easystory.usersservice.command.application.notification.Result;
import com.webcrew.easystory.usersservice.command.application.queries.GetClientByIdQuery;
import com.webcrew.easystory.usersservice.command.application.validators.EditClientValidator;
import com.webcrew.easystory.usersservice.command.application.validators.RegisterClientValidator;

@Component
public class ClientApplicationService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final RegisterClientValidator registerClientValidator;

    public ClientApplicationService(CommandGateway commandGateway, QueryGateway queryGateway,
            RegisterClientValidator registerClientValidator, EditClientValidator editClientValidator) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        this.registerClientValidator = registerClientValidator;
    }

    public Result<RegisterClientResponse, Notification> registerClient(RegisterClientRequest registerClientRequest)
            throws Exception {
        Notification notification = this.registerClientValidator.validate(registerClientRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String clientId = UUID.randomUUID().toString();
        RegisterClient registerClient = new RegisterClient(clientId, registerClientRequest.getUserName().trim(),
                registerClientRequest.getEmail().trim(), registerClientRequest.getPassword().trim(),
                registerClientRequest.getPhone().trim());

        CompletableFuture<Object> future = commandGateway.send(registerClient);

        CompletableFuture<ResultType> futureResult = future
                .handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterClientResponse registerClientResponse = new RegisterClientResponse(
                registerClient.getId(),
                registerClient.getUserName(),
                registerClient.getEmail(),
                registerClient.getPassword(),
                registerClient.getPhone());
        return Result.success(registerClientResponse);
    }

    public ClientView getById(String clientId) throws Exception {
        CompletableFuture<ClientView> future = queryGateway.query(new GetClientByIdQuery(clientId),
                ResponseTypes.instanceOf(ClientView.class));
        CompletableFuture<ResultType> futureResult = future
                .handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        return future.get();
    }

}
