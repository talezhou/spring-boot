package com.iinaq.springboot.common;

import org.aspectj.lang.ProceedingJoinPoint;

public interface CacheKeyGenerator {
    String getLockKey(ProceedingJoinPoint pjp);
}
