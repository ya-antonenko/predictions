package com.predictions.emojist.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Emoji {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String encryption;
    private String keywords;

    public Emoji(String encryption, String keywords) {
        this.encryption = encryption;
        this.keywords = keywords;
    }

    public Emoji(){
        super();
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
}
