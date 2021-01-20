package mg.cnaps.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "dmd_travaux")
public class DmdTravauxMod{
	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_dmd_logbat", nullable = false )
	private Integer iddmdlogbat;
	
	@Column(name = "date")
	private Date date;

	@Column(name = "description")
	private String description;
	
	@Column(name = "code_service")
	private String codeservice;
	
	@Column(name = "reference")
	private String reference;
	
	@Column(name = "validation_trav")
	private Boolean validationtrav;

	public Boolean getValidationtrav() {
		return validationtrav;
	}

	public void setValidationtrav(Boolean validationtrav) {
		this.validationtrav = validationtrav;
	}

	public Integer getIddmdlogbat() {
		return iddmdlogbat;
	}

	public void setIddmdlogbat(Integer iddmdlogbat) {
		this.iddmdlogbat = iddmdlogbat;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCodeservice() {
		return codeservice;
	}

	public void setCodeservice(String codeservice) {
		this.codeservice = codeservice;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
}
