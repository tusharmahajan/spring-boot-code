package com.tushar.swiggy.authorizationAssignment.models;

import lombok.Getter;

@Getter
public enum PermissionsEnum {
    ALLOW_READ("read"),
    ALLOW_WRITE("write"),
    ALLOW_UPDATE("update");

    private final String val;

    PermissionsEnum(String val) {
        this.val = val;
    }
}
