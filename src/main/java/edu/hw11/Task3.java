package edu.hw11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
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
public static void main(String[] args) throws IOException {
    saveFile("edu.hw11.FibonacciClass");
}
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

    private static ByteCodeAppender.Size generateFibonacciMethod(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "fibonacci", "(I)I", null, null);
        mv.visitCode();

        Label main = new Label();
        Label resReturn = new Label();
        Label whileStart = new Label();

        // n < 1
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, main);
        mv.visitInsn(Opcodes.ICONST_M1);
        mv.visitInsn(Opcodes.IRETURN);

        //loop
        mv.visitLabel(main);
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitVarInsn(Opcodes.ISTORE, 2);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitVarInsn(Opcodes.ISTORE, 3);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitVarInsn(Opcodes.ISTORE, 4);
        mv.visitLabel(whileStart);

        mv.visitFrame(
            Opcodes.F_FULL,
            5,
            new Object[] {Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER, Opcodes.INTEGER},
            0,
            null
        );

        mv.visitVarInsn(Opcodes.ILOAD, 4);
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitJumpInsn(Opcodes.IF_ICMPGE, resReturn);

        // loop body
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitInsn(Opcodes.IADD);
        mv.visitVarInsn(Opcodes.ISTORE, 3);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitVarInsn(Opcodes.ISTORE, 2);
        mv.visitIincInsn(4, 1);
        mv.visitJumpInsn(Opcodes.GOTO, whileStart);

        // return
        mv.visitLabel(resReturn);
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitInsn(Opcodes.IRETURN);

        return new ByteCodeAppender.Size(2, 5);

    }
}

