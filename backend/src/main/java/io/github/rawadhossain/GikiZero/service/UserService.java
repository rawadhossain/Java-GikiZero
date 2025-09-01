package io.github.rawadhossain.GikiZero.service;

import io.github.rawadhossain.GikiZero.model.User;
import io.github.rawadhossain.GikiZero.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateOnboarding(String id, String name, String age, String location) {
        return userRepository.findById(id).map(user -> {
            user.setName(name.trim());
            user.setAge(age.trim());
            user.setLocation(location.trim());
            user.setOnboardingCompleted(true);
            user.setUpdatedAt(java.time.LocalDateTime.now());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
