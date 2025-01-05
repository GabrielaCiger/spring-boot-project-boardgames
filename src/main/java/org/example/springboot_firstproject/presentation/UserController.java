package org.example.springboot_firstproject.presentation;
import org.example.springboot_firstproject.service.services.UserCreationParamsDTO;
import org.example.springboot_firstproject.service.services.UserService;
import org.example.springboot_firstproject.service.user.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public Iterable<GameUser> getAllUsers() {
        return userService.findAll();
    }
    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody @Validated UserCreationParamsDTO params) {
        try {
           GameUser user = new GameUser();
           user.setUsername(params.getUserName());
           user.setPassword(params.getPassword());
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
    @GetMapping("/{id}")
    public Object getUser(@PathVariable UUID id) {
        try {
            Optional<GameUser> matchById = userService.getUserById(id.toString());
            if (matchById.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        try {
            userService.deleteUserByUserId(id);
            boolean isNotDeleted = userService.getUserById(id.toString()).isPresent();
            if (!isNotDeleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User successfully deleted.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody GameUser user) {
        try {
           int updated = userService.updateUser(userId, user);
           if (updated > 0) {
               return ResponseEntity.ok(user.toString());
           } else {
               return ResponseEntity.badRequest().build();
           }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }
}
