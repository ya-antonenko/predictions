package com.predictions.emojist.controllers;


import com.predictions.emojist.domain.Emoji;
import com.predictions.emojist.domain.Predictions;
import com.predictions.emojist.services.EmojiService;
import com.predictions.emojist.services.PredictionsService;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class EmojisController {

    @Autowired
    EmojiService emojiService;
    @Autowired
    PredictionsService predictionsService;

    private final int quantityEmojis = 5;


    /*** Prediction generation ***/

    @PostMapping("/predictions")
    public JSONObject predictionsToJson(@RequestBody JSONObject emojisFromClient){
        JSONObject jsonObjectResult = new JSONObject();
        if (emojisFromClient.isEmpty()){
            jsonObjectResult.put("massage","Json is empty");
            return jsonObjectResult;
        }

        List<String> finallyPredictionToClient = new ArrayList<>();
        List<String> emojiEncryption = (List<String>) emojisFromClient.get("emojis");

        for (int i=0; i<emojiEncryption.size(); i++) {
            String onePredictionToClient = generatedOnePredictToClient(emojiEncryption.get(i));
            if (onePredictionToClient == null) continue;
            if (finallyPredictionToClient.contains(onePredictionToClient)){
                i--;
                continue;
            }
            finallyPredictionToClient.add(onePredictionToClient);
        }

        if (finallyPredictionToClient.isEmpty()){
            jsonObjectResult.put("massage",StringUtils.join(generateRandomPrediction(), " "));
            return jsonObjectResult;
        }

        jsonObjectResult.put("massage", StringUtils.join(finallyPredictionToClient, " "));
        return jsonObjectResult;
    }

    public String generatedOnePredictToClient(String emojiEncryption){
        Emoji emojiToPredict = emojiService.findEmojiByEncryption(emojiEncryption);
        if (emojiToPredict == null) return null;
        Random random = new Random();
        String onePredictionToClient = emojiToPredict.getPredictionsList().
                get(random.nextInt(emojiToPredict.getPredictionsList().size())).getPredict();
        return onePredictionToClient;
    }

    public List<String> generateRandomPrediction(){
        List<Predictions> predictionsList = predictionsService.findAllPredictions();
        List<String> finallyPredictionsToClient = new ArrayList<>();
        Random random = new Random();
        for (int i=0; i < quantityEmojis; i++){
            String predict = predictionsList.get(random.nextInt(predictionsList.size())).getPredict();
            if (!finallyPredictionsToClient.contains(predict))
                finallyPredictionsToClient.add(predict);
            else {
                i--;
                continue;
            }
        }
        return finallyPredictionsToClient;
    }
}
