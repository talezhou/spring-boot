package com.iinaq.springboot.common;

import com.iinaq.springboot.annotation.CacheLock;
import com.iinaq.springboot.annotation.CacheParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class CacheKeyGeneratorImpl implements CacheKeyGenerator{
    @Override
    public String getLockKey(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Method method = signature.getMethod();
        CacheLock cacheLock = method.getAnnotation(CacheLock.class);
        final Object[] args = pjp.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parameters.length; i++) {
            CacheParam cacheParam = parameters[i].getAnnotation(CacheParam.class);
            if (cacheParam == null){
                continue;
            }
            builder.append(cacheLock.delimeter()).append(args[i]);
        }
        if (StringUtils.isEmpty(builder.toString())){
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                final Object arg = args[i];
                final Field[] fields = arg.getClass().getDeclaredFields();
                for (Field f:fields) {
                    CacheParam cacheParam = f.getAnnotation(CacheParam.class);
                    if (cacheParam == null){
                        continue;
                    }
                    f.setAccessible(true);
                    builder.append(cacheLock.delimeter()).append(ReflectionUtils.getField(f, arg));
                }
            }
        }
        return cacheLock.prefix()+builder.toString();
    }
}
