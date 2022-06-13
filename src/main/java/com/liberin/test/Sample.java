package com.liberin.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fci.icoreparser.MT940Converter;

public class Sample {
    public static void main(String[] args) {
        new Sample().writeFiles();
    }
    // @Test
    void writeFiles() {
        List<File> filedirectory = Arrays.asList(new File("files\\Input files\\mt940lst25042022").listFiles());
        String outputDirectory = "files\\calculatedMT940\\mt940lst25042022\\";
        Track track = new Track();
        for (File file : filedirectory) {

            
            try {
                writeFile(file, outputDirectory);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println(track.count);

    }

    class Track {
        int count;

        Track() {
            count = 0;
        }

        void increaseCount() {
            this.count += 1;
        }
    }

    // @Test
    void writeFile(File file, String outputDirectory) throws IOException {

        String text = "";
        System.out.println(file.getName());
        text = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        Map<String, Object> map = MT940Converter.convertToMT940(text);
        if (map.get("MT940 message") == null) {
            System.out.println(map.get("error message").toString());
            return;
        }
        FileWriter writer = new FileWriter(outputDirectory + map.get("file name"));
        writer.write(map.get("MT940 message").toString());
        writer.close();

    }

}
