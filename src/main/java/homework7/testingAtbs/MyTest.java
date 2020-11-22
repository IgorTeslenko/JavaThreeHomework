package homework7.testingAtbs;

import java.lang.annotation.*;

/**
 * Собственный класс для тестирования. Выбрано имя отличное от того, что в задании, чтобы не плодить сущностей
 * и продолжать разработку в существующем проекте, в котором имплементирован JUnit
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyTest {
    int priority();
}