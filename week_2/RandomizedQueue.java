import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // construct an empty randomized queue
    private Item[] items;
    private int size;

    public RandomizedQueue() {
        int initialSize = 32;
        items = (Item[]) new Object[initialSize];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;

    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        items[size] = item;
        size++;
        int currentLength = items.length;
        if (size >= currentLength) {
            Item[] newItems = (Item[]) new Object[currentLength * 2];
            for (int i = 0; i < currentLength; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }

    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int randomIndex = StdRandom.uniformInt(0, size);
        Item item = items[randomIndex];

        // plug the hole with the last element
        size--;
        items[randomIndex] = items[size];
        items[size] = null;

        // downsize
        if (size <= items.length / 4) {
            int newSize = items.length / 2;
            Item[] newItems = (Item[]) new Object[newSize];
            for (int i = 0; i < newSize; i++) {
                newItems[i] = items[i];
            }
            items = newItems;
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniformInt(0, size);
        return items[randomIndex];
    }

    // return an independent iterator over items in random order

    private class ListIterator implements Iterator<Item> {
        private Item[] iteratorItems;
        private int currentIndex;

        public ListIterator() {
            iteratorItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                iteratorItems[i] = items[i];
            }
            StdRandom.shuffle(iteratorItems);
        }

        public boolean hasNext() {
            return currentIndex < iteratorItems.length;
        }

        // not supported
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = iteratorItems[currentIndex];
            currentIndex++;
            return item;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        StdOut.println("Is empty: " + randomizedQueue.isEmpty());
        StdOut.println("size: " + randomizedQueue.size());
        for (Integer integer : randomizedQueue) {
            StdOut.print(integer + " ");
        }
        StdOut.println();
        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.dequeue());
        StdOut.println(randomizedQueue.dequeue());
        StdOut.println(randomizedQueue.dequeue());
        StdOut.println(randomizedQueue.dequeue());
        StdOut.println("Is empty: " + randomizedQueue.isEmpty());
        StdOut.println("size: " + randomizedQueue.size());

    }

}