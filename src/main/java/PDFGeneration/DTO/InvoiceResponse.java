package PDFGeneration.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InvoiceResponse {
    private Date timeStamp;
    private byte[] invoicePDF;
    private int userId;
    private int custId;
    private String invoiceName;

}
