package mg.cnaps.services;

import java.io.Serializable;
//import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.CsLogMod;
import mg.cnaps.repository.CsLogRepository;
@Service
public class CsLogServiceImpl implements CsLogService{
	
	public static int max=50;
	
	@Autowired
	CsLogRepository repository;

	@Override
	public void save(CsLogMod cou) {
		repository.save(cou);
		
	}

	@Override
	public CsLogMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CsLogMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(CsLogMod entity) {
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

//	@Override
//	public List<CsLogMod> getByDestinataireDateCou(String destinataire, Date dateInsert) {
//		return repository.getByDestinataireDateCou(destinataire, dateInsert);
//	}

}
