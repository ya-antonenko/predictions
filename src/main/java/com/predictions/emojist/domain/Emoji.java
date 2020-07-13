package com.predictions.emojist.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Emoji {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String encryption;
    private String keywords;
    @ManyToMany
    @JoinTable(name = "prediction_dependence",
            joinColumns = {@JoinColumn(name = "emoji_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "prediction_id", referencedColumnName = "id")})
    private List<Predictions> predictionsList = new ArrayList<>();

    public Emoji(String encryption, String keywords) {
        this.encryption = encryption;
        this.keywords = keywords;
    }

    public Emoji() {
        super();
    }

    public void addPredictionToEmoji(Predictions predictions){
        if (!predictionsList.contains(predictions)) predictionsList.add(predictions);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public List<Predictions> getPredictionsList() {
        return predictionsList;
    }

    public void setPredictionsList(List<Predictions> predictionsList) {
        this.predictionsList = predictionsList;
    }
}
