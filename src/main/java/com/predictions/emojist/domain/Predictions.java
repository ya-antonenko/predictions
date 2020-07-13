package com.predictions.emojist.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Predictions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String keywords;
    private String predict;
    @ManyToMany(mappedBy = "predictionsList", cascade = CascadeType.ALL)
    private List<Emoji> emojiList = new ArrayList<>();


    public Predictions(String keywords, String predict) {
        this.keywords = keywords;
        this.predict = predict;
    }

    public Predictions() {
        super();
    }

    public void addEmojiToPrediction(Emoji emoji){
        if (!emoji.getPredictionsList().contains(this))
            emoji.getPredictionsList().add(this);
        if (!emojiList.contains(emoji)) emojiList.add(emoji);
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

    public List<Emoji> getEmojiList() {
        return emojiList;
    }

    public void setEmojiList(List<Emoji> emojiList) {
        this.emojiList = emojiList;
    }
}
