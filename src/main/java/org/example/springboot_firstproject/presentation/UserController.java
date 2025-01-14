package org.example.springboot_firstproject.presentation;
import org.example.springboot_firstproject.service.services.user.UserCreationParamsDTO;
import org.example.springboot_firstproject.service.services.UserService;
import org.example.springboot_firstproject.data.access.user.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;



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
    public ResponseEntity<?> createUser(@RequestBody @Validated UserCreationParamsDTO params) {
        try {
           GameUser user = new GameUser();
           user.setUsername(params.getUsername());
           user.setPassword(params.getPassword());
           userService.createUser(user);
           return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }
    @GetMapping("/by-id/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        try {
            Optional<GameUser> matchById = userService.getUserById(id);
            if (matchById.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(matchById.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            Optional<GameUser> matchByUsername = userService.getUserByUsername(username);
            if (matchByUsername.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(matchByUsername.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + username);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUserById(id);
            boolean isNotDeleted = userService.getUserById(id).isPresent();
            if (!isNotDeleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User successfully deleted.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody String[] toUpdate) {
        try {
            userService.updateUser(id, toUpdate[0], toUpdate[1]);
            Optional<GameUser> user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unknown error : " + e.getMessage());
        }
    }

}
