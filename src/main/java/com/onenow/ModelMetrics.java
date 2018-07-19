package com.onenow;

public class ModelMetrics {


    //Class 0: tested 5, correctly classified 4
    //Class 1: tested 7, correctly classified 5
    //Overall: tested 12, correctly classified 9
    //Accuracy = 0.75
    public static String getAccuracy(Dataset testDataset, PredictionMaximizerInterface predictionMaximizerInterface, String annotation) {
        int y0CorrectCount = 0;
        int y1CorrectCount = 0;
        int y0numTested = 0;
        int y1numTested = 0;
        int numRows = testDataset.getDataRows().size();
        for(int i=0; i<numRows; i++) {
            DataRow row = testDataset.getDataRows().get(i);
            Boolean prediction = predictionMaximizerInterface.getPrediction(row);
            Boolean actualOutput = row.getOutputY();
            // System.out.println("FOR_ROW="+row + " PREDICTED_OUTPUT="+prediction + " VS ACTUAL="+actualOutput);
            if(actualOutput) {
                y1numTested+=1;
                if(prediction){
                    y1CorrectCount+=1;
                }
            }
            if(!actualOutput) {
                y0numTested+=1;
                if(!prediction) {
                    y0CorrectCount+=1;
                }
            }
        }
        int totalTested = y0numTested+y1numTested;
        int accurate = y0CorrectCount+y1CorrectCount;
        double accuracy = (double)accurate/(double)numRows;
        String s = "DATASET_ACCURACY="+ testDataset.getPath() + "\n";
        s = s + "\t" + annotation;
        s = s + "\tClass 0: tested " + y0numTested + ", correctly classified " + y0CorrectCount + "\n";
        s = s + "\tClass 1: tested " + y1numTested + ", correctly classified " + y1CorrectCount + "\n";
        s = s + "\tOverall: tested " + totalTested + ", correctly classified " + accurate + "\n";
        s = s + "\tAccuracy = " + accuracy;
        return s;
    }

}
