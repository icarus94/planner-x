package rs.fon.plannerx.infrastructure.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ExecutionTimeLoggingAspect {

    public final Logger logger = LoggerFactory.getLogger(ExecutionTimeLoggingAspect.class);

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object postMethodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return this.methodTimeLogger(proceedingJoinPoint, "POST");
    }

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object getMethodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return this.methodTimeLogger(proceedingJoinPoint, "GET");
    }

    public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint, String httpMethod) throws Throwable {
        this.logger.info("[StopWatch - Aspect] RequestMapping is: {}", httpMethod);
        // Measure method execution time
        StopWatch stopWatch = new StopWatch(this.getIdentifier(proceedingJoinPoint));
        stopWatch.start(this.getTaskName(proceedingJoinPoint));
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        this.logger.info(stopWatch.prettyPrint());
        return result;
    }

    private String getIdentifier(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        // Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String uri = request.getRequestURI();
        return String.format(
                "HttpMethod: %s | Uri: %s [ Class: %s | Method: %s ]",
                request.getMethod(),
                uri,
                className,
                methodName
        );
    }

    private String getTaskName(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        return String.format("%s::%s", methodSignature.getDeclaringType().getSimpleName(), methodSignature.getName());
    }
}
