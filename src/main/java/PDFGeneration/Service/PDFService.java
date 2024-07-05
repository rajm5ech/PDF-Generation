package PDFGeneration.Service;

import PDFGeneration.Configuration.InvoiceConfig;
import PDFGeneration.DTO.GetAllPDF;
import PDFGeneration.DTO.InvoiceInput;
import PDFGeneration.DTO.InvoiceResponse;
import PDFGeneration.Domain.PDFGeneration;
import PDFGeneration.Domain.PDFTemplate;
import PDFGeneration.Exception.InputInvalid;
import PDFGeneration.Exception.PDFGenerationFailed;
import PDFGeneration.InvoiceGeneration.InvoiceCategory;
import PDFGeneration.Repository.PDFRepository;
import PDFGeneration.Repository.PDFTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.results.NoMoreOutputsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
public class PDFService {
    private PDFRepository pr;
    private InvoiceConfig ic;
    private PDFTemplateRepository ptr;

    @Autowired
    public void setPtr(PDFTemplateRepository ptr) {
        this.ptr = ptr;
    }

    @Autowired
    public void setIc(InvoiceConfig ic) {
        this.ic = ic;
    }

    @Autowired
    public void setPDFRepository(PDFRepository pr) {
        this.pr = pr;
    }

    public List<GetAllPDF> getAllPdf(int custId, int userId, int pageNo) {
        try {
            PageRequest pageRequest = PageRequest.of(pageNo, 10);
            Page<PDFGeneration> paginatedResult = pr.findByUserIdAndCustId(custId, userId, pageRequest);
            if (pageNo >= paginatedResult.getTotalPages()) {
                log.error("Requested Page Does Not Exists :: {}", pageNo);
                throw new NoMoreOutputsException("Requested Page Does Not Exists");
            }
            ArrayList<GetAllPDF> getAllPDFArrayList = new ArrayList<>();
            for (PDFGeneration pdf : paginatedResult.getContent()) {
                GetAllPDF getAllPDF = new GetAllPDF();
                getAllPDF.setCustomerName(pdf.getCustomerName());
                getAllPDF.setCreatedDate(String.valueOf(pdf.getCreatedDate()));
                getAllPDF.setInvoiceName(pdf.getInvoiceName());
                getAllPDFArrayList.add(getAllPDF);
            }
            return getAllPDFArrayList;
        } catch (Exception ex) {
            log.error("Invalid Request custId , userId , pageNo :: {} {} {}", custId, userId, pageNo);
            throw new InputInvalid("Failed to retrieve Invoice for given inputs");
        }

    }

    public InvoiceResponse createPdf(int userId, int custId, InvoiceInput invoiceInput) {
        try {
            InvoiceResponse invoiceResponse = new InvoiceResponse();
            InvoiceCategory invoiceCategory = ic.decideInvoiceCategory(invoiceInput.getClientGSTIN());
            PDFTemplate pdfTemplate = ptr.findByTemplateID(userId, Integer.parseInt(invoiceInput.getPdfTemplateId()));
            byte[] generatedInvoicePDF = invoiceCategory.generateInvoicePDF(invoiceInput, pdfTemplate);
            PDFGeneration pdfGeneration = new PDFGeneration();
            pdfGeneration.setUserId(userId);
            pdfGeneration.setCustId(custId);
            pdfGeneration.setCustomerName(invoiceInput.getClientName());
            pdfGeneration.setInvoicePDF(generatedInvoicePDF);
            pdfGeneration.setCreatedDate(Calendar.getInstance().getTime());
            pdfGeneration.setInvoiceName(invoiceInput.invoiceName);
            pr.save(pdfGeneration);
            invoiceResponse.setInvoicePDF(generatedInvoicePDF);
            return invoiceResponse;
        } catch (Exception ex) {
            log.error("PDF generation failed");
            throw new PDFGenerationFailed("PDF generation failed");
        }
    }
}


