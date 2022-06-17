package com.webcrew.easystory.usersservice.command.application.queries;

import lombok.Data;

@Data
public class GetClientByIdQuery {
    private String clientId;

    public GetClientByIdQuery(String clientId) {
        this.clientId = clientId;
    }
}
