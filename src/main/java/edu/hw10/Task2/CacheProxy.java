//CHECKSTYLE:OFF: checkstyle:OuterTypeNumber

package edu.hw10.Task2;
//CHECKSTYLE:OFF: checkstyle:OuterTypeNumber
//CHECKSTYLE:OFF: checkstyle:AvoidStarImport
//CHECKSTYLE:OFF: checkstyle:OneTopLevelClass

import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) @interface Cache {
    boolean persist() default false;
}

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Long> cache;
    private final String cacheDir;

    private CacheProxy(Object target, String cacheDir) {
        this.target = target;
        this.cache = new ConcurrentHashMap<>();
        this.cacheDir = cacheDir;
    }

    public static <T> T create(T target, Class<?> targetInterface, String cacheDir) {
        return (T) Proxy.newProxyInstance(
            targetInterface.getClassLoader(),
            target.getClass().getInterfaces(),
            new CacheProxy(target, cacheDir)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            Cache cacheAnnotation = method.getAnnotation(Cache.class);
            boolean persist = cacheAnnotation.persist();
            int arg = (int) args[0];

            String cacheKey = method.getName() + "_" + arg;

            if (cache.containsKey(cacheKey)) {
                return cache.get(cacheKey);
            }

            long result = (long) method.invoke(target, args);
            cache.put(cacheKey, result);

            if (persist) {
                saveToDisk(cacheKey, result);
            }

            return result;
        } else {
            return method.invoke(target, args);
        }
    }

    private void saveToDisk(String cacheKey, long result) {
        String fileName = cacheDir + "/" + cacheKey + ".cache";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(result);
        } catch (IOException e) {
        }
    }
}



