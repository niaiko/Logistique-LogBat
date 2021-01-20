package mg.cnaps.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
//import java.sql.Date;
//import java.util.List;
//
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import mg.cnaps.models.DmdTravauxMod;

@CrossOrigin("*")
public interface DmdTravauxRepository extends JpaRepository<DmdTravauxMod, Integer> {
	@Query(value = "select nextval('\"RFM\".dmd_travaux_id_dmd_logbat_seq')", nativeQuery = true)
	long seqDemande();

	@Transactional
	@Modifying
	@Query(value = "update DmdTravauxMod u set  u.validationtrav = 'true' where u.iddmdlogbat = ?1 ")
	public void updatevalidationdmd(int iddmdlogbat);

	@Query("select u from DmdTravauxMod u where u.validationtrav = 'true'")
	List<DmdTravauxMod> listevalidationdmdlog();

	@Query("select u from DmdTravauxMod u where u.validationtrav = 'false' ")
	List<DmdTravauxMod> listenonvalidationdmdlog(Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "update ActiviteMod u set u.idstatue = 2 where u.iddmdlogbat = ?1 ")
	public void updatestatuedmd(int iddmdlogbat);

	@Transactional
	@Modifying
	@Query(value = "update ActiviteMod u set u.idstatue = 1, datefin = ?2 where u.iddmdlogbat = ?1 ")
	public void updateactiviteencours(int idactivite, Date datefin);

	DmdTravauxMod findByIddmdlogbat(int iddmdlogbat);
}
