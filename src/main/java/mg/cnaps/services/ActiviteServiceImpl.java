package mg.cnaps.services;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mg.cnaps.models.ActiviteMod;
import mg.cnaps.repository.ActiviteRepository;
@Service
public class ActiviteServiceImpl implements ActiviteService{
	
	public static int max=50;
	
	@Autowired
	ActiviteRepository repository;

	@Override
	public void save(ActiviteMod cou) {
		repository.save(cou);
		
	}

	@Override
	public ActiviteMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActiviteMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(ActiviteMod entity) {
		repository.delete(entity);
		
	}

	@Override
	public int nombrepage() {
		return (int)(repository.count()/max)+1;
	}

	@Override
	public long seqDemande() {
		return repository.seqDemande();
	}
	
	@Override
	public List<ActiviteMod> getActiviteByDateDebutFin(Date dateDebut, Date dateFin){
		return repository.getAciviteByDateDebutFin(dateDebut, dateFin);
		
	}
	
	@Override
	public List<ActiviteMod> getActiviteByReference(int reference, String typetravaux, Pageable pageable){
		return repository.findbyReferenceAndTypetravaux(reference,typetravaux, pageable);
	}
	
	@Override
	public List<ActiviteMod> getActiviteByReference1(String typetravaux, Pageable pageable){
		return repository.findbyReferenceAndTypetravaux1(typetravaux, pageable);
	}

	@Override
	public List<ActiviteMod> listeactiviteencours() {
		
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public int countactivitebyid(int iddmdlobat) {
		// TODO Auto-generated method stub
		return repository.countactivitebyid(iddmdlobat);
	}

	@Override
	public List<ActiviteMod> findallactivite(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findallactivite( pageable);
	}
	
	
	
//	@Override
//	public List<CsLogMod> getByDestinataireDateCou(String destinataire, Date dateInsert) {
//		return repository.getByDestinataireDateCou(destinataire, dateInsert);
//	}

}
