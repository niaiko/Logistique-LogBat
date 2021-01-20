package mg.cnaps.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

//import java.sql.Date;
//import java.util.List;
import mg.cnaps.models.DmdTravauxMod;


public interface DmdTravauxService extends CRUDService<DmdTravauxMod> {
	 long seqDemande();
	 public void updatevalidationdmd(int iddmdlogbat);
	 List<DmdTravauxMod> listevalidationdmdlog();
	 List<DmdTravauxMod> listenonvalidationdmdlog(Pageable pageable);
	 public void updatestatuedmd(int iddmdlogbat);
	 public void updateactiviteencours(int idactivite, Date datefin);
	 DmdTravauxMod getBylistebyid(int iddmdlogbat);
}
