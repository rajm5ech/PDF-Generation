package PDFGeneration.Repository;

import PDFGeneration.Domain.PDFGeneration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(isolation = Isolation.SERIALIZABLE)
public interface PDFRepository extends JpaRepository<PDFGeneration, Integer> {

    @Query("SELECT e FROM PDFGeneration e WHERE e.userId = :userId AND e.custId = :custId ORDER BY e.createdDate DESC")
    Page<PDFGeneration> findByUserIdAndCustId(@Param("userId") int custId, @Param("custId") int userId, PageRequest pageRequest);


}
