package Tests.TestSkipList;

import DataStructures.skipList.SkipList;
import DataStructures.skipList.IllegalKeyValueException;
import DataStructures.skipList.NodeNotFoundException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.Arrays;
import java.util.Collection;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class TestRemoveNode {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {10, true, false}, // exception unexpected
            {50, true, false}, // exception expected
            {51, false, true}, // exception unexpected
            {4, true, false}, // exception expected
            {8, false, true} // exception expected
        });
    }

    private int key;
    private boolean expected;
    private boolean exceptionExpected;
    private static SkipList skiplist;

    // Constructor to initialize parameters
    public TestRemoveNode(int key, boolean expected, boolean exceptionExpected) {
        this.key = key;
        this.expected = expected;
        this.exceptionExpected = exceptionExpected;
    }

    @BeforeClass
    public static void set() {
        skiplist = new SkipList();
        skiplist.addNode(1);
        skiplist.addNode(5);
        skiplist.addNode(4);
        skiplist.addNode(3);
        skiplist.addNode(2);
        skiplist.addNode(16);
        skiplist.addNode(7);
        skiplist.addNode(21);
        skiplist.addNode(10);
        skiplist.addNode(50);
        skiplist.addNode(30);
    }

    @Test
    public void testRemoveNode() {
        try {
            if(key == Integer.MIN_VALUE || key == Integer.MAX_VALUE) {
                throw new IllegalKeyValueException("key value cant be negative or positive infinity");
            }

            if(exceptionExpected) {
                try {
                    skiplist.removeNode(key); // if does not throw exception then fail
                    fail("Expected keyNotFoundException for key: " + key);
                }
                catch(NodeNotFoundException e) {
                    assertTrue(true);
                }
            }
            else {
                try {
                    assertEquals(expected, skiplist.removeNode(key));
                }
                catch(NodeNotFoundException e) {
                    System.out.println(e.toString());
                    System.out.println(key + "cant be removed as it is not present in skip list");
                    e.printStackTrace();
                }
            }
        }
        catch(IllegalKeyValueException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
