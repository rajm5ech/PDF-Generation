package PDFGeneration.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class GetAllPDF {
    private String customerName;
    private byte[] invoicePDF;
    private String createdDate;
}