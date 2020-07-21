package com.predictions.emojist.controllers;

import com.predictions.emojist.domain.Emoji;
import com.predictions.emojist.domain.Predictions;
import com.predictions.emojist.services.EmojiService;
import com.predictions.emojist.services.PredictionsService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddingToDBController {
    @Autowired
    private EmojiService emojiService;
    @Autowired
    private PredictionsService predictionsService;

    //      *** initialization entity to DB ***

    @PostMapping("/add/emoji")
    public HttpStatus addEmojiWithDependenceToDB(@RequestBody JSONObject entityToDB){
        if (entityToDB.isEmpty()) return HttpStatus.NOT_FOUND;
        String encryption = (String)entityToDB.get("encryption");
        String keywords = (String)entityToDB.get("keywords");
        if (encryption.isEmpty() || keywords.isEmpty()) return HttpStatus.BAD_REQUEST;
        Emoji emojiToDB = new Emoji(encryption, keywords);
        emojiService.addingEmojiToDBWithDependence(emojiToDB);
        return HttpStatus.NO_CONTENT;
    }

    @PostMapping("/add/prediction")
    public HttpStatus addPredictionWithDependenceToDB(@RequestBody JSONObject entityToDB){
        if (entityToDB.isEmpty()) return HttpStatus.NOT_FOUND;
        String keywords = (String)entityToDB.get("keywords");
        String predict = (String)entityToDB.get("predict");
        if (keywords.isEmpty() || predict.isEmpty()) return HttpStatus.BAD_REQUEST;
        Predictions predictionToDB = new Predictions(keywords, predict);
        predictionsService.addingPredictionToDBWithDependence(predictionToDB);
        return HttpStatus.NO_CONTENT;
    }
}
