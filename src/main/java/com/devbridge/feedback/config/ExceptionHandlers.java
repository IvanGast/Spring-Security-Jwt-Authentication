package com.devbridge.feedback.config;

import com.devbridge.feedback.exception.ServerError;
import com.devbridge.feedback.exception.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlers {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = ValidationException.class)
    @ResponseBody
    public ServerError handleValidationException(HttpServletResponse response, ValidationException ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        String header = messageSource.getMessage(ex.getErrors().getGlobalError(), Locale.getDefault());
        List<String> items = new ArrayList<>();
        for (FieldError fieldError : ex.getErrors().getFieldErrors()) {
            items.add(messageSource.getMessage(fieldError, Locale.getDefault()));
        }

        return new ServerError(header, items);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ServerError handleIllegalArgumentException(HttpServletResponse response, IllegalArgumentException ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        String header = ex.getMessage();
        return new ServerError(header, new ArrayList<>());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerError handleGeneralException(HttpServletResponse response, Exception ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        String header = messageSource.getMessage("serverError", null, Locale.getDefault());
        return new ServerError(header, new ArrayList<>());
    }
}
