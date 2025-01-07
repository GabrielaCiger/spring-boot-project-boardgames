package org.example.springboot_firstproject.data.access.user;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.UUID;

//Soy POJO :-)
@Entity
@Table(name = "users")
public class GameUser {

    //Source : https://www.baeldung.com/jpa-get-auto-generated-id
    //Source 2 : https://www.baeldung.com/jpa-strategies-when-set-primary-key
    //This strategy tells JPA that the database is responsible for generating
    //the primary key using its auto-increment feature.
    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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
