package com.devbridge.feedback.service;

import com.devbridge.feedback.dto.FeedbackDto;
import com.devbridge.feedback.entity.Feedback;
import com.devbridge.feedback.entity.FeedbackAnswer;
import com.devbridge.feedback.entity.Quiz;
import com.devbridge.feedback.repository.FeedbackRepository;
import com.devbridge.feedback.repository.QuizRepository;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private QuizRepository quizRepository;

    public Feedback saveFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = convertDtoToEntity(feedbackDto);
        return feedbackRepository.save(feedback);
    }

    private Feedback convertDtoToEntity(FeedbackDto feedbackDto) {
        Quiz quiz = quizRepository.findById(feedbackDto.getQuizId());

        Feedback feedback = new Feedback();
        feedback.setDate(LocalDate.now());
        feedback.setQuiz(quiz);

        for (Map.Entry<String, String> entry : feedbackDto.getAnswers().entrySet()) {
            FeedbackAnswer feedbackAnswer = new FeedbackAnswer();
            feedbackAnswer.setQuestion(quiz.getQuestions().stream()
                .filter(q -> q.getId().equals(Long.parseLong(entry.getKey().substring("question-".length()))))
                .findFirst()
                .orElse(null));

            switch (feedbackAnswer.getQuestion().getType()) {
                case RADIO:
                    feedbackAnswer.setAnswer(quiz.getQuestions().stream()
                        .flatMap(q -> q.getAnswers().stream())
                        .filter(a -> a.getId().equals(Long.parseLong(entry.getValue())))
                        .findFirst()
                        .orElse(null));
                    break;
                case SHORT:
                case LONG:
                    feedbackAnswer.setText(entry.getValue());
                    break;
            }

            feedback.getFeedbackAnswers().add(feedbackAnswer);
        }

        return feedback;
    }
}
