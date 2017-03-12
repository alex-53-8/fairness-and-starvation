package info.biosfood.fairness.and.starvation;

import org.apache.log4j.Logger;
import org.junit.Test;

public class StarvationTest extends AbstractTest {

    private static final Logger LOG = Logger.getLogger(StarvationTest.class);

    private final UnfairObjectAccess subject = new UnfairObjectAccess();

    @Test
    public void testStarvationForWriteOperation() throws InterruptedException {
        ThreadsBuilder.create()
                //  priority 9, repeat 30 times given job
                .withThread(9, 30, createReadJob(LOG, subject))
                //  priority 2, repeat 1 time given job
                .withThread(2, createWriteJob(LOG, subject))
                //  priority 9, repeat 30 times given job
                .withThread(9, 30, createReadJob(LOG, subject))
                .start();

        Thread.sleep(1000);
    }

}
