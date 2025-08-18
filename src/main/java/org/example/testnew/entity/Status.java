package org.example.testnew.entity;

public enum Status {
    ACTIVE(200),
    INACTIVE(210),
    SUSPENDED(230);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
