package com.predictions.emojist.controllers;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmojisController {
    @PostMapping("/predictions")
    public JSONObject predictionsToJson(JSONObject emojis){
        String emoji = (String) emojis.get("emojis");
        JSONObject jsonObjectResult = new JSONObject();
        jsonObjectResult.put("emojis", emoji.concat(" + result..."));
        return jsonObjectResult;
    }
    @GetMapping()
    public JSONObject mainPage(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("First", "Hello");
        return jsonObject;
    }
}
