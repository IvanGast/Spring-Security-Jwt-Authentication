package com.devbridge.feedback.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Getter
@Setter
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length = 50)
    @NotNull
    @Size(max = 50)
    private String code;

    @Column(name = "title", nullable = false)
    @NotNull
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "active", nullable = false)
    @NotNull
    private Boolean active;

    @JoinColumn(name = "quiz_id", referencedColumnName = "id", nullable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    @OrderBy(value = "id")
    @Valid
    private List<Question> questions;
}
