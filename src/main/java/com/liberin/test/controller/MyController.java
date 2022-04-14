package com.liberin.test.controller;

import java.io.FileWriter;
import java.util.Map;

import com.fci.icoreparser.MT940Converter;

// import com.fci.icoreparser.controller.ParseController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    
    @PostMapping("/mt940")
    public Map<String,Object> toHashMap(@RequestBody String text) {
        
        
        Map<String,Object> answer = MT940Converter.convertToMT940(text);

        try (FileWriter writer = new FileWriter("./"+answer.get("file name"))) {
            System.out.println("writing");
            writer.write(answer.get("MT940 message").toString());
        } catch (Exception e) {
            //TODO: handle exception
        }
        return answer;
    }
}
