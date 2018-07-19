package com.onenow;

import java.util.HashMap;
import java.util.List;

/**
 * For every value of an input variable, we count the number of occurances of an output value
 */
public class CounterBayesian {

    private Dataset dataset;

    protected HashMap<Integer, Qtable> inputVariableValueToOutputVariableValueCountTables;
    protected HashMap<Boolean, Integer> outputVariableValueCountTable;


    public CounterBayesian(Dataset dataset) {
        this.dataset = dataset;
        this.inputVariableValueToOutputVariableValueCountTables = new HashMap<>();
        this.outputVariableValueCountTable = new HashMap<>();
    }

    // output value count
    protected HashMap<Boolean, Integer> countOutputVar() {
        for(DataRow row:this.dataset.getDataRows()) { // all columns
            if(row.getOutputY()) {
                countOutputCase(true, outputVariableValueCountTable); // output 1
            } else {
                countOutputCase(false, outputVariableValueCountTable); // output 0
            }
        }
        return outputVariableValueCountTable;
    }

    private static void countOutputCase(boolean outputCase, HashMap<Boolean, Integer> outputVariableValueCountTable) {
        Integer currentValue = outputVariableValueCountTable.get(outputCase);
        if(currentValue==null) {
            currentValue = 0;
            outputVariableValueCountTable.put(outputCase, currentValue); // zero count initialization
        }
        outputVariableValueCountTable.put(outputCase, currentValue+1); // zero count initialization
    }

    // input->output respective value table count
    protected HashMap<Integer, Qtable> countInputVarToOutput() {
        int numInputVars = dataset.getDataRows().get(0).getInputX().size();
        for(int inputVarId=0; inputVarId<numInputVars; inputVarId++) { // all columns
            Qtable inputVarTable = new Qtable(dataset.getPath(), inputVarId);
            inputVariableValueToOutputVariableValueCountTables.put(inputVarId, inputVarTable);
            inputVarTable.setCountXzeroYzero(getCount(inputVarId, false, false, dataset));
            inputVarTable.setCountXoneYzero(getCount(inputVarId, true, false, dataset));
            inputVarTable.setCountXzeroYone(getCount(inputVarId, false, true, dataset));
            inputVarTable.setCountXoneYone(getCount(inputVarId, true, true, dataset));
            System.out.println(inputVarTable);
        }
        return inputVariableValueToOutputVariableValueCountTables;
    }

    private static double getCount(int inputXcolunn, boolean inputXvalue, boolean outputYvalue, Dataset dataset) {
        double count = 0;
        List<DataRow> datasetRows = dataset.getDataRows();
        for(int i=0; i<datasetRows.size(); i++) { // row datapoints
            DataRow dataRow = datasetRows.get(i);
            boolean actualInput = dataRow.getInputX(inputXcolunn);
            boolean actualOutput = dataRow.getOutputY();
            if(actualInput==inputXvalue && actualOutput==outputYvalue) { // matches output
                count++;
            }
        }
        return count;
    }

    protected int getTotalRowCount() {
        return this.outputVariableValueCountTable.get(false) + this.outputVariableValueCountTable.get(true);
    }

    protected Dataset getDataset() {
        return dataset;
    }
}
