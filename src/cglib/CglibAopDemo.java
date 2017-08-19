package cglib;

import java.lang.reflect.Method;

import model.Business;
import model.IBusiness2;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * �ֽ������ɻ�����ʾAOP
 * 
 * @author tengfei.fangtf
 */
public class CglibAopDemo {

    public static void main(String[] args) {
        byteCodeGe();
    }

    public static void byteCodeGe() {
        //����һ��֯����
        Enhancer enhancer = new Enhancer();
        //���ø���
        enhancer.setSuperclass(Business.class);
        //������Ҫ֯����߼�
        enhancer.setCallback(new LogIntercept());
        //��������
        IBusiness2 newBusiness = (IBusiness2) enhancer.create();
        newBusiness.doSomeThing2();
    }

    /**
     * ��¼��־
     */
    public static class LogIntercept implements MethodInterceptor {

        @Override
        public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            //ִ��ԭ���߼�
            Object rev = proxy.invokeSuper(target, args);
            //ִ��֯�����־
            if (method.getName().equals("doSomeThing2")) {
                System.out.println("��¼��־");
            }
            return rev;
        }
    }
}
