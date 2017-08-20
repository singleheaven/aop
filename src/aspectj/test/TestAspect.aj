package test;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.ConstructorSignature;

public aspect TestAspect {
    pointcut testAll():within(Test);

    before():testAll(){
        System.out.println("before calling: " + thisJoinPoint);
        System.out.println("      at: " + thisJoinPoint.getSourceLocation());
    }

    after():testAll(){
        Signature sig = thisJoinPoint.getSignature();
        if (sig instanceof ConstructorSignature) {
            System.out.println("after calling: " + thisJoinPoint);
            System.out.println("      at: " + thisJoinPoint.getSourceLocation());
        }
    }

    public pointcut staticInit(): staticinitialization(test.Test.TestBase);
    before(): staticInit() {
        System.out.println("before: " + thisJoinPoint);
        System.out.println("      at: " + thisJoinPoint.getSourceLocation());
    }

    after(): staticInit() {
        System.out.println("after: " + thisJoinPoint);
        System.out.println("      at: " + thisJoinPoint.getSourceLocation());
    }
    after() returning(): staticInit() {
        System.out.println("after returning: " + thisJoinPoint);
        System.out.println("      at: " + thisJoinPoint.getSourceLocation());
    }
    after() throwing(): staticInit() {
        System.out.println("after throwing: " + thisJoinPoint);
        System.out.println("      at: " + thisJoinPoint.getSourceLocation());
    }
}
