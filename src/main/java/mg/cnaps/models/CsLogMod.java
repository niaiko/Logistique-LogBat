package mg.cnaps.models;

//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "cs_logbat")
public class CsLogMod{
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_cs_logbat", length = 100, nullable = false )
	private int idcslog;
	
	@Column(name = "id_dmd_logbat")
	private int iddmdlogbat;
	
	@Column(name = "libelle", length = 100)
	private String libellecs;

	public int getIdcslog() {
		return idcslog;
	}

	public void setIdcslog(int idcslog) {
		this.idcslog = idcslog;
	}

	public int getIddmdlogbat() {
		return iddmdlogbat;
	}

	public void setIddmdlogbat(int iddmdlogbat) {
		this.iddmdlogbat = iddmdlogbat;
	}

	public String getLibellecs() {
		return libellecs;
	}

	public void setLibellecs(String libellecs) {
		this.libellecs = libellecs;
	}
	
	
}
