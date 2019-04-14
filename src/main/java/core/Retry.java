package core;

import org.apache.log4j.Logger;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class Retry implements TestRule {

    private int retryCount;
    private static Logger logger = Logger.getLogger(Retry.class);

    public Retry(int retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return statement(base, description);
    }

    private Statement statement(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable throwable = null;
                for(int i=0; i< retryCount; i++) {
                    try {
                        base.evaluate();
                        return;
                    } catch (Throwable e) {
                        logger.error("Run " + (i+1) + " failed");
                        throwable = e;
                    }
                }
                logger.error("Giving up after " + retryCount + " attempts");
                throw throwable;
            }
        };
    }
}
