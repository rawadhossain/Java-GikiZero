package io.github.rawadhossain.GikiZero.repository;

import io.github.rawadhossain.GikiZero.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, String> {
    List<Question> findByCategoryAndIsActiveTrue(String category);
    List<Question> findByIsActiveTrue();
}
