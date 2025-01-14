package org.example.springboot_firstproject.data.access.user;

public class UseCredentialsDTO {
    private String username;
    private String password;

    public UseCredentialsDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
