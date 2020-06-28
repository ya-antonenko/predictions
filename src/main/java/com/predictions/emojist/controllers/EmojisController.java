package com.predictions.emojist.controllers;


import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmojisController {
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

    @GetMapping()
    public JSONObject mainPage(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("First", "Hello");
        return jsonObject;
    }
}
