package com.predictions.emojist.controllers;

import com.predictions.emojist.domain.Emoji;
import com.predictions.emojist.domain.Predictions;
import com.predictions.emojist.services.EmojiService;
import com.predictions.emojist.services.PredictionsService;
import org.apache.catalina.connector.Response;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AddingToDBController {
    @Autowired
    private EmojiService emojiService;
    @Autowired
    private PredictionsService predictionsService;

    @PostMapping("/add/emoji")
    public int addEmojiWithDependenceToDB(@RequestBody JSONObject entityToDB){
        if (entityToDB.isEmpty()) return Response.SC_NOT_FOUND;
        String encryption = (String)entityToDB.get("encryption");
        String keywords = (String)entityToDB.get("keywords");
        if (encryption.isEmpty() || keywords.isEmpty()) return Response.SC_BAD_REQUEST;
        Emoji emojiToDB = new Emoji(encryption, keywords);
        emojiService.addingEmojiToDBWithDependence(emojiToDB);
        return Response.SC_OK;
    }

    @PostMapping("/add/prediction")
    public int addPredictionWithDependenceToDB(@RequestBody JSONObject entityToDB){
        if (entityToDB.isEmpty()) return Response.SC_NOT_FOUND;
        String keywords = (String)entityToDB.get("keywords");
        String predict = (String)entityToDB.get("predict");
        if (keywords.isEmpty() || predict.isEmpty()) return Response.SC_BAD_REQUEST;
        Predictions predictionToDB = new Predictions(keywords, predict);
        predictionsService.addingPredictionToDBWithDependence(predictionToDB);
        return Response.SC_OK;
    }
}
