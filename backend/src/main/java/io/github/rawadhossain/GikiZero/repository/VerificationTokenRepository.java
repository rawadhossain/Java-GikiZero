package io.github.rawadhossain.GikiZero.repository;

import io.github.rawadhossain.GikiZero.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
    Optional<VerificationToken> findByToken(String token);
    Optional<VerificationToken> findByIdentifierAndToken(String identifier, String token);
}
