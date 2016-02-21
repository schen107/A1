/**
 * JUnit Test Suite for given List data structure, based on the ADT from OpenDSA.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class ListTest {

    static CList<Integer> c;
    static final int[] values = {1, 2, 3, 4, 5};

    @BeforeClass
    public static void init() {
        c = new CList<Integer>();
        //lstr = new CList<String>();
    }

    @Before
    public void setup() { // dependent on clear being good
        c.clear();
        for (int i = 0; i < values.length; i++) {
            c.append(values[i]); // assume append works
        }
        //lstr.clear();
    }

    @Test
    public void testClearEmptyList() {
        c.clear();
        assertEquals(0, c.length()); // assume length works
    }

    @Test
    public void testClearNonEmptyList() {
        assertNotSame(0, c.length()); // assume length works
        c.clear();
        assertEquals(0, c.length());
    }

    
    @Test
    public void testInsertEmptyList() {
        c.clear();
        assertEquals(0, c.length());
        c.insert(6);
        assertEquals(0, c.currPos());
        assertEquals(1, c.length());
        assertEquals(6, (int) c.getValue());
    }


    @Test
    public void testInsertAtFront() {
        assertEquals(0, c.currPos()); // assume currPos works
        assertEquals(values.length, c.length());
        c.insert(6);
        assertEquals(0, c.currPos());
        assertEquals(values.length + 1, c.length());
        assertEquals(6, (int) c.getValue()); // assume getValue works
    }

    @Test
    public void testInsertAtMiddle() {
        c.moveToPos(3);
        assertEquals(3, c.currPos());
        assertEquals(values.length, c.length());
        c.insert(6);
        assertEquals(3, c.currPos());
        assertEquals(values.length + 1, c.length());
        assertEquals(6, (int) c.getValue());
    }

    @Test
    public void testInsertAtEnd() {
        c.moveToPos(c.length() - 1);
        assertEquals(c.length() - 1, c.currPos());
        assertEquals(values.length, c.length());
        c.insert(6);
        assertEquals(c.length() - 2, c.currPos());
        assertEquals(values.length + 1, c.length());
        assertEquals(6, (int) c.getValue());
    }

    @Test
    public void testAppendEmptyList() {
        c.clear();
        assertEquals(0, c.length());
        c.append(6);
        assertEquals(0, c.currPos());
        assertEquals(1, c.length());
        assertEquals(6, (int) c.getValue());
    }

    @Test
    public void testAppendAtFront() {
        assertEquals(0, c.currPos()); // assume currPos works
        assertEquals(values.length, c.length());
        c.append(6);
        assertEquals(0, c.currPos());
        assertEquals(values.length + 1, c.length());
        assertEquals(1, (int) c.getValue()); // assume getValue works
    }

    @Test
    public void testAppendAtMiddle() {
        c.moveToPos(3);
        assertEquals(3, c.currPos());
        assertEquals(values.length, c.length());
        c.append(6);
        assertEquals(3, c.currPos());
        assertEquals(values.length + 1, c.length());
        assertEquals(4, (int) c.getValue());
    }

    @Test
    public void testAppendAtEnd() {
        c.moveToPos(c.length() - 1);
        assertEquals(c.length() - 1, c.currPos());
        assertEquals(c.length(), values.length);
        c.append(6);
        assertEquals(c.length() - 2, c.currPos());
        assertEquals(values.length + 1, c.length());
        assertEquals(5, (int) c.getValue());
    }

    @Test
    public void testRemoveEmptyList() {
        c.clear();
        assertEquals(0, c.length());
        assertNull(c.remove());
        assertEquals(0, c.length());
    }


    @Test
    public void testRemoveAtFront() {
        assertEquals(0, c.currPos()); // assume currPos works
        assertEquals(values.length, c.length());
        assertEquals(1, (int) c.remove());
        assertEquals(0, c.currPos());
        assertEquals(values.length - 1, c.length());
        assertEquals(2, (int) c.getValue());
    }

    @Test
    public void testRemoveAtMiddle() {
        c.moveToPos(3);
        assertEquals(3, c.currPos());
        assertEquals(values.length, c.length());
        assertEquals(4, (int) c.remove());
        assertEquals(3, c.currPos());
        assertEquals(values.length - 1, c.length());
        assertEquals(5, (int) c.getValue());
    }

    @Test
    public void testRemoveAtEnd() {
        c.moveToPos(c.length() - 1);
        assertEquals(c.length() - 1, c.currPos());
        assertEquals(values.length, c.length());
        assertEquals(5, (int) c.remove());
        assertEquals(0, c.currPos());
        assertEquals(values.length - 1, c.length());
        assertEquals(1, (int) c.getValue());
    }

    @Test
    public void testMoveToStartEmptyList() {
        c.clear();
        c.moveToStart();
        assertEquals(0, c.currPos());
    }

    @Test
    public void testMoveToStartFromStart() {
        assertEquals(0, c.currPos());
        c.moveToStart();
        assertEquals(0, c.currPos());
    }   
    
    @Test
    public void testMoveToStartFromMiddle() {
        c.moveToPos(3);
        assertEquals(3, c.currPos());
        c.moveToStart();
        assertEquals(0, c.currPos());
    }

    @Test
    public void testMoveToEndEmptyList() {
        c.clear();
        c.moveToEnd();
        assertEquals(0, c.currPos());
    }

    @Test
    public void testMoveToEndFromEnd() {
        assertEquals(0, c.currPos());
        c.moveToPos(c.length() - 1);
        assertEquals(c.length() - 1, c.currPos());
        c.moveToEnd();
        assertEquals(c.length() - 1, c.currPos());
    }
    
    @Test
    public void testMoveToEndFromMiddle() {
        c.moveToPos(3);
        assertEquals(3, c.currPos());
        c.moveToEnd();
        assertEquals(c.length() - 1, c.currPos());
    }

    @Test
    public void testPrevEmptyList() {
        c.clear();
        c.prev();
        assertNull(c.getValue());
    }

    @Test
    public void testPrevAtStart() {
        assertEquals(0, c.currPos());
        c.prev();
        assertEquals(0, c.currPos());
    }

    @Test
    public void testPrevInMiddle() {
        c.moveToPos(3);
        assertEquals(3, c.currPos());
        c.prev();
        assertEquals(2, c.currPos());
    }

    @Test
    public void testNextEmptyList() {
        c.clear();
        c.next();
        assertNull(c.getValue());
    }

    @Test
    public void testNextAtEnd() {
        c.moveToPos(c.length() - 1);
        assertEquals(c.length() - 1, c.currPos());
        c.next();
        assertEquals(c.length() - 1, c.currPos());
    }

    @Test
    public void testNextInMiddle() {
        c.moveToPos(3);
        assertEquals(3, c.currPos());
        c.next();
        assertEquals(4, c.currPos());
    }

    @Test
    public void testLength() {
        assertEquals(5, c.length());
        c.clear();
        assertEquals(0, c.length());
    }

    @Test
    public void testCurrPosEmptyList() {
        c.clear();
        assertEquals(0, c.currPos());
    }

    @Test
    public void testCurrPos() {
        assertEquals(0, c.currPos());
        c.prev();
        assertEquals(0, c.currPos());
        c.next();
        assertEquals(1, c.currPos());
    }

    @Test
    public void testMoveToPosEmptyList() {
        c.clear();
        assertFalse(c.moveToPos(1));
    }

    @Test
    public void testMoveToPosOutOfBounds() {
        assertEquals(0, c.currPos());
        assertFalse(c.moveToPos(10));
    }   
    
    @Test
    public void testMoveToPos() {
        assertEquals(0, c.currPos());
        assertTrue(c.moveToPos(3));
        assertEquals(3, c.currPos());
        assertTrue(c.moveToPos(1));
        assertEquals(1, c.currPos());
    }

    @Test
    public void testIsAtEndTrue() {
        assertEquals(0, c.currPos());
        c.moveToEnd();
        assertTrue(c.isAtEnd());
    }

    @Test
    public void testIsAtEndFalse() {
        assertEquals(0, c.currPos());
        assertFalse(c.isAtEnd());
    }

    @Test
    public void testGetValueEmptyList() {
        c.clear();
        assertEquals(0, c.length());
        assertNull(c.getValue());
    }

    @Test
    public void testGetValue() {
        assertEquals(0, c.currPos());
        assertEquals(1, (int) c.getValue());
    }
}
