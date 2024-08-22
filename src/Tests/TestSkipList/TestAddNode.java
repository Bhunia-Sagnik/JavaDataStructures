package Tests.TestSkipList;

import DataStructures.skipList.SkipList;
import java.util.Collection;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestAddNode {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {10, true},
            {50, true},
            {51, true},
            {4, true},
            {8, true}, 
            {8, true}
        });
    }

    private int key;
    private boolean expected;
    public TestAddNode(int key, boolean expected) {
        this.key = key;
        this.expected = expected;
    }

    private static SkipList skiplist;
    @BeforeClass
    public static void set() {
        skiplist = new SkipList();
    }

    @Test
    public void testAddNode() {
        skiplist.addNode(key);
        assertEquals(expected, skiplist.containsNode(key));
    }
}
