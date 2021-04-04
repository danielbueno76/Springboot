package com.keepcoding.springboot.versioning;

public class UserVersionV1 {

    private String name;

    public UserVersionV1() {
    }

    public UserVersionV1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
