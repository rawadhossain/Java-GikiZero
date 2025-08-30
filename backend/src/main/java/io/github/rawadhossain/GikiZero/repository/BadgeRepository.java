package io.github.rawadhossain.GikiZero.repository;

import io.github.rawadhossain.GikiZero.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, String> {
    boolean existsByName(String name);
}