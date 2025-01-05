package org.example.springboot_firstproject.service.services;

import org.example.springboot_firstproject.service.user.GameUser;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Iterable<GameUser> findAll();
    boolean createUser(GameUser gameUser);
    Optional<GameUser> getUserById(String id);
    void deleteUserByUserId(UUID id);
    int updateUser(String userId, GameUser gameUser);
}
