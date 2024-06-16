package PDFGeneration.Advice;

import PDFGeneration.DTO.InvoiceResponse;
import PDFGeneration.Exception.InputInvalid;
import PDFGeneration.Exception.UserIdInvalid;
import org.hibernate.sql.results.NoMoreOutputsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = UserIdInvalid.class)
    public ResponseEntity<InvoiceResponse> handleUnAuthorizedUser(UserIdInvalid ex){
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setStatus(HttpStatus.UNAUTHORIZED);
        invoiceResponse.setErrorDescription(ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(invoiceResponse);
    }

    @ExceptionHandler(value = InputInvalid.class)
    public ResponseEntity<InvoiceResponse> handleInvalidInput(InputInvalid ex){
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setStatus(HttpStatus.BAD_REQUEST);
        invoiceResponse.setErrorDescription(ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(invoiceResponse);
    }

    @ExceptionHandler(value = NoMoreOutputsException.class)
    public ResponseEntity<InvoiceResponse> handlePageRequest(NoMoreOutputsException ex){
        InvoiceResponse invoiceResponse = new InvoiceResponse();
        invoiceResponse.setStatus(HttpStatus.BAD_REQUEST);
        invoiceResponse.setErrorDescription(ex.getLocalizedMessage());
        return ResponseEntity.badRequest().body(invoiceResponse);

    }
}
