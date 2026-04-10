package com.gla_logistic;

class RouteLinkedList<T extends Checkpoint>{
    class Node{
        T data;
        Node next;
        Node(T data){
            this.data=data;
            this.next=null;
        }
    }
    Node head;
    public void addCheckpoint(T checkpoint){
        Node newNode=new Node(checkpoint);
        if (head ==null){
            head=newNode;
            return;
        }
        Node temp=head;
        while(temp.next!=null) {
            temp=temp.next;
        }
        temp.next=newNode;
    }
    public boolean removeCheckpoint(String checkpointId) {
        if(head==null) return false;
        if(head.data.checkpointId.equals(checkpointId)){
            head=head.next;
            return true;
        }
        Node temp=head;
        while (temp.next!=null){
            if (temp.next.data.checkpointId.equals(checkpointId)){
                temp.next=temp.next.next;
                return true;
            }
            temp=temp.next;
        }
        return false;
    }
    public T findCheckpoint(String checkpointId){
        Node temp=head;
        while (temp!=null) {
            if (temp.data.checkpointId.equals(checkpointId)) {
                return temp.data;
            }
            temp=temp.next;
        }
        return null;
    }
    public double computeTotalDistance() {
        double sum = 0;
        Node temp = head;
        while (temp != null) {
            sum += temp.data.distanceFromLast;
            temp = temp.next;
        }
        return sum;
    }
    public double computeTotalPenalty() {
        double sum = 0;
        Node temp = head;
        while (temp != null) {
            sum += temp.data.calculatePenalty();
            temp = temp.next;
        }
        return sum;
    }
    public boolean checkCritical() {
        boolean hasDelivery=false;
        boolean hasFuel=false;
        Node temp=head;
        while (temp!=null) {
            if (temp.data instanceof DeliveryCheckpoint){
                hasDelivery=true;
            }
            if (temp.data instanceof FuelCheckpoint) {
                hasFuel=true;
            }
            temp=temp.next;
        }

        return hasDelivery && hasFuel;
    }
    public void printRoute(){
        Node temp=head;
        int i=1;
        while(temp!=null){
            Checkpoint c=temp.data;
            String status=c.isDelayed() ? "Delayed" : "On Time";
            System.out.println(i + ". " + c.getType() + " – " +
                    c.locationName + " – " +
                    status + " – Penalty: " +
                    c.calculatePenalty());
            temp=temp.next;
            i++;
        }
    }
}
