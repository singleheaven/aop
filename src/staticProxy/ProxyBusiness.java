/**
 * Create on 2011-10-12 ����02:03:12 by tengfei.fangtf
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * 
 * All rights reserved.
 */
package staticProxy;

import java.lang.reflect.Method;

import model.Business;
import model.IBusiness;
import model.IBusiness2;
import dynamicProxy.DynamicProxyDemo.LogInvocationHandler;

/**
 * ֯�������ɵĴ�����
 * 
 * @author tengfei.fangtf
 */
public class ProxyBusiness implements IBusiness, IBusiness2 {

    private LogInvocationHandler h;

    @Override
    public void doSomeThing2() {
        try {
            Method m = (h.target).getClass().getMethod("doSomeThing2", null);
            h.invoke(this, m, null);
        } catch (Throwable e) {
            // �쳣���� 1���ԣ�
        }
    }

    @Override
    public boolean doSomeThing() {
        try {
            Method m = (h.target).getClass().getMethod("doSomeThing", null);
            return (Boolean) h.invoke(this, m, null);
        } catch (Throwable e) {
            // �쳣���� 1���ԣ�
        }
        return false;
    }

    public ProxyBusiness(LogInvocationHandler h) {
        this.h = h;
    }

    //������
    public static void main(String[] args) {
        //����AOP��Advice
        staticDynamic();
    }

    public static void staticDynamic() {
        LogInvocationHandler handler = new LogInvocationHandler(new Business());
        new ProxyBusiness(handler).doSomeThing();
        new ProxyBusiness(handler).doSomeThing2();
    }

}
