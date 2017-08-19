/**
 * Create on 2011-10-13 下午04:29:04 by tengfei.fangtf
 * <p>
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * <p>
 * All rights reserved.
 */
package javassist;

import model.Business;

/**
 * 使用Javassist演示Aop的Demo.
 *
 * @author tengfei.fangtf
 */
public class JavassistAopDemo {

    public static void main(String[] args) throws Exception {
        aop();
    }

    public static void aop() throws NotFoundException, CannotCompileException, InstantiationException, IllegalAccessException {
        //获取存放CtClass的容器ClassPool
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("model.Business");
        //获得指定方法名的方法
        CtMethod m = cc.getDeclaredMethod("doSomeThing");
        //在方法执行前插入代码
        m.insertBefore("{ System.out.println(\"记录日志\"); }");
        ((Business) cc.toClass().newInstance()).doSomeThing();

    }
}
