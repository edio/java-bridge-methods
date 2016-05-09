package edio.java.experiments;

import edio.java.experiments.generation.AsmClassLoader;
import edio.java.experiments.generation.SVGenerator;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class BridgeMethodsExperiment {

  public static void main(String[] args) throws Exception {
    ClassWriter cw = SVGenerator.generateClass();

    // Store class to filesystem for investigation purposes.
//    Files.write(new File("/tmp/SV.class").toPath(), cw.toByteArray());

    AsmClassLoader classLoader = new AsmClassLoader();
    Class<?> generatedClazz = classLoader.defineAsmClass(SVGenerator.SV_FQCN, cw);
    Object o = generatedClazz.newInstance();
    ((S)o).m(1);
    ((V)o).m(1);
  }
}
