package com.webcrew.easystory.usersservice.command.domain.values;

import javax.persistence.Embeddable;

import com.webcrew.easystory.usersservice.command.application.notification.Notification;
import com.webcrew.easystory.usersservice.command.application.notification.Result;

import lombok.Value;

@Embeddable
@Value
public class Email {
    public String value;

    private final static int MAX_LENGTH = 150;

    private Email(String email) {
        value = email;
    }

    protected Email() {
        this.value = "";
    }

    public static Result<Email, Notification> create(String email) {
        Notification notification = new Notification();
        email = email == null ? "" : email.trim();
        if (email.isEmpty()) {
            notification.addError("Email is required");
            return Result.failure(notification);
        }
        if (email.length() > MAX_LENGTH) {
            notification.addError("Email is too long");
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Email(email));
    }
}
