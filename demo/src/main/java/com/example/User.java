package com.example;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

public class User {

    private final UUID id;
    private final String name;
    private final String email;
    private final String role;

    public User(UUID id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }


    public User updateEmail(String newEmail) {
        return new User(
            this.id,
            this.name,
            newEmail,
            this.role
        );
    }

    public User updateRole(String newRole) {
        return new User(
            this.id,
            this.name,
            this.email,
            newRole
        );
    }
}
