package org.example.springboot_firstproject.service.services;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserCreationParamsDTO {
@NotNull(message = "User can't be null.")
@NotBlank(message = "User can't be blank.")
private String userId;

    private String userName;
    private String password;

    public UserCreationParamsDTO(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getPassword() { return password; }
}
