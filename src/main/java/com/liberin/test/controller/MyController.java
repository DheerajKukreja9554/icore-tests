package com.liberin.test.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.fci.icoreparser.MT940Converter;
import com.fci.icoreparser.entity.ICoreMessage;
import com.fci.icoreparser.service.ParserServiceImpl;
import com.fci.icoreparser.swiftfin.SwiftFinParser;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/random")
    public void random(){

        String text = "";
        String file = "D:\\PROGRAMMING\\Liberin\\ICOREPARSER PROJECT\\icore-parser\\files\\Input files\\mt940_lst\\mt940_lst\\00470501559520220403.txt";

        try {
            text =new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        StopWatch watch = new StopWatch();
        watch.start();
        ICoreMessage message = new ParserServiceImpl().createICoreMessage(text);
        watch.stop();
        System.out.println(message.getBody().size());
        System.out.println(watch.getTime());

        watch.reset();
        watch.start();
        String doc = new SwiftFinParser().createMT940Document(message);
        watch.stop();
        System.out.println("documented in "+ watch.getTime());

        try (FileWriter writer = new FileWriter("random.swt1")) {
            writer.write(text);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
