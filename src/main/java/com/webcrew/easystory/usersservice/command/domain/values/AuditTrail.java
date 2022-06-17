package com.webcrew.easystory.usersservice.command.domain.values;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.webcrew.easystory.usersservice.command.application.notification.Notification;
import com.webcrew.easystory.usersservice.command.application.notification.Result;

import lombok.Value;

@Embeddable
@Value
public class AuditTrail {
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "created_by"))
    })
    private ClientId createdBy;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "uptated_by"))
    })
    private ClientId updatedBy;

    private AuditTrail(LocalDateTime createdAt, LocalDateTime updatedAt, ClientId createdBy, ClientId updatedBy) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    protected AuditTrail() {
        this.createdAt = null;
        this.updatedAt = null;
        this.createdBy = null;
        this.updatedBy = null;
    }

    public static Result<AuditTrail, Notification> create(UUID createdBy) {
        return Result.success(new AuditTrail(LocalDateTime.now(ZoneOffset.UTC), null, ClientId.of(createdBy), null));
    }
}
