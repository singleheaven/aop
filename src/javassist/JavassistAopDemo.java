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
public class JavassistAopDemo {

    public static void main(String[] args) throws Exception {
        aop();
    }

    public static void aop() throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
        //��ȡ���CtClass������ClassPool
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("model.Business");
        //���ָ���������ķ���
        CtMethod m = cc.getDeclaredMethod("doSomeThing");
        //�ڷ���ִ��ǰ�������
        m.insertBefore("{ System.out.println(\"��¼��־\"); }");
        ((Business) cc.toClass().newInstance()).doSomeThing();

    }
}
