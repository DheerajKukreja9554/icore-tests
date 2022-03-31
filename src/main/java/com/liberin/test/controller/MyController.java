package com.liberin.test.controller;

import java.io.FileWriter;
import java.util.Map;

import com.liberin.icoreparser.controller.ParseController;

import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    
    @PostMapping("/mt940")
    public Map<String,Object> toHashMap(@RequestBody String text) {
        
        ParseController controller = new ParseController();
        Map<String,Object> answer = controller.convertToMT940(text);

        try (FileWriter writer = new FileWriter("./"+answer.get("file name"))) {
            writer.write(answer.get("MT940 message").toString());
        } catch (Exception e) {
            //TODO: handle exception
        }
        return answer;
    }
}
