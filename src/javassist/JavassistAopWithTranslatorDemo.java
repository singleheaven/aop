/**
 * Create on 2011-10-13 ����04:29:04 by tengfei.fangtf
 * <p>
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * <p>
 * All rights reserved.
 */
package javassist;

import model.Business;

/**
 * ʹ��Javassist��ʾAop��Demo.
 *
 * @author tengfei.fangtf
 */
public class JavassistAopWithTranslatorDemo {

    public static void main(String[] args) throws Exception {
        aop();
    }

    public static void aop() throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
        //��ȡ���CtClass������ClassPool
        ClassPool cp = ClassPool.getDefault();
        //����һ���������
        Loader cl = new Loader();
        //����һ��ת������������ص�ʱ��
        cl.addTranslator(cp, new MyTranslator());
        //����װ�ص�JVM
        try {
            cl.run("javassist.JavassistAopWithTranslatorDemo$MyTranslator", null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static class MyTranslator implements Translator {

        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
        }

        /* *
         * ��װ�ص�JVMǰ���д���֯��
         */
        public void onLoad(ClassPool pool, String classname) {
            System.out.println("MyTranslator onLoad classname: " + classname);
            if (!"model.Business".equals(classname)) {
                return;
            }
            //ͨ��������ȡ���ļ�
            try {
                CtClass cc = pool.get(classname);
                //���ָ���������ķ���
                CtMethod m = cc.getDeclaredMethod("doSomeThing");
                //�ڷ���ִ��ǰ�������
                m.insertBefore("{ System.out.println(\"��¼��־\"); }");
            } catch (NotFoundException e) {
            } catch (CannotCompileException e) {
            }
        }

        public static void main(String[] args) {
            Business b = new Business();
            b.doSomeThing2();
            b.doSomeThing();
        }
    }

}
