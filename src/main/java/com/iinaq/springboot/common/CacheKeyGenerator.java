package com.iinaq.springboot.common;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @ClassName CacheKeyGenerator
 * @Description TODO
 * @Author zhouzhongshan
 * @Date 2019/7/1  10:21
 **/
public interface CacheKeyGenerator {
    String getLockKey(ProceedingJoinPoint pjp);
}
