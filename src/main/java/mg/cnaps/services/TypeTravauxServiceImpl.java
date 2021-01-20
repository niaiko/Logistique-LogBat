package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mg.cnaps.models.TypeTravauxMod;
import mg.cnaps.repository.TypeTravauxRepository;
@Service
public class TypeTravauxServiceImpl implements TypeTravauxService{
	
	public static int max=50;
	
	@Autowired
	TypeTravauxRepository repository;

	@Override
	public void save(TypeTravauxMod cou) {
		repository.save(cou);
		
	}

	@Override
	public TypeTravauxMod getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TypeTravauxMod> getAll(int page) {
		return (repository.findAll(new PageRequest(page-1,max))).getContent();
	}

	@Override
	public void delete(TypeTravauxMod entity) {
		repository.delete(entity);
		
	}

	@Override
	public int nombrepage() {
		return (int)(repository.count()/max)+1;
	}

	@Override
	public TypeTravauxMod getdonneetypetravaux(int idtravaux) {
		// TODO Auto-generated method stub
		return repository.getdonneetypetravaux( idtravaux);
	}

}
