package com.onenow;

public class Qtable {

    private String name;
    private Integer id;

    // row is input variable i; colums are values the random variable takes
    private Double[][] value;

    public Qtable() {
        this.name = "";
        this.id = null;
        this.value = new Double[2][2]; // input and output are indicator variables
    }

    public Qtable(String name, int inputColumnId) {
        this.name = name;
        this.id = inputColumnId;
        this.value = new Double[2][2]; // input and output are indicator variables
    }

    public void setCountXzeroYzero(Double count) {
        this.value[0][0] = count;
    }
    public void setCountXoneYzero(Double count) {
        this.value[0][1] = count;
    }
    public void setCountXzeroYone(Double count) {
        this.value[1][0] = count;
    }
    public void setCountXoneYone(Double count) {
        this.value[1][1] = count;
    }

    public Double getValueXzeroYzero() {
        return this.value[0][0];
    }
    public Double getValueXoneYzero() {
        return this.value[0][1];
    }
    public Double getValueXzeroYone() {
        return this.value[1][0];
    }
    public Double getValueXoneYone() {
        return this.value[1][1];
    }

    public String toString() {
        String s = "QTABLE column#"+id;
        if(!name.equals("")) {
            s = s + " FROM " + name;
        }
        s = s + "\n";
        s = s + "\t(x=0,y=0)"+ getValueXzeroYzero() + "\n";
        s = s + "\t(x=1,y=0)"+ getValueXoneYzero() + "\n";
        s = s + "\t(x=0,y=1)"+ getValueXzeroYone() + "\n";
        s = s + "\t(x=1,y=1)"+ getValueXoneYone();
        return s;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
