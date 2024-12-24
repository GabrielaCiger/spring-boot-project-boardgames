package org.example.springboot_firstproject.service.services;

import org.example.springboot_firstproject.service.user.GameUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<GameUser> findAll();
    boolean createUser(GameUser gameUser);
    Optional<GameUser> getUserById(String id);
    Optional<GameUser> getUserByUsername(String username);
    boolean deleteUserById(String id);
    boolean updateUser(GameUser gameUser);
}
