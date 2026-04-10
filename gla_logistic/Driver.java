package com.gla_logistic;

class Driver{
    String driverId;
    String name;
    RouteLinkedList<Checkpoint> routeHistory;
    public Driver(String id,String name){
        this.driverId=id;
        this.name=name;
        this.routeHistory=new RouteLinkedList<>();
    }
    public void printSummary(){
        System.out.println("Driver: " + driverId + " – " + name);
        System.out.println("Route Summary:");
        routeHistory.printRoute();
        double totalDistance=routeHistory.computeTotalDistance();
        double totalPenalty=routeHistory.computeTotalPenalty();
        double routeScore=totalDistance - totalPenalty;
        System.out.println("Total Distance: " + totalDistance + " km");
        System.out.println("Total Penalty: " + totalPenalty);
        System.out.println("Route Score: " + routeScore);
        boolean ok = routeHistory.checkCritical();
        System.out.println("Critical Route Check: " +
                (ok ? "All required checkpoints present" : "Missing critical checkpoints"));
    }
}