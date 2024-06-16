package PDFGeneration.Service;

import PDFGeneration.DTO.GetAllPDF;
import PDFGeneration.DTO.InvoiceInput;
import PDFGeneration.Domain.PDFGeneration;
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

	@Autowired
	public void setPDFRepository(PDFRepository pr){
		this.pr = pr;
	}

	public List<GetAllPDF> getAllPdf(int custId, int userId, int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo , 10);
		Page<PDFGeneration> paginatedResult= pr.findByUserIdAndCustId(custId , userId , pageRequest);
		if(pageNo > paginatedResult.getTotalPages()){
			log.error("Requested Page Does Not Exists :: {}" , pageNo);
			throw new NoMoreOutputsException("Requested Page Does Not Exists");
		}
		ArrayList<GetAllPDF> getAllPDFArrayList = new ArrayList<>();
		for (PDFGeneration pdf : paginatedResult.getContent()){
			GetAllPDF getAllPDF = new GetAllPDF();
			getAllPDF.setInvoicePDF("INVOICE NAME");
		}
		return null;
	}

	public byte[] createPdf(int userId, int custId, InvoiceInput invoiceInput) {
	return null;
	}
}


