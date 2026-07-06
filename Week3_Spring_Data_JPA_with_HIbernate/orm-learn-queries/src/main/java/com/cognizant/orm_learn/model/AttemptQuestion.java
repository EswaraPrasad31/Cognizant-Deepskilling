package com.cognizant.orm_learn.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "attempt_question")
public class AttemptQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aq_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "aq_at_id")
    private Attempt attempt;

    @ManyToOne
    @JoinColumn(name = "aq_qu_id")
    private Question question;

    @OneToMany(mappedBy = "attemptQuestion", fetch = FetchType.LAZY)
    private java.util.Set<AttemptOption> attemptOptions;

    public AttemptQuestion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public java.util.Set<AttemptOption> getAttemptOptions() {
        return attemptOptions;
    }

    public void setAttemptOptions(java.util.Set<AttemptOption> attemptOptions) {
        this.attemptOptions = attemptOptions;
    }


    @Override
    public String toString() {
        return "AttemptQuestion [id=" + id + "]";
    }
}
