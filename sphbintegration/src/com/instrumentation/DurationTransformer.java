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
                CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
                CtMethod[] methods = ctClass.getDeclaredMethods();

                for (CtMethod method : methods) {
                    // System.out.println(method.getMethodInfo().getName());
                    if (method.getName().equals("openSession")) {
                        // method.addLocalVariable("Logstatements", CtClass.charType);
                        // method.insertBefore("Logstatements = in method call");
                        // method.insertBefore("System.out.println(\"saveOrUpdate ");
                        // method.insertBefore("System.out.println(\"get current session called ");
                        method.insertBefore("System.out.println(\"Here I am!\")");
                        System.out.println("hello i am in open session");

                    }
                    if (method.getName().equals("close")) {
                        System.out.println("i am in close");
                    }
                    if (method.getName().equals("getStatistics")) {
                        System.out.println("i am in getStatistics");
                    }
                    if (method.getName().equals("getCurrentSession")) {
                        System.out.println("i am in getCurrentSession");
                    }

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
