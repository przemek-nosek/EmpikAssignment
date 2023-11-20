package pl.java.shared.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectLogging {

    @Pointcut("execution(* pl.java..*(..))")
    public void matchAllMethods() {
    }

    @Before("matchAllMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        log.info("[START] {}.{} method with params: {}", className, methodName, joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "matchAllMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        log.info("[END] {}.{} method returned {}", className, methodName, result);
    }

    @AfterThrowing(pointcut = "matchAllMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        log.info("[ERROR] {}.{} method finished with an error: {}, stack: ", className, methodName, exception.getMessage(), exception);
    }
}
