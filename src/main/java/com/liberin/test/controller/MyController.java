package com.liberin.test.controller;

import java.util.Map;

import com.liberin.icoreparser.controller.ParseController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    
    @GetMapping("/mt940")
    public Map<String,Object> toHashMap(@RequestBody String text) {
        
        ParseController controller = new ParseController();
        return controller.convertToMT940(text);
    }
}
