package PDFGeneration.Configuration;

import PDFGeneration.Exception.UserIdInvalid;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AOPConfiguration {
    @AfterThrowing(pointcut = "execution(* PDFGeneration.Repository.PDFRepository.*(..))", throwing = "ex")
    public void centralizeExceptionHandlingRepository(JoinPoint joinPoint , ArithmeticException ex){
        log.error("Exception is caught :: {} at method :: {}" , ex.getMessage() , joinPoint);
        throw new UserIdInvalid("User is unauthorized to visit the page");
    }
    @AfterThrowing(pointcut = "execution(* PDFGeneration.Controller.PDFGenerationController.*(..))", throwing = "ex")
    public void centralizeExceptionHandling(JoinPoint joinPoint , UserIdInvalid ex){
        log.info("Exception is caught :: {} at method :: {}" , ex.getMessage() , joinPoint);
        throw new UserIdInvalid("User is unauthorized to visit the page");
    }
    @AfterThrowing(pointcut = "execution(* PDFGeneration.Service.PDFService.*(..))", throwing = "ex")
    public void centralizeExceptionHandlingService(JoinPoint joinPoint , UserIdInvalid ex) {
        log.error("Exception is caught :: {} at method :: {}", ex.getMessage(), joinPoint);
        throw new UserIdInvalid("User is unauthorized to visit the page");
    }


    }



