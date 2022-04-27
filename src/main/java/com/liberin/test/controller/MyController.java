package com.liberin.test.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import com.fci.icoreparser.MT940Converter;
import com.liberin.test.entity.Student;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/student")
    public List<Student> getStudents(){

        // FileReader reader = new FileReader(file)
        ColumnPositionMappingStrategy<Student> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Student.class);
        CsvToBean<Student> parser = null;
        try {
            parser = new CsvToBeanBuilder<Student>(new FileReader("files/data.csv"))
                                        .withType(Student.class)
                                        .withMappingStrategy(strategy)
                                        .build();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return parser.parse();

    }
}
