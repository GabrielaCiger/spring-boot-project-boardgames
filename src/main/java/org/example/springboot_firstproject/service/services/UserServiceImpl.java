package org.example.springboot_firstproject.service.services;
import org.example.springboot_firstproject.service.user.GameUser;
import org.example.springboot_firstproject.service.user.GameUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    GameUserRepository gameUserRepository;

    @Override
    public Iterable<GameUser> findAll() {
        return gameUserRepository.findAll();
    }

    @Override
    public void createUser(GameUser gameUser) { gameUserRepository.save(gameUser); }

    @Override
    public Optional<GameUser> getUserById(int id) { return gameUserRepository.findById(String.valueOf(id)); }

    @Override
    public Optional<GameUser> getUserByUsername(String username) {
        return gameUserRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(int id) {
       gameUserRepository.deleteById(String.valueOf(id));
    }

    @Override
    public void updateUser(int id, String newUsername, String newPassword) {
        Optional<GameUser> userToUpdate = gameUserRepository.findById(String.valueOf(id));
        if (userToUpdate.isPresent()) {
            userToUpdate.get().setUsername(newUsername);
            userToUpdate.get().setPassword(newPassword);
            gameUserRepository.save(userToUpdate.get());
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
