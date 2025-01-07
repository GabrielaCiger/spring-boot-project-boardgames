package org.example.springboot_firstproject.data.access;
import org.example.springboot_firstproject.data.access.user.GameUser;
import java.util.List;
import java.util.Optional;

public interface GameUserDao {
    List<GameUser> getAllUsers();
    Optional<GameUser> getUserById(int id);
    Optional<GameUser> getUserByUsername(String username);
    boolean createUser(GameUser user);
    boolean updateUser(GameUser user);
    boolean deleteUser(int id);
}
