package PDFGeneration.Repository;

import PDFGeneration.Domain.PDFTemplate;
import PDFGeneration.Exception.PdfTemplateNotFound;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Repository
@Transactional(isolation = Isolation.SERIALIZABLE)
public class PDFTemplateRepository {
    @PersistenceContext
    EntityManager em;

    public PDFTemplate findByTemplateID(int userID, Integer templateID) {
        try{
            Query query = em.createNativeQuery("PDFTemplate.findById", PDFTemplate.class);
            query.setParameter("userID", userID);
            query.setParameter("pdfTemplateID", templateID);
            return (PDFTemplate) query.getSingleResult();
        }
        catch(NoResultException ex){
            log.error("PDF Template not found for userID : {} , templateId : {}", userID ,templateID);
            throw new PdfTemplateNotFound("PDF Template not found");
        }
    }
}
