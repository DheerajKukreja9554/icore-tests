package com.liberin.test.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.codehaus.plexus.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fci.icoreparser.MT940Converter;

@RestController
public class MyController {
    
    @Autowired
    TransactionsDao daolayer;


    @PostMapping("/mt940")
    public Map<String,Object> toHashMap(@RequestBody String text) {
        
        
        Map<String,Object> answer = MT940Converter.convertToMT940(text);

        try (FileWriter writer = new FileWriter("./"+answer.get("file name"))) {
            System.out.println("writing");
            writer.write(answer.get("MT940 message").toString());
        } catch (Exception e) {
        }
        return answer;
    }

    @PostMapping("/train")
    public void fillData(@RequestBody String text) throws SerialException, SQLException {
        writeFiles();
    }

    void writeFiles() throws SerialException, SQLException {
        List<File> filedirectory = Arrays.asList(new File("files\\18052022").listFiles());
        
        for (File file : filedirectory) {
            try {
                writeFile(file);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }


    void writeFile(File file) throws IOException, SerialException, SQLException {

        String text = "";
        System.out.println(file.getName());
        text = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        Map<String, Object> map = MT940Converter.convertToMT940(text);
        if (map.get("MT940 message") == null) {
            System.out.println(map.get("error message").toString());
            return;
        }
        MT940Transactions transactions = new MT940Transactions();
        transactions.setFilename(map.get("file name").toString());
        transactions.setData(new SerialClob(map.get("MT940 message").toString().toCharArray()));
        daolayer.save(transactions);   

        
        
    }
}
        
