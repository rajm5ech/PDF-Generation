package PDFGeneration.InvoiceGeneration;

import PDFGeneration.DTO.InvoiceInput;
import PDFGeneration.Domain.PDFTemplate;

public interface InvoiceCategory {
	
	public byte[] generateInvoicePDF(InvoiceInput input , PDFTemplate pdfTemplate);

}
