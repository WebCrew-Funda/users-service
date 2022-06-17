package com.webcrew.easystory.usersservice.command.domain.values;

import javax.persistence.Embeddable;

import com.webcrew.easystory.usersservice.command.application.notification.Notification;
import com.webcrew.easystory.usersservice.command.application.notification.Result;

import lombok.Value;

@Embeddable
@Value
public class UserName {
    private String value;
    private final static int MAX_LENGTH = 200;

    private UserName(String UserName) {
        value = UserName;
    }

    protected UserName() {
        this.value = "";
    }

    public static Result<UserName, Notification> create(String userName) {
        Notification notification = new Notification();
        userName = userName == null ? "" : userName.trim();
        if (userName.isEmpty()) {
            notification.addError("UserName is empty");
            return Result.failure(notification);
        }
        if (userName.length() > MAX_LENGTH) {
            notification.addError("UserName is too long");
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new UserName(userName));
    }
}
