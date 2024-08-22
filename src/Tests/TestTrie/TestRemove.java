/* This test case check if the remove method is working properly */

package Tests.TestTrie;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import DataStructures.trie.Trie;
import DataStructures.trie.Trie.WordNotFoundException;

@RunWith(Parameterized.class)
public class TestRemove {
    @Parameters 
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {"hello!!", false, false}, // exception unexpected
            {"hell", false, false}, // exception unexpected
            {"shout..", true, true}, // exception expected
            {"Has@tings", false, false}, // exception unexpected
            {"share__", false, false}, // exception unexpected
            {"sha+re", true, true}, // exception expected
            {"happy", true, true} // exception expected
        });
    }

    private String word;
    private boolean expected;
    private boolean exceptionExpected;
    private static Trie trie;

    // Constructor to initialize parameters
    public TestRemove(String word, boolean expected, boolean exceptionExpected) {
        this.word = word;
        this.expected = expected;
        this.exceptionExpected = exceptionExpected;
    }


    @BeforeClass
    public static void set() {
        trie = new Trie();
        trie.add("hello!!");
        trie.add("Happy:)");
        trie.add("hell");
        trie.add("Has@tings");
        trie.add("help#");
        trie.add("shout...");
        trie.add("share__");
        trie.add("sh_me");
        trie.add("sh+are");
    }

    @Test
    public void testRemove() {
        if(exceptionExpected) {
            try {
                trie.remove(word); // if does not throw exception then fail
                fail("Expected WordNotFoundException for word: " + word);

            }
            catch(WordNotFoundException e) {
                assertTrue(true);
            }
        }
        else {
            try {
                trie.remove(word);
                assertEquals(expected, trie.contains(word));
            }
            catch(WordNotFoundException e) {
                System.out.println(e.toString());
                System.out.println(word + "cant be removed as it is not present in trie");
                e.printStackTrace();
            }
        }
    }
}
