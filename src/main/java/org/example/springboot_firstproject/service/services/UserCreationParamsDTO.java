package org.example.springboot_firstproject.service.services;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserCreationParamsDTO {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    private String password;

    public UserCreationParamsDTO(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
