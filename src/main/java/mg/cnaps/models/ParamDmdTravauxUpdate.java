package mg.cnaps.models;

import java.sql.Date;

import javax.persistence.Id;

public class ParamDmdTravauxUpdate{
	
	
	@Id
	private Integer iddmdlogbat;
	private Date date;
	private String description;
	private String codeservice;
	private String reference;
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
