package io.github.rawadhossain.GikiZero.repository;

import io.github.rawadhossain.GikiZero.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String> {
    List<Submission> findByUser_IdOrderByCreatedAtDesc(String userId);
    List<Submission> findByUser_IdAndCreatedAtAfterOrderByCreatedAtDesc(String userId, LocalDateTime date);
}
