package com.iinaq.springboot.annotation;

import java.lang.annotation.*;

/**
 * @ClassName LocalLock
 * @Description TODO
 * @Author zhouzhongshan
 * @Date 2019/7/1  9:55
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LocalLock {
    String key() default "";

    int expire() default 5;
}
