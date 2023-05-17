package com.example.xixixi;

public class SurvivedCounter implements Cloneable {
    private int secondsSurvived = 1;
    public SurvivedCounter(){

    }
    public void updateSeconds() {
        this.secondsSurvived +=1;

    }

    public void setSecondsSurvived(int secondsSurvived) {
        this.secondsSurvived = secondsSurvived;
    }

    public int getSecondsSurvived() {
        return secondsSurvived;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SurvivedCounter tmp = new SurvivedCounter();
        return tmp;
    }
}
