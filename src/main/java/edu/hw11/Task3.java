package edu.hw11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;

//CHECKSTYLE:OFF: checkstyle:MagicNumber
@Slf4j
public class Task3 {
    private Task3() {

    }

//    public static void main(String[] args)
//        throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException,
//        IOException {
//        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
//            .subclass(Object.class)
//            .name("FibonacciClass")
//            .defineMethod("fib", long.class, Visibility.PUBLIC)
//            .withParameter(int.class, "n")
//            .intercept(MethodDelegation.to(FibonacciInterceptor.class))
//            .make();
//        Class<?> clazz = dynamicType.load(ByteBuddyExample.class.getClassLoader())
//            .getLoaded();
//
//
//        Object instance = clazz.getDeclaredConstructor().newInstance();
    // Вызываем метод
    //long result = FibonacciClass.class
    // (long) clazz.getDeclaredMethods()[0].invoke(instance, 6);
//        saveFile("FibonacciClass");
//
//    }

    private static void saveFile(String className)
        throws IOException {
        byte[] byteCode = generateFibonacciClass(className);
        String fileName = className + ".class";
        File file = new File(fileName);

        Files.write(file.toPath(), byteCode);

        log.info("Class saved to: " + file.getAbsolutePath());
    }

    private static byte[] generateFibonacciClass(String className) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, Type.getInternalName(Object.class), null);

        generateFibonacciMethod(cw);

        cw.visitEnd();

        return cw.toByteArray();
    }

    private static void generateFibonacciMethod(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "fibonacci", "(I)J", null, null);
        mv.visitCode();

        Label labelEnd = new Label();
        Label labelNonZero = new Label();

        // n==0
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitJumpInsn(Opcodes.IFNE, labelNonZero);
        mv.visitInsn(Opcodes.LCONST_0);
        mv.visitInsn(Opcodes.LRETURN);

        mv.visitLabel(labelNonZero);

        mv.visitInsn(Opcodes.LCONST_1); // fib(1)
        mv.visitVarInsn(Opcodes.LSTORE, 1);
        mv.visitInsn(Opcodes.LCONST_1); // fib(2)
        mv.visitVarInsn(Opcodes.LSTORE, 3);

        // Цикл
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        Label labelLoopStart = new Label();

        mv.visitLabel(labelLoopStart);
        mv.visitVarInsn(Opcodes.LLOAD, 1);
        mv.visitVarInsn(Opcodes.LLOAD, 3);
        mv.visitInsn(Opcodes.LADD);
        mv.visitVarInsn(Opcodes.LSTORE, 1);
        mv.visitVarInsn(Opcodes.LLOAD, 1);
        mv.visitVarInsn(Opcodes.LSTORE, 3);

        mv.visitInsn(Opcodes.LCONST_1);
        mv.visitVarInsn(Opcodes.LLOAD, 3);
        mv.visitInsn(Opcodes.LADD);
        mv.visitVarInsn(Opcodes.LSTORE, 3);
        // Уменьшение var0
        mv.visitIincInsn(0, -1);
        // Проверка условия выхода из цикла
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitJumpInsn(Opcodes.IFGT, labelLoopStart);

        // Метка окончания цикла
        mv.visitLabel(labelEnd);
        mv.visitVarInsn(Opcodes.LLOAD, 1);
        mv.visitInsn(Opcodes.LRETURN);

        // Окончание метода
        mv.visitMaxs(3, 3);
        mv.visitEnd();
    }

    public static class FibonacciInterceptor {
        @RuntimeType
        public static long fib(@AllArguments Object[] args) {
            int n = (int) args[0];
            return calculateFibonacci(n);
        }

        private static long calculateFibonacci(int n) {
            if (n <= 1) {
                return n;
            } else {
                return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
            }
        }
    }
}

