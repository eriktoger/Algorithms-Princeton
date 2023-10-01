import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DequeTests {
    @Test
    public void testDeque() {
        // Your test code for addition
        Deque<Integer> deque = new Deque<Integer>();
        assertEquals(deque.isEmpty(), true);

        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);

        int[] ints = new int[4];
        int counter = 0;
        for (Integer integer : deque) {
            ints[counter] = integer;
            counter++;

        }
        assertEquals(ints[0], 1);
        assertEquals(ints[1], 2);
        assertEquals(ints[2], 3);
        assertEquals(ints[3], 4);

        assertEquals(deque.isEmpty(), false);

        assertEquals(deque.removeFirst(), (Integer) 1);
        assertEquals(deque.removeFirst(), (Integer) 2);
        assertEquals(deque.removeLast(), (Integer) 4);
        assertEquals(deque.removeLast(), (Integer) 3);
        assertEquals(deque.isEmpty(), true);

        int counter2 = 0;
        for (Integer integer : deque) {
            counter2++;

        }
        assertEquals(counter2, 0);

    }

}
