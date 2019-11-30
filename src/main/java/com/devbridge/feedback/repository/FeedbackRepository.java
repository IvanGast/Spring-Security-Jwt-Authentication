package com.devbridge.feedback.repository;

import com.devbridge.feedback.entity.Feedback;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface FeedbackRepository extends Repository<Feedback, Long> {

    @Query("SELECT f FROM Feedback f WHERE f.id = :id")
    Feedback findById(Long id);

    @Query("SELECT f FROM Feedback f WHERE f.quiz.id = :quizId")
    List<Feedback> findByQuizId(Long quizId);

    Feedback save(Feedback feedback);

    void delete(Feedback feedback);
}
