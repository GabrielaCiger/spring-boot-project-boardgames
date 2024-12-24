package org.example.springboot_firstproject.service.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

//Soy POJO :-)
//@Entity
public class GameUser {

//    @Id
    private UUID id;
    private String username;
    private String password;

    public GameUser() {
        this.id = UUID.randomUUID();
    }

    private UUID createUniqueId() {
        return UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
