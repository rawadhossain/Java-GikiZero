package io.github.rawadhossain.GikiZero.repository;

import io.github.rawadhossain.GikiZero.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
    Optional<Session> findBySessionToken(String sessionToken);
}
