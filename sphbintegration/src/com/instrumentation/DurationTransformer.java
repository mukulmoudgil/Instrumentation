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

        if (className.equals("org/hibernate/impl/SessionFactoryImpl")) {
            System.out.println("Instrumenting...... class " + className);
            try {
                ClassPool classPool = ClassPool.getDefault();
                CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

                CtMethod[] methods = ctClass.getDeclaredMethods();

                for (CtMethod method : methods) {

                    if (method.getName().equals("openSession")) {
                        method.insertBefore("{ System.out.println(\"hello i am in open connection of SessionFactoryImpl\"); }");

                    }
                    if (method.getName().equals("close")) {
                        method.insertAfter("{ System.out.println(\"hello i am in Close session of SessionFactoryImpl\"); }");
                    }

                    if (method.getName().equals("getConnectionProvider")) {
                        method.insertAfter("{ System.out.println(\"hello i am in getConnectionProvider of SessionFactoryImpl\"); }");
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

        if (className.equals("org/hibernate/impl/SessionImpl")) {
            System.out.println("Instrumenting...... class " + className);
            try {
                ClassPool classPool = ClassPool.getDefault();
                CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

                CtMethod[] methods = ctClass.getDeclaredMethods();

                for (CtMethod method : methods) {


                    if (method.getName().equals("connection")) {
                        method.insertBefore("{ System.out.println(\"hello i am in  connection method of SessionImpl\"); }");

                    }
                    if (method.getName().equals("close")) {
                        method.insertAfter("{ System.out.println(\"hello i am in Close session of SessionImpl\"); }");
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

        if (className.equals("org/hibernate/stat/StatisticsImpl")) {
            System.out.println("Instrumenting...... class " + className);
            try {
                ClassPool classPool = ClassPool.getDefault();
                CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));

                CtMethod[] methods = ctClass.getDeclaredMethods();

                for (CtMethod method : methods) {

                    if (method.getName().equals("getSessionOpenCount")) {
                        System.out.println(method.getName().equals("getSessionOpenCount"));
                        System.out.println("good bye !!");
                        method.insertAfter("{ System.out.println(\"hello i am in getSessionOpenCountof StatisticsImpl+sessionOpenCount\"); }");

                    }
                    if (method.getName().equals("getSessionCloseCount")) {
                        method.insertBefore("{ System.out.println(\"hello i am in getSessionOpenCount of StatisticsImpl+sessionCloseCount\"); }");
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
