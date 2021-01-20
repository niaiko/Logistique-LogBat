package mg.cnaps.repository;

//import java.sql.Date;
//import java.util.List;
//
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import mg.cnaps.models.CsLogMod;

@CrossOrigin("*")
public interface CsLogRepository extends JpaRepository<CsLogMod,Integer> {
	@Query(value = "select nextval('\"RFM\".seqdemande')", nativeQuery=true)
	long seqDemande();
	
//  recherche
//	@Query("select u from CourrierMod u where (lower(u.destinataire) like '%'||lower(?1)||'%' or ?1 is null ) and (u.dateInsert = ?2 or cast(?2 as date) is null)")
//	List<CsLogMod> getByDestinataireDateCou(String destinataire,Date dateInsert);


}
