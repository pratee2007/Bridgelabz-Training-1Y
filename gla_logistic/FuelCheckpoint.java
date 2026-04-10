package com.gla_logistic;

class FuelCheckpoint extends Checkpoint{
    public FuelCheckpoint(String id,String loc,double dist,int exp,int act) {
        super(id,loc,dist,exp,act);
    }
    public boolean isCritical(){
        return true;
    }
    public String getType(){
        return "FuelCheckpoint";
    }
    public double calculatePenalty(){
        return isDelayed() ? 10 : 0;
    }
}