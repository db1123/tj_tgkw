package fun.config.intercepors.actionlog;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogOperation {
    String value() default ""; // 默认为空，因为名字是value，实际操作中可以不写"value="
}