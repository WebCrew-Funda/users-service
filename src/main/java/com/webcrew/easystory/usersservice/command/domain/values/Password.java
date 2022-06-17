package com.webcrew.easystory.usersservice.command.domain.values;

import javax.persistence.Embeddable;

import com.webcrew.easystory.usersservice.command.application.notification.Notification;
import com.webcrew.easystory.usersservice.command.application.notification.Result;

import lombok.Value;

@Embeddable
@Value
public class Password {
    private String value;

    private final static int MAX_LENGTH = 200;

    private Password(String password) {
        value = password;
    }

    protected Password() {
        this.value = "";
    }

    public static Result<Password, Notification> create(String password) {
        Notification notification = new Notification();
        password = password == null ? "" : password.trim();
        if (password.isEmpty()) {
            notification.addError("Password is required");
            return Result.failure(notification);
        }
        if (password.length() > MAX_LENGTH) {
            notification.addError("Password is too long");
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Password(password));
    }
}
