package log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，便于开发时的调试等
 * 方法上加上该注解，会自动记录该方法运行时间
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DevelopingLogAnnotation {
    String interfaceName();
} 
