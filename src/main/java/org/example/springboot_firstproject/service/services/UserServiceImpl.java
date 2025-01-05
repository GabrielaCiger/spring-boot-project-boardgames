package org.example.springboot_firstproject.service.services;
import org.example.springboot_firstproject.service.user.GameUser;
import org.example.springboot_firstproject.service.user.GameUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    GameUserRepository gameUserRepository;

    @Override
    public Iterable<GameUser> findAll() {
        return gameUserRepository.findAll();
    }

    @Override
    public boolean createUser(GameUser gameUser) {
        //JPA Spring will generate this method automatically
        return gameUserRepository.save(gameUser) != null;
    }

    @Override
    public Optional<GameUser> getUserById(String id) {
        return gameUserRepository.findById(id);
    }

    @Override
    public void deleteUserByUserId(UUID id) {
       gameUserRepository.deleteGameUserByUserId(id);
    }

    @Override
    public int updateUser(String userId, GameUser gameUser) {
        return gameUserRepository.updateGameUserByUserId(gameUser.getUsername(), gameUser.getPassword(), userId);
    }
}
