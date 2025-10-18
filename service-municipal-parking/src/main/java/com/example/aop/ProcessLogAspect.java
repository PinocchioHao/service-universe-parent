package com.example.aop;

import com.example.util.ProcessLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
public class ProcessLogAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        // Activity 就用方法名，也可以自定义注解来映射
        String activity = methodName;
        String caseId = extractCaseId(joinPoint.getArgs()); // 从参数中提取 caseId（如车牌号）
        String resource = joinPoint.getTarget().getClass().getSimpleName();

        if (caseId != null) {
            ProcessLogger.log(caseId, activity, resource);
        }
    }

    private String extractCaseId(Object[] args) {
        // 简化逻辑：如果第一个参数是 String，就认为是 caseId (比如 licensePlate)
        if (args != null && args.length > 0 && args[0] instanceof String) {
            return (String) args[0];
        }
        return UUID.randomUUID().toString();
    }
}
