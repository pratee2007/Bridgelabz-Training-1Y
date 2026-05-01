package list;

import checkpoint.Checkpoint;
import checkpoint.DeliveryCheckpoint;
import checkpoint.FuelCheckpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic singly linked list that stores checkpoints of type T.
 * T must be a subtype of Checkpoint.
 */
public class RouteLinkedList<T extends Checkpoint> {

    // ── Inner Node ───────────────────────────────────
    private static class Node<T> {
        T       data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // ── Fields ───────────────────────────────────────
    private Node<T> head;
    private int     size;

    // ── Core Operations ──────────────────────────────

    /** Appends a checkpoint to the end of the list. */
    public void addCheckpoint(T checkpoint) {
        if (checkpoint == null) throw new IllegalArgumentException("Checkpoint cannot be null.");
        Node<T> newNode = new Node<>(checkpoint);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = newNode;
        }
        size++;
    }

    /** Inserts a checkpoint at a specific 0-based index. */
    public void addCheckpointAt(T checkpoint, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index: " + index);
        Node<T> newNode = new Node<>(checkpoint);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> cur = head;
            for (int i = 0; i < index - 1; i++) cur = cur.next;
            newNode.next = cur.next;
            cur.next = newNode;
        }
        size++;
    }

    /** Removes the checkpoint with the given ID. Returns true if found and removed. */
    public boolean removeCheckpoint(String checkpointId) {
        if (head == null) return false;
        if (head.data.getCheckpointId().equals(checkpointId)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> cur = head;
        while (cur.next != null) {
            if (cur.next.data.getCheckpointId().equals(checkpointId)) {
                cur.next = cur.next.next;
                size--;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /** Finds and returns a checkpoint by ID, or null if not found. */
    public T findCheckpoint(String checkpointId) {
        Node<T> cur = head;
        while (cur != null) {
            if (cur.data.getCheckpointId().equals(checkpointId)) return cur.data;
            cur = cur.next;
        }
        return null;
    }

    /** Returns all checkpoints as a list (for iteration). */
    public List<T> getAllCheckpoints() {
        List<T> result = new ArrayList<>();
        Node<T> cur = head;
        while (cur != null) {
            result.add(cur.data);
            cur = cur.next;
        }
        return result;
    }

    /** Total route distance in km. */
    public double computeTotalDistance() {
        double  total = 0;
        Node<T> cur   = head;
        while (cur != null) {
            total += cur.data.getDistanceFromLast();
            cur = cur.next;
        }
        return total;
    }

    /** Total penalty across all checkpoints. */
    public double computeTotalPenalty() {
        double  total = 0;
        Node<T> cur   = head;
        while (cur != null) {
            total += cur.data.calculatePenalty();
            cur = cur.next;
        }
        return total;
    }

    /** Route score = totalDistance - totalPenalty */
    public double computeRouteScore() {
        return computeTotalDistance() - computeTotalPenalty();
    }

    /**
     * Consistency check: at least one DeliveryCheckpoint and one FuelCheckpoint must exist.
     * Returns false if any critical type is missing.
     */
    public boolean isConsistent() {
        boolean hasDelivery = false;
        boolean hasFuel     = false;
        Node<T> cur         = head;
        while (cur != null) {
            if (cur.data instanceof DeliveryCheckpoint) hasDelivery = true;
            if (cur.data instanceof FuelCheckpoint)     hasFuel     = true;
            cur = cur.next;
        }
        return hasDelivery && hasFuel;
    }

    /** Count how many checkpoints are delayed. */
    public int countDelayed() {
        int     count = 0;
        Node<T> cur   = head;
        while (cur != null) {
            if (cur.data.isDelayed()) count++;
            cur = cur.next;
        }
        return count;
    }

    /** Prints every checkpoint with a 1-based index. */
    public void printRoute() {
        Node<T> cur = head;
        int     idx = 1;
        while (cur != null) {
            System.out.println("  " + idx++ + ". " + cur.data);
            cur = cur.next;
        }
    }

    public int     getSize()  { return size; }
    public boolean isEmpty()  { return size == 0; }
    public T       getFirst() { return head == null ? null : head.data; }
}
