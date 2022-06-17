package com.webcrew.easystory.usersservice.command.domain.values;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import lombok.Value;

@Embeddable
@Value(staticConstructor = "of")
public class ClientId implements Serializable {
    private UUID value;

    private ClientId(UUID value) {
        this.value = value;
    }

    protected ClientId() {
        this.value = UUID.randomUUID();
    }

}
