package com.predictions.emojist.services;

import com.predictions.emojist.domain.Emoji;
import com.predictions.emojist.domain.Predictions;
import com.predictions.emojist.repos.EmojiRepo;
import com.predictions.emojist.repos.PredictionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionsService {
    @Autowired
    PredictionsRepo predictionsRepo;
    @Autowired
    EmojiRepo emojiRepo;

    public void addingPredictionToDBWithDependence(Predictions predictionsToDB){
        List<Emoji> emojiList = emojiRepo.findAllEmojisByKeywordsPrediction(predictionsToDB.getKeywords());
        if (emojiList.isEmpty()){
            predictionsRepo.save(predictionsToDB);
            return;
        }
        for (int i=0; i<emojiList.size(); i++){
            predictionsToDB.addEmojiToPrediction(emojiList.get(i));
        }
        predictionsRepo.save(predictionsToDB);
    }
}
