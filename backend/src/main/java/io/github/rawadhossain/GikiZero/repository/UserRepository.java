package io.github.rawadhossain.GikiZero.repository;

import io.github.rawadhossain.GikiZero.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // Custom query methods:
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}