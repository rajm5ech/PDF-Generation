package PDFGeneration.Controller;

import java.util.List;

import PDFGeneration.DTO.GetAllPDF;
import PDFGeneration.DTO.InvoiceInput;
import PDFGeneration.Exception.UserIdInvalid;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PDFGeneration.Service.PDFService;

@Slf4j
@RestController
@RequestMapping(value = "/pdf")
public class PDFGenerationController {
    private PDFService ps;

    @Autowired
    public void setPDFService(PDFService ps) {
        this.ps = ps;
    }

    /* Get all the PDF of the USER using the userId and the custId for the particular customer */
    @GetMapping(value = "/getAllPdf/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetAllPDF>> getAllPdf(@RequestParam(name = "pageNo", required = true, defaultValue = "0") int pageNo, @PathVariable(name = "id") int custId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");
        if (userId == null || userId.isEmpty()) {
            log.error("NO USER AND SESSION FOUND");
            throw new UserIdInvalid("User ID not found.");
        }
        log.info("CLIENT IP ADDRESS {}", request.getRemoteAddr());
        return ResponseEntity.ok().body(ps.getAllPdf(custId, Integer.parseInt(userId), pageNo));
    }

    /* Create Invoice PDF for a customer with all the details coming from Billing Service */
    @PostMapping(value = "/createPdf/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> createPdf(@PathVariable(name = "id") int custId, HttpServletRequest request, @Valid @RequestBody InvoiceInput invoiceInput) {
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");
        if (userId == null || userId.isEmpty()) {
            log.error("NO USER AND SESSION FOUND ");
            throw new UserIdInvalid("User ID not found.");
        }
        log.info("CLIENT IP  ADDRESS {}", request.getRemoteAddr()); // To be moved in Security Config
        return ResponseEntity.ok().body(ps.createPdf(Integer.parseInt(userId), custId, invoiceInput));
    }

}
