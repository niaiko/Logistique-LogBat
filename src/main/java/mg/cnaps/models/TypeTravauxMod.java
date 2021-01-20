package mg.cnaps.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "type_travaux")
public class TypeTravauxMod{
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_travaux", length = 100, nullable = false )
	private Integer idtravaux;
	
	@Column(name = "libelle_travaux")
	private String libelletravaux;

	public Integer getIdtravaux() {
		return idtravaux;
	}

	public void setIdtravaux(Integer idtravaux) {
		this.idtravaux = idtravaux;
	}

	public String getLibelletravaux() {
		return libelletravaux;
	}

	public void setLibelletravaux(String libelletravaux) {
		this.libelletravaux = libelletravaux;
	}
	
}
