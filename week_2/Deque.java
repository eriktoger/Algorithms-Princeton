import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node prev;
        Node next;

    }

    private Node first;
    private Node last;
    private int size;

    private class ListIterator implements Iterator<Item> {
        private Node current;

        public ListIterator() {
            if (size == 0) {
                current = null;
            } else {
                current = first;
            }

        }

        public boolean hasNext() {
            return current != null;
        }

        // not supported
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == 0) {
            first = new Node();
            first.item = item;
            last = first;

        } else {
            Node temp = first;
            first = new Node();
            first.item = item;
            first.next = temp;
            temp.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (size == 0) {
            first = new Node();
            first.item = item;
            last = first;

        } else {
            Node temp = last;
            last = new Node();
            last.item = item;
            last.prev = temp;
            temp.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = first.item;

        // drop first
        Node next = first.next;
        if (next != null) {
            next.prev = null;
        }
        first = next;
        size--;

        if (size == 0) {
            first = null;
            last = null;
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = last.item;

        // drop last
        Node prev = last.prev;
        if (prev != null) {
            prev.next = null;
        }
        last = prev;
        size--;

        if (size == 0) {
            first = null;
            last = null;
        }

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);
        for (Integer integer : deque) {
            StdOut.print(integer + " ");
        }
        StdOut.println();
        StdOut.println("Is empty: " + deque.isEmpty());
        StdOut.println("size: " + deque.size());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeFirst());
        StdOut.println(deque.removeLast());
        StdOut.println(deque.removeLast());
        StdOut.println("Is empty: " + deque.isEmpty());
        StdOut.println("size: " + deque.size());
    }

}