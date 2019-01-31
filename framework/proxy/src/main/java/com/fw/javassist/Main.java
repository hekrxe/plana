package com.fw.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

/**
 * Created by tanhuayou on 2019/01/18
 */
public class Main {
    public static void main(String[] args) throws Exception {
        testTiming();
    }


    private static void testTiming() throws Exception {
        CtClass clas = ClassPool.getDefault().get("com.fw.javassist.StringBuilder");
        addTiming(clas, "buildString");
        clas.writeFile();

        // for load
        ((StringBuilder) clas.toClass().newInstance()).buildString(23423);
        StringBuilder builder = new StringBuilder();
        builder.buildString(10233);
    }

    private static void addTiming(CtClass cc, String methodName) throws Exception {
        CtMethod method = cc.getDeclaredMethod(methodName);
        String newMethodName = methodName + "$impl";
        method.setName(newMethodName);
        CtMethod newMethod = CtNewMethod.copy(method, methodName, cc, null);
        String type = method.getReturnType().getName();
        StringBuffer body = new StringBuffer();

        body.append("{\n");
        body.append("long t1 = System.currentTimeMillis();\n");
        if (!"void".equals(type)) {
            body.append(type).append(" result = ");
        }
        body.append(newMethodName).append("($$);\n");
        body.append("System.out.println(\"Time: \"+ (System.currentTimeMillis() - t1));\n");
        if (!"void".equals(type)) {
            body.append("return result;\n");
        }
        body.append("}");
        newMethod.setBody(body.toString());
        cc.addMethod(newMethod);

        System.out.println(body.toString());

    }


    private static void superclass() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.fw.javassist.Demo");
        cc.setSuperclass(pool.get("com.fw.javassist.Echo"));
        cc.writeFile();

        Class newClass = cc.toClass();
        Object o = newClass.newInstance();
        ((Echo) o).echo("aaaaaaaa");
    }

    private static void defineNewClass() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.fw.javassist.Demo");
        CtMethod time = cc.getDeclaredMethod("time");
        time.insertBefore(" long t1 = System.currentTimeMillis();");
        time.insertAfter("long t2 = System.currentTimeMillis(); System.out.println(t2-t1);");
        Demo demo = (Demo) cc.toClass().newInstance();
        demo.time();
    }


}
