package com.onenow;

import java.util.ArrayList;
import java.util.List;

public class DataRow {

    private List<Boolean> inputX;
    private Boolean outputY;

    public DataRow(String inputX, String outputY) {
        this.inputX = getInputX(inputX);
        this.outputY = getOutputY(outputY);
        // System.out.println("FEATURES_SPLIT=" + inputX);
        // System.out.println("SUPERVISOR_SPLIT=" + outputY);
    }

    public List<Boolean> getInputX() {
        return inputX;
    }

    public Boolean getInputX(int inputVarNum) {
        return inputX.get(inputVarNum);
    }

    public List<Boolean> getInputX(String inputXstring) {
        String[] array = inputXstring.split("");
        List<Boolean> inputXbool = new ArrayList<>();
        for(String str: array) {
            if(str.equals("1")) {
                inputXbool.add(true);
            } else {
                inputXbool.add(false);
            }
        }
        //System.out.println("TURNED_INPUT="+inputXstring+"INTO_OUTPUT="+inputXbool);
        return inputXbool;
    }

    public Boolean getOutputY() {
        return new Boolean(outputY);
    }

    public Boolean getOutputY(String outputString) {
        if(outputString.equals("1")) {
            return true;
        }
        return false;
    }

    public String toString() {
        String s = "";
        s = s + inputX + ":" + outputY;
        return s;
    }

}
