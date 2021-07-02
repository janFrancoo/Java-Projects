package com.janfranco.springaop.aspect;

import com.janfranco.springaop.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Aspect
@Component
@Order(1) // Aspect execution order
// If methods need to be lined up, separate them into multiple aspect classes
// Order -> -999 -> -500 -> 0 -> 1 -> 3 -> 99 -> 1000...
// Same order value, undefined execution line
public class LoggingAspect {

    // "execution(public void addAccount())" -> will call addAccount in every Aspect classes
    // "execution(public void <package>.addAccount())" -> will call exact addAccount
    // "execution(public void add*())" -> will call every method that starts with add name & void return type
    // "execution(* * add*())" -> will call every method that starts with add name
    // Modifier is optional
    // For parameters, () -> no arg match, (*) -> one arg match, (..) -> 0 or more arg match
    // Class with full package info -> specific arg match
    // .. operator can be combined like this -> package.package.*.*(..)
    /*
    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice() {
        System.out.println(getClass() + ": logging add account");
    }
    */

    // For using same pointcut ->
    @Pointcut("execution(public void addAccount(..))")
    private void addAccountPointcut() {
    }

    @Before("addAccountPointcut()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) { // joinPoint can get the args
        System.out.println(getClass() + ": logging add account");

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
    }

    @AfterReturning(
            pointcut = "execution(* addAccount(..))",
            returning = "accountList"
    )
    public void afterAddAccount(JoinPoint joinPoint, List<Account> accountList) {
        for (Account account : accountList) {
            account.setName(account.getName().toUpperCase(Locale.ROOT));
        }
    }

    @AfterThrowing(
            pointcut = "execution(* getAccount())",
            throwing = "exception"
    )
    public void afterThrowGetAccount(JoinPoint joinPoint, Throwable exception) {
        System.out.println(exception.getLocalizedMessage());
    }

    // Combining pointcuts
    /*
    @Pointcut("execution(* * add*()")
    private void somePointcut() {
    }

    @Pointcut("execution(* * get*())")
    private void getterPointcuts() {
    }

    @Pointcut("execution(* * set*())")
    private void setterPointcuts() {
    }

    @Pointcut("somePointcut() && !(getterPointcuts() || setterPointcuts())")
    private void pointcutWithoutGetters() {
    }
    */

    @Around("execution(* doWork())")
    public Object aroundDoWork(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println(duration);
        return result;
    }
}
