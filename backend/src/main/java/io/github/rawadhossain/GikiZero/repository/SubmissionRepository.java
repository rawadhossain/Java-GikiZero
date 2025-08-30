package io.github.rawadhossain.GikiZero.repository;

import io.github.rawadhossain.GikiZero.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String> {
    // Get all submissions by user
    List<Submission> findByUserIdOrderByCreatedAtDesc(String userId);

    // Get submissions since a certain date
    List<Submission> findByUserIdAndCreatedAtAfterOrderByCreatedAtDesc(String userId, Date date);
}