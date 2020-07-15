package com.predictions.emojist.services;

import com.predictions.emojist.domain.Emoji;
import com.predictions.emojist.domain.Predictions;
import com.predictions.emojist.repos.EmojiRepo;
import com.predictions.emojist.repos.PredictionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PredictionsService {
    @Autowired
    PredictionsRepo predictionsRepo;
    @Autowired
    EmojiRepo emojiRepo;

    public void addingPredictionToDBWithDependence(Predictions predictionsToDB){
        List<Emoji> finallyEmojiList = new ArrayList<>();
        String[] arrayListKeywords;
        String splitter = ";";
        arrayListKeywords = predictionsToDB.getKeywords().split(splitter);
        for (int i=0; i<arrayListKeywords.length; i++){
            List<Emoji> emojiList =
                    emojiRepo.findAllEmojisByKeywordsPrediction("%".concat(arrayListKeywords[i]).concat("%"));
            if (!emojiList.isEmpty()) finallyEmojiList.addAll(emojiList);
        }

        if (finallyEmojiList.isEmpty()){
            predictionsRepo.save(predictionsToDB);
            return;
        }
        for (int i=0; i<finallyEmojiList.size(); i++){
            predictionsToDB.addEmojiToPrediction(finallyEmojiList.get(i));
        }
        predictionsRepo.save(predictionsToDB);
    }

    public List<Predictions> findAllPredictions(){
        return predictionsRepo.findAll();
    }
}
