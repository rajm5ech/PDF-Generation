package PDFGeneration.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InvoiceResponse {
    public HttpStatus status;
    public byte[] invoicePDF;
    public String errorDescription;
}
