package PDFGeneration.Service;

import PDFGeneration.Configuration.InvoiceConfig;
import PDFGeneration.DTO.GetAllPDF;
import PDFGeneration.DTO.InvoiceInput;
import PDFGeneration.Domain.PDFGeneration;
import PDFGeneration.Exception.InputInvalid;
import PDFGeneration.Exception.PDFGenerationFailed;
import PDFGeneration.InvoiceGeneration.InvoiceCategory;
import PDFGeneration.Repository.PDFRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.results.NoMoreOutputsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PDFService {
    private PDFRepository pr;
    private InvoiceConfig ic;

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

    public byte[] createPdf(int userId, int custId, InvoiceInput invoiceInput) {
        try {
            InvoiceCategory invoiceCategory = ic.decideInvoiceCategory(invoiceInput.getClientGSTIN());
            return invoiceCategory.generateInvoicePDF(invoiceInput);
        } catch (Exception ex) {
            log.error("PDF generation failed");
            throw new PDFGenerationFailed("PDF generation failed");
        }
    }
}


