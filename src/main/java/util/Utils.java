package util;

import annotation.Element;
import exception.AutotestError;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by Ramich on 26.10.2018.
 */
public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static void waitForCondition(Supplier<Boolean> conditionSupplier, long timeout, int pollingInterval) throws AutotestError {
        if(!conditionSupplier.get()) {
            try {
                Thread.sleep(pollingInterval * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new AutotestError("Thread was interrupted while waiting for condition", e);
            }
            waitForCondition(conditionSupplier, timeout - pollingInterval, pollingInterval);
        }
    }

    public static Field getElementByTitle(Class<?> clazz, String title) throws AutotestError {
        return getAllFields(clazz).stream()
                .filter(field -> field.isAnnotationPresent(Element.class)
                && field.getName().equals(castToCamelCase(title)))
                .findFirst()
                .orElseThrow(()-> new AutotestError(String.format("Element wiht title %s isn't found", title)));
    }

    public static String castToCamelCase(@NonNull String input) {
        String[] strings = input.split(" ");
        return strings[0] + Arrays.stream(strings).skip(1).map(StringUtils::capitalize).collect(Collectors.joining());
    }

    public static Set<Field> getAllFields(Class<?> type) {
        Set<Field> fields = new java.util.HashSet<>();
        if (type.getSuperclass() != null) {
            fields.addAll(getAllFields(type.getSuperclass()));
        }

        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        return fields;
    }
}
