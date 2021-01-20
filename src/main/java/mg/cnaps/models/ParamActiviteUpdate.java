package mg.cnaps.models;




import java.sql.Date;

import javax.persistence.Id;
public class ParamActiviteUpdate{
	
	
	@Id
	private Integer idactivite;
	private Integer idcslogbat;
	private Date datedebut;
	private Date datefin;
	private Integer nbrtravaux;
	private String idindividu;
	private Integer nbrindividu;
	private String reference;
	private String lieu;
	private String typetravaux;
    private Integer iddmdlogbat;
	private Integer idstatue;

	public Integer getIddmdlogbat() {
		return iddmdlogbat;
	}

	public void setIddmdlogbat(Integer iddmdlogbat) {
		this.iddmdlogbat = iddmdlogbat;
	}

	public Integer getIdstatue() {
		return idstatue;
	}

	public void setIdstatue(Integer idstatue) {
		this.idstatue = idstatue;
	}

	public Integer getIdactivite() {
		return idactivite;
	}

	public void setIdactivite(Integer idactivite) {
		this.idactivite = idactivite;
	}

	public Integer getIdcslogbat() {
		return idcslogbat;
	}

	public void setIdcslogbat(Integer idcslogbat) {
		this.idcslogbat = idcslogbat;
	}

	public Date getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public Integer getNbrtravaux() {
		return nbrtravaux;
	}

	public void setNbrtravaux(Integer nbrtravaux) {
		this.nbrtravaux = nbrtravaux;
	}

	public String getIdindividu() {
		return idindividu;
	}

	public void setIdindividu(String idindividu) {
		this.idindividu = idindividu;
	}

	public Integer getNbrindividu() {
		return nbrindividu;
	}

	public void setNbrindividu(Integer nbrindividu) {
		this.nbrindividu = nbrindividu;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getTypetravaux() {
		return typetravaux;
	}

	public void setTypetravaux(String typetravaux) {
		this.typetravaux = typetravaux;
	}
}
