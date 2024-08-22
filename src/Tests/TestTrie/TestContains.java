/* This test case tests the Trie.contains() method */

package Tests.TestTrie;

import java.util.Collection;
import java.util.Arrays;
import static org.junit.Assert.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.BeforeClass;
import DataStructures.trie.Trie;

@RunWith(Parameterized.class)
public class TestContains {
    @Parameters 
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {"hello!!", true}, 
            {"hell", true}, 
            {"shout..", false}, 
            {"Has@tings", true}, 
            {"share__", true}, 
            {"sha+re", false}, 
            {"Happy:)", true}, 
            {"h+are", false}, 
            {"how", false}, 
        });
    }

    private String word;
    private boolean expected;
    private static Trie trie;

    // Constructor to initialize parameters
    public TestContains(String word, boolean expected) {
        this.word = word;
        this.expected = expected;
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
    public void testContains() {
        assertEquals(expected, trie.contains(word));
    }
}