package com.iinaq.springboot.annotation;

import java.lang.annotation.*;

/**
 * @ClassName CacheParam
 * @Description TODO
 * @Author zhouzhongshan
 * @Date 2019/7/1  10:19
 **/
@Target({ElementType.PARAMETER,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {
    String name() default "";
}
