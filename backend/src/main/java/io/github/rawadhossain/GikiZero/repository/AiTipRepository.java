package io.github.rawadhossain.GikiZero.repository;

import io.github.rawadhossain.GikiZero.model.AiTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AiTipRepository extends JpaRepository<AiTip, String> {
    List<AiTip> findByUserId(String userId);
    void deleteByUserId(String userId);
}
