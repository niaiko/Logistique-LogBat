package mg.cnaps.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Statue_travaux")
public class StatueTravauxMod{
	
	@Id
	@Column(name = "id_statue", length = 100, nullable = false )
	private Integer idstatue;
	
	@Column(name = "libelle")
	private String libelle;
	
}
