package com.gla_logistic;
    abstract class Checkpoint {
        String checkpointId;
        String locationName;
        double distanceFromLast;
        double expectedDuration;
        double actualDuration;

        public Checkpoint(String id, String location, double distance,
                          double expected, double actual) {
            this.checkpointId = id;
            this.locationName = location;
            this.distanceFromLast = distance;
            this.expectedDuration = expected;
            this.actualDuration = actual;
        }

        public boolean isDelayed() {
            return actualDuration > expectedDuration;
        }

        public abstract boolean isCritical();
        public abstract String getType();
        public abstract double calculatePenalty();
    }

