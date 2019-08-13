package com.iinaq.springboot.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {
    String name() default "";
}
