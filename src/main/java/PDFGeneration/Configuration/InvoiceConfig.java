package PDFGeneration.Configuration;

import PDFGeneration.InvoiceGeneration.NonRegisteredAndValueMoreThan50000;
import PDFGeneration.InvoiceGeneration.RegisteredClient;
import PDFGeneration.InvoiceGeneration.InvoiceCategory;
import org.springframework.stereotype.Component;

@Component
public class InvoiceConfig {

    public InvoiceCategory decideInvoiceCategory(String clientGSTIN) {
        if (clientGSTIN == null) {
            return new RegisteredClient();
        } else {
            return new NonRegisteredAndValueMoreThan50000();
        }
    }

}
