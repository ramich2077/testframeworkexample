package util;

import pages.exception.AutotestError;

import java.util.function.Supplier;

/**
 * Created by Ramich on 26.10.2018.
 */
public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static void waitForCondition(Supplier<Boolean> conditionSupplier, long timeout, int pollingInterval) throws AutotestError {
        while (!conditionSupplier.get()) {
            try {
                Thread.sleep(pollingInterval * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AutotestError("Thread was interrupted while waiting for condition", e);
            }
            waitForCondition(conditionSupplier, timeout - pollingInterval, pollingInterval);
        }
    }
}
