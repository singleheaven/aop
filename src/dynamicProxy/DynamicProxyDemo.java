package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import model.Business;
import model.IBusiness;
import model.IBusiness2;

/**
 * 使用动态代理实现AOP的Demo演示
 * 
 * @author tengfei.fangtf
 */
public class DynamicProxyDemo {

public static void main(String[] args) {
    aop();
}

public static void aop() {
    //需要代理的接口，被代理类实现的多个接口都必须在这里定义
    Class[] proxyInterface = new Class[] { IBusiness.class, IBusiness2.class };
    //构建AOP的Advice
    LogInvocationHandler handler = new LogInvocationHandler(new Business());
    //生成代理类的类加载器
    ClassLoader classLoader = DynamicProxyDemo.class.getClassLoader();
    //生成代理类
    IBusiness2 proxyBusiness = (IBusiness2) Proxy.newProxyInstance(classLoader, proxyInterface, handler);
    //使用代理类的实例来调用方法。
    proxyBusiness.doSomeThing2();
    ((IBusiness) proxyBusiness).doSomeThing();
}



/**
 * 打印日志的切面
 */
public static class LogInvocationHandler implements InvocationHandler {

    public Object target; //目标对象

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行原有逻辑
        Object rev = method.invoke(target, args);
        //执行织入的日志
        if (method.getName().equals("doSomeThing")) {
            System.out.println("动态代理记录日志");
        }
        return rev;
    }
}

}
