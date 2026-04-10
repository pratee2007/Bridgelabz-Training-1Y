package com.gla_logistic;

public class Main{
    public static void main(String[] args){
        Driver d=new Driver("D1204", "Kavita Nair");
        d.routeHistory.addCheckpoint(
                new DeliveryCheckpoint("C1", "Warehouse A", 30, 20, 30));
        d.routeHistory.addCheckpoint(
                new FuelCheckpoint("C2", "Pump 12", 20, 15, 15));
        d.routeHistory.addCheckpoint(
                new RestCheckpoint("C3", "Motel X", 10, 30, 35));
        d.routeHistory.addCheckpoint(
                new DeliveryCheckpoint("C4", "Client Hub", 60, 25, 40));
        d.printSummary();
    }
}