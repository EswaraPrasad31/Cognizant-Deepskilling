package com.cognizant.orm_learn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "options")
public class Options {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "op_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "op_qu_id")
    private Question question;

    @Column(name = "op_text")
    private String text;

    @Column(name = "op_score")
    private java.math.BigDecimal score;

    public Options() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public java.math.BigDecimal getScore() {
        return score;
    }

    public void setScore(java.math.BigDecimal score) {
        this.score = score;
    }


    @Override
    public String toString() {
        return "Options [id=" + id + ", text=" + text + ", score=" + score + "]";
    }
}
