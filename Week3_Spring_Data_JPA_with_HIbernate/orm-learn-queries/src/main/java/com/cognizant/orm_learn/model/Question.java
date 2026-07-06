package com.cognizant.orm_learn.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qu_id")
    private int id;

    @Column(name = "qu_text")
    private String text;

    @Column(name = "qu_score")
    private java.math.BigDecimal score;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private java.util.Set<Options> optionsList;

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public java.util.Set<Options> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(java.util.Set<Options> optionsList) {
        this.optionsList = optionsList;
    }


    @Override
    public String toString() {
        return "Question [id=" + id + ", text=" + text + ", score=" + score + "]";
    }
}
