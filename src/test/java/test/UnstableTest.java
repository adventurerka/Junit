package test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

public class UnstableTest {

    @Rule
    public TestRule runTwiceRule = new RunTwiceRule();

    private static int attempt = 2;

    @Test
    @Unstable(3)
    public void randomlyFailingTest() {
        if (attempt == 2) {
        } else {
            Assert.fail("Failed on attempt");
        }
    }

    @Test
    @Unstable(3)
    public void randomlyFailingTest2() {
        if (attempt == 3) {
        } else {
            Assert.fail("Failed on attempt");
        }
    }
}
