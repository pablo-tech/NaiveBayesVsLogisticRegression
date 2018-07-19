package com.onenow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dataset {

    private String path;
    private Integer numInputVariables;
    private Integer numDataRows;
    private List<DataRow> dataRows;

    public Dataset(String path) {
        this.path = path;
        this.numInputVariables = null;
        this.numDataRows = null;
        this.dataRows = new ArrayList<>();
        // System.out.println("ADDED_DATASET="+path);
    }

    public void addDataRow(DataRow dataRow) throws IOException {
        if(dataRow.getInputX().size()!=this.numInputVariables) {
            throw new IOException("WRONG_LENGTH_FOR_INPUT_DATA: is=" + dataRow.getInputX().size() + " should=" + this.numInputVariables);
        }
        this.dataRows.add(dataRow);
    }

    public List<DataRow> getDataRows() {
        return dataRows;
    }

    public Boolean getInputX(int rowNum, int colNum) {
        return dataRows.get(rowNum).getInputX(colNum);
    }
    public Boolean getOutput(int rowNum) {
        return dataRows.get(rowNum).getOutputY();
    }

    public Boolean isComplete() throws IOException {
        Boolean isComplete = dataRows.size()==this.numDataRows;
        if(!isComplete) {
            throw new IOException("INCOMPLETE_DATASET: is="+this.dataRows.size() + " should="+this.numDataRows);
        }
        return isComplete;
    }

    public void setNumInputVariables(Integer numInputVariables) {
        this.numInputVariables = numInputVariables;
    }

    public void setNumDataRows(Integer numDataRows) {
        this.numDataRows = numDataRows;
    }

    public String getPath() {
        return path;
    }

    public String toString() {
        String s = "";
        try {
            s = s + "*** SUMMARY = " + isComplete().toString() + "\n";
            s = s + "-path=" + path + "\n";
            s = s + "-numInputVariables=" + numInputVariables + "\n";
            s = s + "-numDataRows=" + numDataRows + "\n";
            s = s + "-numActualRows=" + dataRows.size() + "\n";
            int rowNum=0;
            int colNum=0;
            s = s + "-row0col0=(x,y)=("+ getInputX(rowNum,colNum)+","+getOutput(rowNum)+")";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
