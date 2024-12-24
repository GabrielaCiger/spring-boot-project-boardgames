package org.example.springboot_firstproject.service.services;

import org.example.springboot_firstproject.data.access.GameUserDao;
import org.example.springboot_firstproject.service.user.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    GameUserDao gameUserDao;

    @Override
    public List<GameUser> findAll() {
        return gameUserDao.getAllUsers();
    }

    @Override
    public boolean createUser(GameUser gameUser) {
        return gameUserDao.createUser(gameUser);
    }

    @Override
    public Optional<GameUser> getUserById(String id) {
        return gameUserDao.getUserById(id);
    }

    @Override
    public Optional<GameUser> getUserByUsername(String username) {
        return gameUserDao.getUserByUsername(username);
    }

    @Override
    public boolean deleteUserById(String id) {
       return gameUserDao.deleteUser(id);
    }

    @Override
    public boolean updateUser(GameUser gameUser) {
        return gameUserDao.updateUser(gameUser);
    }
}
