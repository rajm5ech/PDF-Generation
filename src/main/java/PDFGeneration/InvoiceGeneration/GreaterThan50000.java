package PDFGeneration.InvoiceGeneration;

import PDFGeneration.DTO.InvoiceInput;
import PDFGeneration.Domain.PDFTemplate;

public class GreaterThan50000 implements InvoiceCategory{

	@Override
	public byte[] generateInvoicePDF(InvoiceInput input, PDFTemplate pdfTemplate) {
		
		return null;
	}

}