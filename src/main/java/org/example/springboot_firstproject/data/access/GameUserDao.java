package org.example.springboot_firstproject.data.access;
import org.example.springboot_firstproject.service.user.GameUser;

import java.util.List;
import java.util.UUID;

public interface GameUserDao {
    public List<GameUser> getAllUsers();
    public GameUser getUserById(UUID id);
    public GameUser getUserByUsername(String username);
    public GameUser createUser(GameUser user);
    public GameUser updateUser(GameUser user);
    public void deleteUser(int id);
}
