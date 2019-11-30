package com.devbridge.feedback.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDto {

    private Long quizId;

    private Map<String, String> answers = new HashMap<>();
}
