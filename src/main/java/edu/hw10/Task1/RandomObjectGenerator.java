//CHECKSTYLE:OFF: checkstyle:OuterTypeNumber

package edu.hw10.Task1;
//CHECKSTYLE:OFF: checkstyle:OneTopLevelClass
//CHECKSTYLE:OFF: checkstyle:AvoidStarImport

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) @interface NotNull {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) @interface Min {
    int value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) @interface Max {
    int value();
}

public class RandomObjectGenerator {
    private final Random random = new Random();

    public <T> T nextObject(Class<T> clazz) throws Exception {
        if (clazz.isRecord()) {
            return generateRecord(clazz);
        } else {
            return generatePOJO(clazz);
        }
    }
    public <T> T nextObject(Class<T> clazz, String fabricMethodName) throws Exception {
        var methods = clazz.getMethods();
        for (var m : methods) {
            if (m.getName().equals(fabricMethodName)) {
                Parameter[] parameter = m.getParameters();
                Object [] values = new Object[parameter.length];
                for (int i = 0; i < parameter.length; i++) {
                    values[i] = generateRandomValue(parameter[i].getType());
                }
                return (T) m.invoke(clazz, values);
            }
        }
        throw new RuntimeException();
    }

    private <T> T generateRecord(Class<T> clazz) {
        try {
            Constructor<T> constructor = (Constructor<T>) clazz.getDeclaredConstructors()[0];
            Object[] params = Arrays.stream(constructor.getParameterTypes())
                .map(this::generateRandomValue)
                .toArray();
            return constructor.newInstance(params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> T generatePOJO(Class<T> clazz) throws Exception {
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
        Constructor<T> constructor = Arrays.stream(constructors)
            .filter(c -> c.getParameterCount() > 0)
            .findFirst()
            .orElseThrow(() -> new NoSuchMethodException("No parameterized constructor found"));

        Object[] params = Arrays.stream(constructor.getParameterTypes())
            .map(this::generateRandomValue)
            .toArray();
        //System.out.println(Arrays.toString(params));
        return constructor.newInstance(params);
    }

    private Object generateRandomValue(Class<?> type) {
        if (type == int.class || type == Integer.class) {
            return random.nextInt(0, Integer.MAX_VALUE);
        } else if (type == String.class) {
            return UUID.randomUUID().toString();
        }

        return null;
    }
}
