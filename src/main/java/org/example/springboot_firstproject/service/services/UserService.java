package org.example.springboot_firstproject.service.services;

import org.example.springboot_firstproject.data.access.user.GameUser;
import org.example.springboot_firstproject.data.access.user.TokenDTO;
import org.example.springboot_firstproject.data.access.user.UserCredentialsDTO;
import org.example.springboot_firstproject.service.services.user.UserCreationParamsDTO;

import java.util.Optional;

public interface UserService {
    Iterable<GameUser> findAll();
    GameUser createUser(UserCredentialsDTO params);
    TokenDTO login(UserCredentialsDTO credentials);
    Optional<GameUser> getUserById(int id);
    Optional<GameUser> getUserByUsername(String username);
    void deleteUserById(int id);
    void updateUser(int id, String newUsername, String newPassword);
}
