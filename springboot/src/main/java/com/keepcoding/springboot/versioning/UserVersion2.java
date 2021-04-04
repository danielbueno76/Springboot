package com.keepcoding.springboot.versioning;

public class UserVersion2 {

    private NameStructure name;

    public UserVersion2() {
    }

    public UserVersion2(NameStructure name) {
        this.name = name;
    }

    public NameStructure getName() {
        return name;
    }

    public void setName(NameStructure name) {
        this.name = name;
    }
}
