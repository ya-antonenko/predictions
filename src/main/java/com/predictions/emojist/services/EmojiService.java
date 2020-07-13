package com.predictions.emojist.services;

import com.predictions.emojist.domain.Emoji;
import com.predictions.emojist.domain.Predictions;
import com.predictions.emojist.repos.EmojiRepo;
import com.predictions.emojist.repos.PredictionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmojiService {
    @Autowired
    EmojiRepo emojiRepo;
    @Autowired
    PredictionsRepo predictionsRepo;

    public void addingEmojiToDBWithDependence(Emoji emojiToDB){
        List<Predictions> predictionsList =
                predictionsRepo.findAllPredictionsByKeywordsEmoji("%".concat(emojiToDB.getKeywords()).concat("%"));
        if (predictionsList.isEmpty()) {
            emojiRepo.save(emojiToDB);
            return;
        }
        for (int i=0; i<predictionsList.size(); i++){
            emojiToDB.addPredictionToEmoji(predictionsList.get(i));
        }
        emojiRepo.save(emojiToDB);
    }

    public Emoji findEmojiByEncryption(String encryption){
        Emoji emoji = emojiRepo.findEmojiByEncryption(encryption);
        return emoji;
    }
}
