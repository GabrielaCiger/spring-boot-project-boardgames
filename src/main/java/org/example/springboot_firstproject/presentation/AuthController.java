package org.example.springboot_firstproject.presentation;

import org.example.springboot_firstproject.data.access.user.GameUser;
import org.example.springboot_firstproject.data.access.user.TokenDTO;
import org.example.springboot_firstproject.data.access.user.UserCredentialsDTO;
import org.example.springboot_firstproject.service.services.UserService;
import org.example.springboot_firstproject.service.services.user.UserCreationParamsDTO;
import org.example.springboot_firstproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    public AuthController() {}

    @PostMapping("/login")
    public TokenDTO login(@RequestBody @Validated UserCredentialsDTO userCredentialsDTO) {
        return userService.login(userCredentialsDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Validated UserCredentialsDTO params) {
        try {
            GameUser user = userService.createUser(params);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

}
