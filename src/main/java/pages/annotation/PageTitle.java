package pages.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Ramich on 03.10.2018.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface PageTitle {
    String value();
}
