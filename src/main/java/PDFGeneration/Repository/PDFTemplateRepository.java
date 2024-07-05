package PDFGeneration.Repository;

import PDFGeneration.Domain.PDFTemplate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(isolation = Isolation.SERIALIZABLE)
public class PDFTemplateRepository {
    @PersistenceContext
    EntityManager em;

    public PDFTemplate findByTemplateID(int userID, Integer templateID) {
        Query query = em.createNativeQuery("PDFTemplate.findById", PDFTemplate.class);
        query.setParameter("userID", userID);
        query.setParameter("pdfTemplateID", templateID);
        return (PDFTemplate) query.getSingleResult();
    }
}
