package PDFGeneration.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import PDFGeneration.InvoiceGeneration.GreaterThan50000;
import PDFGeneration.InvoiceGeneration.InvoiceCategory;
import PDFGeneration.InvoiceGeneration.LessThan50000;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConfig {


	public InvoiceCategory decideInvoiceType(String totalAmount) {
		if(totalAmount.equals("50000")) {
			return new GreaterThan50000();
		}
		else {
			return new LessThan50000();
		}
	}
	
}
