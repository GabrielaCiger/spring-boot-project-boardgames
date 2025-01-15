package org.example.springboot_firstproject.service.services.user;

import org.example.springboot_firstproject.data.access.user.GameUser;
import org.example.springboot_firstproject.data.access.user.GameUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private GameUserRepository gameUserRepository;
    
    public UserDetailsServiceImpl() {}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<GameUser> gameUser = gameUserRepository.findByUsername(username);
        return gameUser.map(UserDetailModel::new).orElseThrow(()->new UsernameNotFoundException("Invalid username."));
    }
}
