package org.example.springboot_firstproject.service.services;
import org.example.springboot_firstproject.data.access.user.GameUser;
import org.example.springboot_firstproject.data.access.user.GameUserRepository;
import org.example.springboot_firstproject.data.access.user.TokenDTO;
import org.example.springboot_firstproject.data.access.user.UserCredentialsDTO;
import org.example.springboot_firstproject.service.services.user.UserCreationParamsDTO;
import org.example.springboot_firstproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    GameUserRepository gameUserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil = new JwtUtil();

    @Override
    public Iterable<GameUser> findAll() {
        return gameUserRepository.findAll();
    }

    @Override
    public GameUser createUser(UserCredentialsDTO params) {
            GameUser gameUser = new GameUser();
            gameUser.setUsername(params.getUsername());
            gameUser.setPassword(params.getPassword());
            gameUser.setRole("ROLE_USER");
            return gameUserRepository.save(gameUser);
         }

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

    @Override
    public TokenDTO login(UserCredentialsDTO credentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword()
                    )
            );
            String token = jwtUtil.generateToken(credentials.getUsername());
            return new TokenDTO(token, credentials.getUsername());
        } catch (BadCredentialsException ex) {
            throw new RuntimeException("Invalid username or password.");
        }
    }
}
