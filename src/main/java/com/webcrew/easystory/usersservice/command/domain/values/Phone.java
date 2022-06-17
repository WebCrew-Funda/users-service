package com.webcrew.easystory.usersservice.command.domain.values;

import javax.persistence.Embeddable;

import com.webcrew.easystory.usersservice.command.application.notification.Notification;
import com.webcrew.easystory.usersservice.command.application.notification.Result;

import lombok.Value;

@Embeddable
@Value
public class Phone {
    private String value;
    private final static int MAX_LENGTH = 200;

    private Phone(String phone) {
        value = phone;
    }

    protected Phone() {
        this.value = "";
    }

    public static Result<Phone, Notification> create(String phone) {
        Notification notification = new Notification();
        phone = phone == null ? "" : phone.trim();
        if (phone.isEmpty()) {
            notification.addError("Phone is required");
            return Result.failure(notification);
        }
        if (phone.length() > MAX_LENGTH) {
            notification.addError("Phone is too long");
        }
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        return Result.success(new Phone(phone));
    }
}
