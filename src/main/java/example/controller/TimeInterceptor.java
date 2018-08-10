package example.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 统计请求时间，可以通过最简单的在方法中计算得知，也可以通过AOP实现，还可以通过拦截器实现
 * 不知道为什么没有实现成功，但是要知道有这种方法
 */
@Aspect
@Component
@Slf4j
public class TimeInterceptor {
    // 一分钟，即1000ms
    private static final long ONE_MINUTE = 1;

    // service层的统计耗时切面，类型必须为final String类型的,注解里要使用的变量只能是静态常量类型的
    public static final String POINT = "execution(* example.service.SysUserService.getById(..))";

    /**
     * 统计方法执行耗时Around环绕通知
     */
    @Around(POINT)
    public Object timeAround(ProceedingJoinPoint joinPoint)
    {
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        try
        {
            obj = joinPoint.proceed(args);
        } catch (Throwable e)
        {
            log.error("统计某方法执行耗时环绕通知出错", e);
        }
        // 获取执行的方法名
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        // 打印耗时的信息
        this.printExecTime(methodName, startTime, endTime);
        return obj;
    }

    /**
     * 打印方法执行耗时的信息，如果超过了一定的时间，才打印
     */
    private void printExecTime(String methodName, long startTime, long endTime)
    {
        long diffTime = endTime - startTime;
        if (diffTime > ONE_MINUTE)
        {
            log.warn("-----" + methodName + " 方法执行耗时：" + diffTime + " ms");
        }
    }

}