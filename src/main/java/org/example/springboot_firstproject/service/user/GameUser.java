package org.example.springboot_firstproject.service.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

//Soy POJO :-)
@Entity
@Table(name = "users")
public class GameUser {

    @Id
    private Long id;

    private UUID userId;
    private String username;
    private String password;

    public GameUser() {
        this.userId = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
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
