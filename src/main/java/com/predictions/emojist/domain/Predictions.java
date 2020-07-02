package com.predictions.emojist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Predictions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String keywords;
    private String predict;

    public Predictions(String keywords, String predict) {
        this.keywords = keywords;
        this.predict = predict;
    }

    public Predictions(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPredict() {
        return predict;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }
}
