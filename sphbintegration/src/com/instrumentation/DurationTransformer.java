package com.instrumentation;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

//this class will be registered with instrumentation agent

public class DurationTransformer implements ClassFileTransformer {
    private ClassPool pool;

    /*
     * public void ImportantLogClassTransformer() { pool = ClassPool.getDefault(); }
     */

    @Override
    public byte[] transform(ClassLoader loader, String className, Class classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] byteCode = classfileBuffer;

        // since this transformer will be called when all the classes are
        // loaded by the classloader, we are restricting the instrumentation
        // using if block only for the Lion class
        // System.out.println("Instrumenting...... class " + className);
        if (className.equals("org/hibernate/SessionFactory")) {
            System.out.println("Instrumenting...... class " + className);
            try {
                ClassPool classPool = ClassPool.getDefault();
                // System.out.println(classPool.getDefault().getMethod(classname, methodname));
                CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
                // CtClass point = classPool.getDefault().get("Point");
                // CtField f = new CtField(CtClass.intType, "z", point);
                // point.addField(f);

                CtMethod[] methods = ctClass.getDeclaredMethods();

                for (CtMethod method : methods) {
                    System.out.println(method.getMethodInfo().getName());
                    if (method.getReturnType().equals("Session")) {
                        // method.addLocalVariable("Logstatements", CtClass.charType);
                        // method.insertBefore("Logstatements = in method call");
                        // method.insertBefore("System.out.println(\"saveOrUpdate ");
                        // method.insertBefore("System.out.println(\"get current session called ");
                        // method.insertBefore("System.out.println(\"Here I am!\")");
                        System.out.println("hello i am in");
                        method.addLocalVariable("startTime", CtClass.longType);
                        method.insertBefore("startTime = System.nanoTime();");
                        method.insertAfter("System.out.println(\"Execution Duration "
                                + "(nano sec): \"+ (System.nanoTime() - startTime) );");
                    }

                    // method.insertAfter("System.out.println(\"Execution Duration "
                    // + "(nano sec): \"+ (System.nanoTime() - startTime) );");
                }
                byteCode = ctClass.toBytecode();
                ctClass.detach();
                System.out.println("Instrumentation complete : " + className);
            } catch (Throwable ex) {
                System.out.println("Exception: " + ex);
                ex.printStackTrace();

            }
        }
        return byteCode;
    }
}
