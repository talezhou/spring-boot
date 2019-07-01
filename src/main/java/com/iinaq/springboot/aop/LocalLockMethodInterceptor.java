package com.iinaq.springboot.aop;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.iinaq.springboot.annotation.LocalLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


@Aspect
@Configuration
public class LocalLockMethodInterceptor {

    private static final Cache<String,Object> CACHES = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(5,TimeUnit.SECONDS)
            .build();

    @Around("execution(public * *()) && @annotation(com.iinaq.springboot.annotation.LocalLock)")
    public Object interceptor(ProceedingJoinPoint pjp){
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        LocalLock annotation = method.getAnnotation(LocalLock.class);
        String key = getKey(annotation.key(), pjp.getArgs());
        if (!StringUtils.isEmpty(key)){
            if (CACHES.getIfPresent(key) != null){
                throw new RuntimeException("请勿重复提交");
            }
            CACHES.put(key,key);
        }

        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException("服务器异常");
        }finally {
            CACHES.invalidate(key);
        }
    }

    private String getKey(String keyExpress,Object[] args){
        for (int i = 0; i < args.length; i++) {
            keyExpress= keyExpress.replace("arg[" + i + "]", args[i].toString());
        }
        return keyExpress;
    }
}
