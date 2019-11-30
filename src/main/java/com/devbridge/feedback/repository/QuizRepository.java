package com.devbridge.feedback.repository;

import com.devbridge.feedback.entity.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface QuizRepository extends Repository<Quiz, Long> {

    @Query("SELECT q FROM Quiz q WHERE q.id = :id")
    Quiz findById(Long id);

    @Query("SELECT q FROM Quiz q WHERE q.code = :code")
    Quiz findByCode(String code);

    Quiz save(Quiz quiz);

    void delete(Quiz quiz);
}
