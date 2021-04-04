package com.keepcoding.springboot.versioning;

public class NameStructure {

    private String name;
    private String surname;

    public NameStructure() {
    }

    public NameStructure(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
