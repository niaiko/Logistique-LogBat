package mg.cnaps.services;


import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

//import java.sql.Date;
//import java.util.List;
import mg.cnaps.models.ActiviteMod;


public interface ActiviteService extends CRUDService<ActiviteMod> {
	 long seqDemande();
	 
	 List<ActiviteMod> getActiviteByDateDebutFin(Date dateDebut, Date dateFin);
	 
	 List<ActiviteMod> getActiviteByReference(int reference,String typetravaux,Pageable pageable);
	 
	 List<ActiviteMod> getActiviteByReference1(String typetravaux,Pageable pageable);
	 
	 List<ActiviteMod> listeactiviteencours();
	 
	 int countactivitebyid(int iddmdlobat);
	 
	 List<ActiviteMod> findallactivite(Pageable pageable);
}
