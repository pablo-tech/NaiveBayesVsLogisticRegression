package com.onenow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public static List<String> read(String filePath) {

        List<String> lines = new ArrayList<>();

        // System.out.println("FILE_PATH="+filePath);

        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(filePath));

            while ((line = br.readLine()) != null) {
                // System.out.println("DATA_LINE=" + line);
                lines.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return lines;
    }
}
