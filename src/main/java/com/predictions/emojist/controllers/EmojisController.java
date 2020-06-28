package com.predictions.emojist.controllers;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmojisController {
    @PostMapping("/predictions")
    public JSONObject predictionsToJson(@RequestParam("emojis") String emojis){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(emojis, Math.random());
        return jsonObject;
    }
    @GetMapping()
    public JSONObject mainPage(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("First", "Hello");
        return jsonObject;
    }
}
