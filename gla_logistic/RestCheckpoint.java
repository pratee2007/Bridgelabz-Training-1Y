package com.gla_logistic;


class RestCheckpoint extends Checkpoint{
    public RestCheckpoint(String id,String loc,double dist,int exp,int act){
        super(id, loc, dist, exp, act);
    }
    public boolean isCritical(){
        return false;
    }
    public String getType(){
        return "RestCheckpoint";
    }
    public double calculatePenalty(){
        if (!isDelayed()) return 0;
        return (actualDuration - expectedDuration)*0.5;
    }
}