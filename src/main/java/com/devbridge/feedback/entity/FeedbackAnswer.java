package com.devbridge.feedback.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedback_answer")
public class FeedbackAnswer {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "question_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Question question;

    @JoinColumn(name = "answer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Answer answer;

    @Column(name = "text")
    private String text;
}
