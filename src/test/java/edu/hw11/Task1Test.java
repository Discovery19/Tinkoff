package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {
    @Test
    @DisplayName("Task 1")
    void testTask1()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.isToString())
            .intercept(FixedValue.value("Hello, Byte Buddy"))
            .make();
        Class<?> aClass = dynamicType.load(getClass()
                .getClassLoader())
            .getLoaded();
        String result = aClass.getConstructor().newInstance().toString();
        assertEquals(
            result, "Hello, Byte Buddy");
    }
}
