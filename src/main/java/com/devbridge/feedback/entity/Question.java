package com.devbridge.feedback.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "question")
public class Question {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    @NotNull
    private String text;

    @Column(name = "type", nullable = false, length = 50)
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private QuestionType type;

    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy(value = "id")
    @Valid
    private List<Answer> answers = new ArrayList<>();
}
