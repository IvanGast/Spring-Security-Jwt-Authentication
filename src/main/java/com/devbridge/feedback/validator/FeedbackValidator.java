package com.devbridge.feedback.validator;

import com.devbridge.feedback.dto.FeedbackDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FeedbackValidator implements Validator {

    @Autowired
    private Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return FeedbackDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validator.validate(target, errors);
        FeedbackDto feedbackDto = (FeedbackDto) target;

        if (feedbackDto.getAnswers().isEmpty()) {
            errors.rejectValue("answers", "NotNull");
        }

        if (errors.hasErrors()) {
            errors.reject("Global");
        }
    }
}
