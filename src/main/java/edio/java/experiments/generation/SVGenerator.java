package edio.java.experiments.generation;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.V1_7;

/**
 * Generates a class implementing {@link edio.java.experiments.S} and {@link edio.java.experiments.V} interfaces.
 */
public class SVGenerator {

  public static final String SV_FQCN = "edio.java.experiments.SV";

  public static ClassWriter generateClass() {
    ClassWriter cw = new ClassWriter(0);

    // package edio.java.experiments
    // public class SV implements S, V
    cw.visit(V1_7, ACC_PUBLIC, "edio/java/experiments/SV", null, "java/lang/Object", new String[]{
        "edio/java/experiments/S",
        "edio/java/experiments/V"
    });

    // constructor
    MethodVisitor constructor = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
    constructor.visitCode();
    constructor.visitVarInsn(Opcodes.ALOAD, 0);
    constructor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
    constructor.visitInsn(Opcodes.RETURN);
    constructor.visitMaxs(1, 1);
    constructor.visitEnd();

    // public String m(int i)
    MethodVisitor mString = cw.visitMethod(ACC_PUBLIC, "m", "(I)Ljava/lang/String;", null, null);
    mString.visitCode();
    mString.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    mString.visitLdcInsn("String");
    mString.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
    mString.visitInsn(Opcodes.ACONST_NULL);
    mString.visitInsn(Opcodes.ARETURN);
    mString.visitMaxs(2, 2);
    mString.visitEnd();

    // public void m(int i)
    MethodVisitor mVoid = cw.visitMethod(ACC_PUBLIC, "m", "(I)V", null, null);
    mVoid.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
    mVoid.visitLdcInsn("void");
    mVoid.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
    mVoid.visitInsn(Opcodes.RETURN);
    mVoid.visitMaxs(2, 2);
    mVoid.visitEnd();

    cw.visitEnd();
    return cw;
  }
}
