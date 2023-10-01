import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            randomizedQueue.enqueue(word);
        }
        for (String word : randomizedQueue) {
            if (size == 0) {
                break;
            }

            StdOut.println(word);
            size--;
        }

    }
}
