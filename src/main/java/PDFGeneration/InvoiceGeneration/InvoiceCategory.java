package PDFGeneration.InvoiceGeneration;

import PDFGeneration.DTO.InvoiceInput;
import PDFGeneration.Domain.PDFTemplate;

@FunctionalInterface
public interface InvoiceCategory {

     byte[] generateInvoicePDF(InvoiceInput input , PDFTemplate pdfTemplate);

}
