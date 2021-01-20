package mg.cnaps.services;

import java.io.Serializable;
import java.util.List;

//import mg.cnaps.models.ServiceCouMod;

public interface CRUDService<E> {

	void save(E entity);

	E getById(Serializable id);

	List<E> getAll(int page);

	void delete(E entity);
	
	int nombrepage();

}
