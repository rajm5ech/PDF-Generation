package PDFGeneration.Advice;

import PDFGeneration.DTO.InvoiceResponse;
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
import java.util.Calendar;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserIdInvalid.class)
    public ResponseEntity<ProblemDetail> handleUnAuthorizedUser(UserIdInvalid ex , HttpServletRequest request) {
        ProblemDetail unAuthUser = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED,ex.getLocalizedMessage());
        unAuthUser.setType(URI.create("/getAllPDF/{id}"));
        unAuthUser.setStatus(HttpStatus.UNAUTHORIZED);
        unAuthUser.setDetail(ex.getLocalizedMessage());
        unAuthUser.setProperty("timeStamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unAuthUser);
    }

    @ExceptionHandler(value = InputInvalid.class)
    public ResponseEntity<InvoiceResponse> handleInvalidInput(InputInvalid ex) {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setStatus(HttpStatus.BAD_REQUEST);
        invoiceResponse.setErrorDescription(ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(invoiceResponse);
    }

    @ExceptionHandler(value = NoMoreOutputsException.class)
    public ResponseEntity<InvoiceResponse> handlePageRequest(NoMoreOutputsException ex) {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setStatus(HttpStatus.BAD_REQUEST);
        invoiceResponse.setErrorDescription(ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(invoiceResponse);

    }

    @ExceptionHandler(value = PDFGenerationFailed.class)
    public ResponseEntity<InvoiceResponse> handlePDFGenerationFailure(PDFGenerationFailed ex) {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        invoiceResponse.setErrorDescription(ex.getLocalizedMessage());
        return ResponseEntity.internalServerError().body(invoiceResponse);
    }

    @Override
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setStatus(HttpStatus.BAD_REQUEST);
        invoiceResponse.setErrorDescription("Invoice Generation input is invalid.");
        return ResponseEntity.badRequest().body(invoiceResponse);
    }
}
