package jsp.Spring.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jsp.Spring.Entity.User;
import jsp.Spring.Repository.UserRepository;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/addUser")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        
        if (userRepository.existsByUsername(user.getUsername())) {
            response.put("Status", "Error");
            response.put("message", "Username already exists");
            return ResponseEntity.badRequest().body(response);
        }
        
        User savedUser = userRepository.save(user);
        response.put("Status", "Success");
        response.put("userId", savedUser.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/queryUser")
    public ResponseEntity<?> queryUser(@RequestBody Map<String, Object> searchCriteria) {
        return ResponseEntity.ok(userRepository.findAll());
    }
}
