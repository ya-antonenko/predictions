package com.predictions.emojist.controllers;


import com.predictions.emojist.domain.Emoji;
import com.predictions.emojist.services.EmojiService;
import com.predictions.emojist.services.PredictionsService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmojisController {

    @Autowired
    EmojiService emojiService;
    @Autowired
    PredictionsService predictionsService;

    @PostMapping("/predictions")
    public JSONObject predictionsToJson(@RequestBody JSONObject emojis){
        JSONObject jsonObjectResult = new JSONObject();
        if (emojis.isEmpty()){
            jsonObjectResult.put("error","Json is empty");
            return jsonObjectResult;
        }
        String emoji = (String) emojis.get("emojis");
        jsonObjectResult.put("emojis", emoji.concat(" + result..."));
        return jsonObjectResult;
    }
}
