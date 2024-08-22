package Tests.TestTrie;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    TestContains.class,
    TestRemove.class
})

public class TestAll {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestAll.class);

        for(Failure failures: result.getFailures()) {
            System.out.println(failures.toString());
        }

        System.out.println("All test cases passed succesfully: " + result.wasSuccessful());
    }
}
