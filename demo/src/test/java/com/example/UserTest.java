package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class UserTest {

    @Test
    void shouldUpdateEmailByCreatingNewInstance() {
        User originalUser = new User(
                UUID.randomUUID(),
                "Fernando",
                "fernando@gmail.com",
                "Developer"
        );

        User updatedUser = originalUser.updateEmail("new@gmail.com");

        assertEquals("fernando@gmail.com", originalUser.getEmail());
        assertEquals("new@gmail.com", updatedUser.getEmail());
        assertNotSame(originalUser, updatedUser);
    }

    @Test
    void shouldUpdateRoleWithoutChangingOriginalInstance() {
        User originalUser = new User(
                UUID.randomUUID(),
                "Ana",
                "ana@gmail.com",
                "Junior Developer"
        );

        User updatedUser = originalUser.updateRole("Senior Developer");

        assertEquals("Junior Developer", originalUser.getRole());
        assertEquals("Senior Developer", updatedUser.getRole());
        assertNotSame(originalUser, updatedUser);
    }
}
