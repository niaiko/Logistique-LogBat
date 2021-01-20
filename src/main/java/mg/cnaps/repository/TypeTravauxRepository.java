package mg.cnaps.repository;


//
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import mg.cnaps.models.TypeTravauxMod;

@CrossOrigin("*")
public interface TypeTravauxRepository extends JpaRepository<TypeTravauxMod,Integer> {
	
	@Query(value = "select u from TypeTravauxMod u where idtravaux = ?1 ")
	TypeTravauxMod getdonneetypetravaux(int idtravaux);
}
