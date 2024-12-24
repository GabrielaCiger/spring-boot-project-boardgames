package org.example.springboot_firstproject.presentation;
import org.example.springboot_firstproject.service.services.UserService;
import org.example.springboot_firstproject.service.user.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public Collection<GameUser> getAllUsers() {
        return userService.findAll();
    }
    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody GameUser user) {
        try {
           boolean isCreated = userService.createUser(user);
           if (isCreated) {
               return ResponseEntity.ok(user.toString());
           } else {
               return ResponseEntity.badRequest().build();
           }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }
    @GetMapping("/{userId}")
    public Object getUser(@PathVariable String userId) {
        try {
            Optional<GameUser> matchById = userService.getUserById(userId);
            Optional<GameUser> matchByUsername = userService.getUserByUsername(userId);
            if (matchById.isPresent() || matchByUsername.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + userId);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteGame(@PathVariable String userId) {
        try {
            boolean isDeleted = userService.deleteUserById(userId);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User successfully deleted.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + userId);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<String> updateGame(@PathVariable String userId, @RequestBody GameUser user) {
        try {
           boolean updated = userService.updateUser(user);
           if (updated) {
               return ResponseEntity.ok(user.toString());
           } else {
               return ResponseEntity.badRequest().build();
           }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }
}
