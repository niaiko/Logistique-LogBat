package mg.cnaps.services;

//import java.sql.Date;
//import java.util.List;
import mg.cnaps.models.CsLogMod;


public interface CsLogService extends CRUDService<CsLogMod> {
	 long seqDemande();
}
