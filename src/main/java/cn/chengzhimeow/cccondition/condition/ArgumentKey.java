package cn.chengzhimeow.cccondition.condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArgumentKey {
    String[] keys();

    boolean disabledCheck() default false;

    boolean required() default true;
}
