package bci;

import model.Business;

/**
 * Created by huoyan on 2017/8/20.
 */
public class GlobalAopDemo {
    public static void main(String[] args) {
        new Business().doSomeThing();
        new Business().doSomeThing2();
    }
}
