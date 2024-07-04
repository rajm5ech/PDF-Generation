package PDFGeneration.InvoiceGeneration;

import PDFGeneration.DTO.InvoiceInput;
import PDFGeneration.Domain.PDFTemplate;

@FunctionalInterface
public interface InvoiceCategory {

    public byte[] generateInvoicePDF(InvoiceInput input);

}
