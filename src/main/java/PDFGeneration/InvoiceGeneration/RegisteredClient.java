package PDFGeneration.InvoiceGeneration;

import PDFGeneration.DTO.InvoiceInput;
import PDFGeneration.Domain.PDFTemplate;
import PDFGeneration.Exception.PDFGenerationFailed;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.IIOException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegisteredClient implements InvoiceCategory {

    @Override
    public byte[] generateInvoicePDF(InvoiceInput input, PDFTemplate pdfTemplate) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document pdfDoc = new Document();
            PdfWriter.getInstance(pdfDoc, baos);
            pdfDoc.open();
            /* "TAX INVOICE" Heading*/
            Font h1FontAndSize = FontFactory.getFont(pdfTemplate.getH1Font(), pdfTemplate.getH1Size());
            Paragraph taxInvoice = new Paragraph("Tax Invoice", h1FontAndSize);
            int h1Alignment = pdfTemplate.getH1Position().equalsIgnoreCase("Left") ? Element.ALIGN_LEFT : (pdfTemplate.getH1Position().equalsIgnoreCase("Right") ? Element.ALIGN_RIGHT : Element.ALIGN_MIDDLE);
            taxInvoice.setAlignment(h1Alignment);
            pdfDoc.add(taxInvoice);
            /* "LOGO OF THE COMPANY" Heading*/
            Image companyLogo = Image.getInstance(pdfTemplate.getCompanyLogo());
            int logoAlignment = pdfTemplate.getLogoPosition().equalsIgnoreCase("Left") ? Element.ALIGN_LEFT : (pdfTemplate.getLogoPosition().equalsIgnoreCase("Right") ? Element.ALIGN_RIGHT : Element.ALIGN_MIDDLE);
            companyLogo.setAlignment(logoAlignment);
            int[] logoSize = pdfTemplate.getLogoSize();
            companyLogo.scaleToFit(logoSize[0], logoSize[1]);
            pdfDoc.add(companyLogo);


        } catch (DocumentException | IOException e) {
            throw new PDFGenerationFailed("PDF generation failed");
        }

        return null;
    }

}
