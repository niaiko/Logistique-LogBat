package mg.cnaps.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import mg.cnaps.models.ActiviteMod;

@CrossOrigin("*")
public interface ActiviteRepository extends JpaRepository<ActiviteMod, Integer> {
	@Query(value = "select nextval('\"RFM\".activite_id_activite_seq')", nativeQuery = true)
	long seqDemande();

//	@Query(value = "select u from ActiviteMod u where date_debut = ?1 or date_fin = ?2 ")
//	List<ActiviteMod> getAciviteByDateDebutFin(Date dateDebut, Date dateFin);

	// Modifié par Tramaki
	@Query(value = "select u from ActiviteMod u where u.datedebut = ?1 or u.datefin = ?2 ")
	List<ActiviteMod> getAciviteByDateDebutFin(Date dateDebut, Date dateFin);

//  recherche
//	@Query("select u from CourrierMod u where (lower(u.destinataire) like '%'||lower(?1)||'%' or ?1 is null ) and (u.dateInsert = ?2 or cast(?2 as date) is null)")
//	List<CsLogMod> getByDestinataireDateCou(String destinataire,Date dateInsert);

	@Query(value = "select u from ActiviteMod u  where u.idtravaux = ?1  and (u.typetravaux = ?2 or ?2 = '' )")
	List<ActiviteMod> findbyReferenceAndTypetravaux(int reference, String typetravaux, Pageable pageable);

	@Query(value = "select u from ActiviteMod u  where (u.typetravaux = ?1 or ?1 = '' ) order by u.idactivite ASC")
	List<ActiviteMod> findbyReferenceAndTypetravaux1(String typetravaux, Pageable pageable);

	@Query(value = "select u from ActiviteMod u  where u.idstatue = 1")
	Page<ActiviteMod> listeactiviteencours(Pageable pageable);

	@Query(value = "select u from ActiviteMod u  where u.idstatue = 2")
	Page<ActiviteMod> listeactiviteTerminer(Pageable pageable);

	@Query(value = "select COUNT(u) from ActiviteMod u  where u.iddmdlogbat = ?1 ")
	int countactivitebyid(int iddmdlobat);

	@Query(value = "select u from ActiviteMod u order by u.idactivite ASC")
	List<ActiviteMod> findallactivite(Pageable pageable);
}
