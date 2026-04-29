public class RouteLinkedList<T extends Checkpoint> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> head;
    private int size;

    public void addCheckpoint(T cp) {
        Node<T> node = new Node<>(cp);
        if (head == null) {
            head = node;
            size++;
            return;
        }

        Node<T> cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = node;
        size++;
    }

    public boolean removeCheckpoint(String id) {
        if (head == null) return false;
        if (head.data.getCheckpointId().equals(id)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> cur = head;
        while (cur.next != null) {
            if (cur.next.data.getCheckpointId().equals(id)) {
                cur.next = cur.next.next;
                size--;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public T findCheckpoint(String id) {
        Node<T> cur = head;
        while (cur != null) {
            if (cur.data.getCheckpointId().equals(id)) return cur.data;
            cur = cur.next;
        }
        return null;
    }

    public double computeTotalDistance() {
        double total = 0;
        Node<T> cur = head;
        while (cur != null) {
            total += cur.data.getDistanceFromLast();
            cur = cur.next;
        }
        return total;
    }

    public double computeTotalPenalty() {
        double total = 0;
        Node<T> cur = head;
        while (cur != null) {
            total += cur.data.calculatePenalty();
            cur = cur.next;
        }
        return total;
    }

    public boolean isConsistent() {
        boolean d = false;
        boolean f = false;
        Node<T> cur = head;
        while (cur != null) {
            if (cur.data instanceof DeliveryCheckpoint) d = true;
            if (cur.data instanceof FuelCheckpoint) f = true;
            cur = cur.next;
        }
        return d && f;
    }

    public void printRoute() {
        Node<T> cur = head;
        int i = 1;
        while (cur != null) {
            System.out.println(i++ + ". " + cur.data);
            cur = cur.next;
        }
    }

    public int getSize() { return size; }
}
