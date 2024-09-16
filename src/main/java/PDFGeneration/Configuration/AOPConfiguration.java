package PDFGeneration.Configuration;

import PDFGeneration.Exception.UserIdInvalid;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AOPConfiguration {

    @AfterThrowing(pointcut = "execution(* PDFGeneration.Controller.PDFGenerationController.getAllPdf(..))", throwing = "ex")
    public void centralizeExceptionHandling(NullPointerException ex){
        log.info("Exception is caught"+ex.getMessage());
        throw new UserIdInvalid("User is unauthorized to visit the page");
    }
}
