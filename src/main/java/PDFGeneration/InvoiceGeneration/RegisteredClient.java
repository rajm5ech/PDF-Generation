package PDFGeneration.InvoiceGeneration;

import PDFGeneration.DTO.InvoiceInput;
import PDFGeneration.Domain.PDFTemplate;

public class RegisteredClient implements InvoiceCategory {

    @Override
    public byte[] generateInvoicePDF(InvoiceInput input , PDFTemplate pdfTemplate) {

        return null;
    }

}
