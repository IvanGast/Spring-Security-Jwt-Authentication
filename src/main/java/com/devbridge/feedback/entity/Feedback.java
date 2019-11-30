package com.devbridge.feedback.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "quiz_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Quiz quiz;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @JoinColumn(name = "feedback_id", referencedColumnName = "id", nullable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy(value = "question_id")
    @Valid
    private List<FeedbackAnswer> feedbackAnswers = new ArrayList<>();
}
