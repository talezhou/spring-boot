package com.iinaq.springboot.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheLock
 * @Description TODO
 * @Author zhouzhongshan
 * @Date 2019/7/1  10:53
 **/
@Target({ElementType.PARAMETER,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLock {

    String prefix() default "";

    int expire() default 5;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    String delimeter() default ":";
}
