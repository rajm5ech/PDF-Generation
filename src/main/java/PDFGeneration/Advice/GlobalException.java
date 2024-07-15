package PDFGeneration.Advice;

import PDFGeneration.Exception.InputInvalid;
import PDFGeneration.Exception.PDFGenerationFailed;
import PDFGeneration.Exception.UserIdInvalid;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.sql.results.NoMoreOutputsException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserIdInvalid.class)
    public ResponseEntity<ProblemDetail> handleUnAuthorizedUser(UserIdInvalid ex, HttpServletRequest request) {
        ProblemDetail unAuthUserEX = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage());
        unAuthUserEX.setTitle("USER NOT AUTHORIZED");
        unAuthUserEX.setType(URI.create("problem/user-id-invalid"));
        unAuthUserEX.setDetail(ex.getLocalizedMessage());
        unAuthUserEX.setProperty("timeStamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unAuthUserEX);
    }

    @ExceptionHandler(value = InputInvalid.class)
    public ResponseEntity<ProblemDetail> handleInvalidInput(InputInvalid ex) {
        ProblemDetail invInputEX = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        invInputEX.setTitle("INVALID INPUT");
        invInputEX.setType(URI.create("problem/invalid-input"));
        invInputEX.setProperty("timeStamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invInputEX);
    }

    @ExceptionHandler(value = NoMoreOutputsException.class)
    public ResponseEntity<ProblemDetail> handlePageRequest(NoMoreOutputsException ex) {
        ProblemDetail paginationEX = ProblemDetail.forStatusAndDetail(HttpStatus.NO_CONTENT, ex.getLocalizedMessage());
        paginationEX.setTitle("NO MORE PAGES TO SHOW");
        paginationEX.setType(URI.create("problem/no-more-output"));
        paginationEX.setProperty("timeStamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(paginationEX);

    }

    @ExceptionHandler(value = PDFGenerationFailed.class)
    public ResponseEntity<ProblemDetail> handlePDFGenerationFailure(PDFGenerationFailed ex) {
        ProblemDetail pdfGenEX = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
        pdfGenEX.setTitle("ERROR IN INVOICE GENERATION");
        pdfGenEX.setType(URI.create("problem/pdf-gen-fail"));
        pdfGenEX.setProperty("timeStamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(pdfGenEX);
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail pdfGenInputValidEX = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        pdfGenInputValidEX.setTitle("INPUT FOR INVOICE GENERATION IS INVALID");
        pdfGenInputValidEX.setType(URI.create("problem/validation-failed"));
        pdfGenInputValidEX.setProperty("timeStamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        return ResponseEntity.badRequest().body(pdfGenInputValidEX);
    }
}
