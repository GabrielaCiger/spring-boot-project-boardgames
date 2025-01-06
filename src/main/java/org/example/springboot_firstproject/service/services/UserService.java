package org.example.springboot_firstproject.service.services;

import org.example.springboot_firstproject.service.user.GameUser;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Iterable<GameUser> findAll();
    void createUser(GameUser gameUser);
    Optional<GameUser> getUserById(int id);
    Optional<GameUser> getUserByUsername(String username);
    void deleteUserById(int id);
    void updateUser(int id, String newUsername, String newPassword);
}
