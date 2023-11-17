package pl.java.shared.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectLogging { //todo

//    @Around("execution(* pl.java..*(..))")
//    public Object aroundExecutionOfMethods(ProceedingJoinPoint joinPoint) {
//        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
//        String methodName = joinPoint.getSignature().getName();
//        try {
//            log.info("[START] {}.{} method with params: {}", className, methodName, joinPoint.getArgs());
//            Object object = joinPoint.proceed();
//            log.info("[END] {}.{} method returned {}", className, methodName, object);
//            return object;
//        } catch (Throwable e) {
//            log.info("[ERROR] {}.{} method finished with an error: {}, stack: ", className, methodName, e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }

//    @Before("execution(* pl.java..*.*(..))")
//    public void logMethodEntry(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getTarget().getClass().getSimpleName();
//        log.info("[START] {}.{} method with params: {}", className, methodName, joinPoint.getArgs());
//    }
//
//    @After("execution(* pl.java..*.*(..))")
//    public void logMethodExit(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getTarget().getClass().getSimpleName();
//        log.info("Exiting {}.{} with result: {}", className, methodName, joinPoint.getTarget());
//    }
//
//    @AfterThrowing(pointcut = "execution(* pl.java..*.*(..))", throwing = "exception")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getTarget().getClass().getSimpleName();
//        log.error("Exception in {}.{} with message: {}", className, methodName, exception.getMessage());
//    }
}
