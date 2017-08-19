/**
 * Create on 2011-10-16 ����07:48:33 by tengfei.fangtf
 * <p>
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * <p>
 * All rights reserved.
 */
package bci;

import javassist.*;
import model.Business;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * �ֽ���ת����
 *
 * @author tengfei.fangtf
 */
public class MyClassFileTransformer implements ClassFileTransformer {
    /**
     * �ֽ�����ص������ǰ������������
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        System.out.println(className);
        //�������Business�������
        if (!"model/Business".equals(className)) {
            return null;
        }

        //javassist�İ������õ�ָ�ģ���Ҫת����
        if (className != null && className.indexOf("/") != -1) {
            className = className.replaceAll("/", ".");
        }
        try {
            //ͨ��������ȡ���ļ�
            CtClass cc = ClassPool.getDefault().get(className);
            //���ָ���������ķ���
            CtMethod m = cc.getDeclaredMethod("doSomeThing");
            //�ڷ���ִ��ǰ�������
            m.insertBefore("{ System.out.println(\"��¼��־\"); }");
            return cc.toBytecode();
        } catch (NotFoundException e) {
        } catch (CannotCompileException e) {
        } catch (IOException e) {
            //�����쳣����
        }
        return null;
    }

    /**
     * ��main����ִ��ǰ��ִ�еĺ���
     *
     * @param options
     * @param ins
     */
    public static void premain(String options, Instrumentation ins) {
        //ע�����Լ����ֽ���ת����
        ins.addTransformer(new MyClassFileTransformer());
    }

}
