package com.gla_logistic;


    class DeliveryCheckpoint extends Checkpoint{
        public DeliveryCheckpoint(String id, String loc, double dist, int exp, int act) {
            super(id, loc, dist, exp, act);
        }
        public boolean isCritical(){
            return true;
        }
        public String getType(){
            return "DeliveryCheckpoint";
        }
        public double calculatePenalty() {
            if (!isDelayed()) return 0;
            return (actualDuration-expectedDuration)*2;
        }
    }

