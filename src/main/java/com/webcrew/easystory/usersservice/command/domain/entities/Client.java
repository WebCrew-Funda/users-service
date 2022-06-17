package com.webcrew.easystory.usersservice.command.domain.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.axonframework.modelling.command.AggregateIdentifier;

import com.webcrew.easystory.usersservice.command.domain.values.AuditTrail;
import com.webcrew.easystory.usersservice.command.domain.values.ClientId;
import com.webcrew.easystory.usersservice.command.domain.values.Email;
import com.webcrew.easystory.usersservice.command.domain.values.Password;
import com.webcrew.easystory.usersservice.command.domain.values.Phone;
import com.webcrew.easystory.usersservice.command.domain.values.UserName;

import lombok.Data;

@Entity(name = "Client")
@Table(name = "clients")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Client {
    @AggregateIdentifier
    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "value", column= @Column(name = "id", columnDefinition = "BINARY(16)"))
    })
    private ClientId id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "user_name", length = 150, nullable = false))
    })
    private UserName userName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "email", length = 150, nullable = false))
    })
    private Email email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "password", length = 150, nullable = false))
    })
    private Password password;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "phone", length = 150, nullable = false))
    })
    private Phone phone;

    @Embedded
    private AuditTrail auditTrail;

    public Client(ClientId clientId, UserName userName, Email email, Password password, Phone phon,
            AuditTrail auditTrail) {
        setId(id);
        setUserName(userName);
        setEmail(email);
        setPassword(password);
        setPhone(phone);
        setAuditTrail(auditTrail);
    }

    protected Client() {
    }

}
