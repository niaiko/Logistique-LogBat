package mg.cnaps.services;

import java.io.Serializable;
import java.util.Date;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mg.cnaps.models.DmdTravauxMod;
import mg.cnaps.repository.DmdTravauxRepository;
@Service
public class DmdTravauxServiceImpl implements DmdTravauxService{
	
	public static int max=50;
	
	@Autowired
	DmdTravauxRepository repository;

	
	
	
	@Override
	public void save(DmdTravauxMod cou) {
		repository.save(cou);
		
	}

	@Override
	public DmdTravauxMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DmdTravauxMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}
	

	@Override
	public void delete(DmdTravauxMod entity) {
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
	public void updatevalidationdmd(int iddmdlogbat) {
		repository.updatevalidationdmd(iddmdlogbat);
	}

	@Override
	public List<DmdTravauxMod> listevalidationdmdlog() {
		return repository.listevalidationdmdlog();
	}

	@Override
	public List<DmdTravauxMod> listenonvalidationdmdlog(Pageable pageable) {
		return repository.listenonvalidationdmdlog(pageable);
	}
	
	@Override
	public void updatestatuedmd(int iddmdlogbat) {
		repository.updatestatuedmd(iddmdlogbat);
	}
	
	@Override
	public void updateactiviteencours(int idactivite, Date datefin){
		repository.updateactiviteencours(idactivite, datefin);
	}
	
	@Override
	public DmdTravauxMod getBylistebyid(int iddmdlogbat) {
		return repository.findByIddmdlogbat(iddmdlogbat);
	}
}
