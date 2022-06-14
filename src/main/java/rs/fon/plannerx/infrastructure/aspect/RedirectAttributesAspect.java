package rs.fon.plannerx.infrastructure.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class RedirectAttributesAspect {

    //@Around("within(org.springframework.web.servlet.mvc.support.RedirectAttributes) && execution(public * addFlashAttribute(..))")
    //@Around("within(org.springframework.web.servlet.mvc.support.RedirectAttributes)")
    public Object alterAttribute(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("AOP <-----------");
        String targetClass = joinPoint.getTarget().getClass().getSimpleName();
        String targetMethod = joinPoint.getSignature().getName();
        String arg = joinPoint.getArgs()[0].toString();

        System.out.printf("Executing {%s}.{%s} with argument: {%s}%n", targetClass, targetMethod, arg);

        List<String> response = (List<String>) joinPoint.proceed();

        System.out.printf("Method returned: {%s}", response);

        return response;
    }

//    @Before("execution( * org.springframework.web.servlet.mvc.support.RedirectAttributes.addFlashAttribute(String, Object))")
//    public void testic(){
//        System.out.println("------> BEFORE BRE<------");
//    }
//
//    @Before("execution( * org.springframework.web.servlet.mvc.support.RedirectAttributes.*(..))")
//    public void testic4(){
//        System.out.println("------> BEFORE 4<------");
//    }
//
//    @Before("within(org.springframework.web.servlet.mvc.support.RedirectAttributes)")
//    public void testic5(){
//        System.out.println("------> BEFORE 5<------");
//    }
//
//    @Before("execution( * org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap.addFlashAttribute(String, Object))")
//    public void testic3(){
//        System.out.println("------> BEFORE 3<------");
//    }
//
//    @Before("execution( * rs.fon.plannerx.infrastructure.web.account.RegisterController.getPage(..))")
//    public void testic2(){
//        System.out.println("------> BEFORE 2<------");
//    }

//    @Before("execution( * rs.fon.plannerx.infrastructure.web.messages.FlashMessageFactory.createFlashMessage(..))")
//    public void testic2(){
//        System.out.println("------> BEFORE 2 bato<------");
//    }
}
