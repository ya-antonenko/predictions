package com.predictions.emojist.controllers;


import com.predictions.emojist.domain.Emoji;
import com.predictions.emojist.services.EmojiService;
import com.predictions.emojist.services.PredictionsService;
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

    private final int quantityEmojiToPredict = 5;


    /*** Prediction generation ***/

    @PostMapping("/predictions")
    public JSONObject predictionsToJson(@RequestBody JSONObject emojisFromClient){
        JSONObject jsonObjectResult = new JSONObject();
        if (emojisFromClient.isEmpty()){
            jsonObjectResult.put("massage","Json is empty");
            return jsonObjectResult;
        }

        List<String> finallyPredictionToClient = new ArrayList<>();

        for (int i=0; i<quantityEmojiToPredict; i++) {
            String emojiEncryption = (String) emojisFromClient.get("encryption".concat(Integer.toString(i)));
            String onePredictionToClient = generatedOnePredictToClient(emojiEncryption);
            if (onePredictionToClient == null ||
                    finallyPredictionToClient.contains(onePredictionToClient)) continue;
            finallyPredictionToClient.add(onePredictionToClient);
        }

        String finallyPredict = new String();

        if (finallyPredictionToClient.isEmpty()){
            jsonObjectResult.put("massage","Предсказание по данным емоджи не найдено");
            return jsonObjectResult;
        }else {

            for (int i=0; i<finallyPredictionToClient.size(); i++){
                if (i == finallyPredictionToClient.size() - 1)
                    finallyPredict.concat(finallyPredictionToClient.get(i)).concat(".");
                finallyPredict.concat(finallyPredictionToClient.get(i)).concat("; ");
            }
        }
        jsonObjectResult.put("massage", finallyPredict);
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
}
