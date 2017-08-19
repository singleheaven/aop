package performance;
import cglib.CglibAopDemo;
import dynamicProxy.DynamicProxyDemo;
import staticProxy.ProxyBusiness;

/**
 * Create on 2011-10-12 ÏÂÎç04:28:35 by tengfei.fangtf
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * 
 * All rights reserved.
 */

/**
 * TODO please describe PerformanceTest.
 * 
 * @author tengfei.fangtf
 */
public class PerformanceTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        DynamicProxyDemo.aop();
        long end = System.currentTimeMillis();
        long dynamicProxyNeedTime = end - start;

        start = System.currentTimeMillis();
        ProxyBusiness.staticDynamic();
        end = System.currentTimeMillis();
        long staticProxyNeedTime = end - start;

        start = System.currentTimeMillis();
        CglibAopDemo.byteCodeGe();
        end = System.currentTimeMillis();
        long byteCodeGeNeedTime = end - start;

        System.out.println("dynamicProxy need time:" + dynamicProxyNeedTime + "ms");
        System.out.println("staticDynamic need time:" + staticProxyNeedTime + "ms");
        System.out.println("byteCodeGe need time:" + byteCodeGeNeedTime + "ms");
    }

}
