package fun.config.intercepors.actionlog;

import java.lang.reflect.Modifier;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// @Aspect
// @Component
public class ApiMonitorAdvice { // 类的执行前与执行后监视

    /** 定义切面 */
    // @Pointcut(
    //   "execution(* fun.server.controller..*.*(..))")
    //   // 为此类内一个空方法上面的注解。可以把拦截的地址表达式表示为方法签名，利于使用起来方便
    // public void pointCut() {}

    // @After("pointCut()")
    // public void afterMethod(JoinPoint joinPoint){
    //     System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
    //     System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
    //     System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
    //     System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
    //     //获取传入目标方法的参数
    //     Object[] args = joinPoint.getArgs();
    //     for (int i = 0; i < args.length; i++) {
    //         System.out.println("第" + (i+1) + "个参数为:" + args[i]);
    //     }
    //     System.out.println("被代理的对象:" + joinPoint.getTarget());
    //     System.out.println("被代理的对象类名:" + joinPoint.getTarget().getClass().getName());
    //     System.out.println("代理对象自己:" + joinPoint.getThis());
    // }
}