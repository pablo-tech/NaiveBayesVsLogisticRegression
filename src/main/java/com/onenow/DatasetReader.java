package com.onenow;

import com.onenow.util.folder.FileHandling;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DatasetReader {


    private static HashMap<String, Dataset> dataSets;


    public DatasetReader(String path) {
        this.dataSets = new HashMap<>();

        try {
            this.dataSets = read(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dataset get(String name, boolean isTest) {
        for(Dataset set: dataSets.values()) {
            if(set.getPath().contains(name)) {
                if(isTest && set.getPath().contains("test")) {
                    return set;
                }
                if(!isTest && set.getPath().contains("train")) {
                    return set;
                }
            }
        }
        return null;
    }

    public HashMap<String, Dataset> read(String path) throws IOException {

        File folder = new File(path);

        List<File> files = FileHandling.listFilesForFolder(folder);

        for(File file:files) {
            Dataset dataSet = new Dataset(file.getPath());
            int i=0;
            for(String line:DataReader.read(file.getPath())) {
                String cleanLine = line.replace(" ", "");
                if(i==0) {
                    dataSet.setNumInputVariables(new Integer(cleanLine));
                }
                if(i==1) {
                    dataSet.setNumDataRows(new Integer(cleanLine));
                }
                if(getFirstSplit(cleanLine).length==2) {  // data rows
                    String[] featuresAndClass = getFirstSplit(cleanLine);
                    DataRow dataRow = new DataRow(featuresAndClass[0], featuresAndClass[1]);
                    dataSet.addDataRow(dataRow);
                }
                i++;
            }
            // System.out.println(dataSet.toString());
            dataSets.put(dataSet.getPath(), dataSet);
        }

        return dataSets;
    }

    private String[] getFirstSplit(String line) {
        return line.split(":");
    }
    private String[] getSecondSplit(String line) {
        return line.split(" ");
    }

}
