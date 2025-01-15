package org.example.springboot_firstproject.data.access.user;

import jakarta.validation.constraints.*;

public class UserCredentialsDTO {

    @NotNull(message = "Username can't be null.")
    @NotBlank(message = "Username can't be blank.")
    @Size(min = 1, max = 20, message = "Username must be at least 1 and at maximum 20 character long. ")
    private String username;

    @NotNull(message = "Password can't be null.")
    @NotBlank(message = "Password can't be blank.")
    //https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one uppercase letter and one number.")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    private String password;

    public UserCredentialsDTO(String username, String password) {
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
