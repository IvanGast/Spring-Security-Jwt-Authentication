package com.devbridge.feedback.controller;

import com.devbridge.feedback.Application;
import com.devbridge.feedback.dto.FeedbackDto;
import com.devbridge.feedback.entity.Feedback;
import com.devbridge.feedback.exception.ValidationException;
import com.devbridge.feedback.repository.FeedbackRepository;
import com.devbridge.feedback.service.FeedbackService;
import com.devbridge.feedback.validator.FeedbackValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Application.PATH)
public class FeedbackController {

    private FeedbackService feedbackService;

    private FeedbackRepository feedbackRepository;

    private FeedbackValidator feedbackValidator;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, FeedbackRepository feedbackRepository, FeedbackValidator feedbackValidator) {
        this.feedbackService = feedbackService;
        this.feedbackRepository = feedbackRepository;
        this.feedbackValidator = feedbackValidator;
    }

    @RequestMapping(value = "/secured/feedback/quiz/{quizId}", method = RequestMethod.GET, produces = "application/json")
    public List<Feedback> getFeedbackById(@PathVariable(value = "quizId") Long quizId) {
        return feedbackRepository.findByQuizId(quizId);
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Feedback saveFeedback(@RequestBody FeedbackDto feedbackDto) {
        validate(feedbackDto, "feedbackDto", feedbackValidator);
        return feedbackService.saveFeedback(feedbackDto);
    }

    @RequestMapping(value = "/secured/feedback/{id}", method = RequestMethod.DELETE)
    public void deleteFeedback(@PathVariable(value = "id") Long id) {
        Feedback feedback = feedbackRepository.findById(id);
        feedbackRepository.delete(feedback);
    }

    protected void validate(FeedbackDto feedbackDto, String entityName, Validator validator) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(feedbackDto, entityName);
        validator.validate(feedbackDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
    }
}
