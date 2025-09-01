package io.github.rawadhossain.GikiZero.controller;

import io.github.rawadhossain.GikiZero.model.User;
import io.github.rawadhossain.GikiZero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id).orElse(null);
    }

    // Create new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Update onboarding (equivalent to Next.js POST /api/user)
    @PutMapping("/{id}/onboarding")
    public User completeOnboarding(
            @PathVariable String id,
            @RequestParam String name,
            @RequestParam String age,
            @RequestParam String location
    ) {
        return userService.updateOnboarding(id, name, age, location);
    }

    // Delete user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "User deleted with id " + id;
    }
}
