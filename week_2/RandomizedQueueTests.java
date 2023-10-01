import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RandomizedQueueTests {
    @Test
    public void testRandomizedQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        assertEquals(randomizedQueue.isEmpty(), false);
        assertEquals(randomizedQueue.size(), 4);

        for (Integer integer : randomizedQueue) {
            assertEquals(integer > 0, true);
            assertEquals(integer < 5, true);

        }

        randomizedQueue.sample();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();

        assertEquals(randomizedQueue.isEmpty(), true);
        assertEquals(randomizedQueue.size(), 0);

        for (int i = 0; i < 40; i++) {
            randomizedQueue.enqueue(i);
        }
        assertEquals(randomizedQueue.size(), 40);
    }
}
