package com.devbridge.feedback.controller;

import com.devbridge.feedback.Application;
import com.devbridge.feedback.entity.Quiz;
import com.devbridge.feedback.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Application.PATH)
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @RequestMapping(value = "/quiz/{code}", method = RequestMethod.GET, produces = "application/json")
    public Quiz getQuizByCode(@PathVariable(value = "code") String code) {
        return quizRepository.findByCode(code);
    }

    @RequestMapping(value = "/secured/quiz", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Quiz saveQuiz(@RequestBody Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @RequestMapping(value = "/secured/quiz/{id}", method = RequestMethod.DELETE)
    public void deleteQuiz(@PathVariable(value = "id") Long id) {
        Quiz quiz = quizRepository.findById(id);
        quizRepository.delete(quiz);
    }
}
