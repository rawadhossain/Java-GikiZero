package io.github.rawadhossain.GikiZero.repository;

import io.github.rawadhossain.GikiZero.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByProviderAndProviderAccountId(String provider, String providerAccountId);
}