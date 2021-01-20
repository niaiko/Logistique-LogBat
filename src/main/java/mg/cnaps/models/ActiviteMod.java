package mg.cnaps.models;




import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "activite")
public class ActiviteMod{
	
	

	
	
	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="MY_SEQ_GEN", initialValue=205, allocationSize=12)
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator="seq-gen")
	@Column(name = "id_activite", length = 100, nullable = false )
	private Integer idactivite;
	
	@Column(name = "id_cs_logbat")
	private Integer idcslogbat;
	
	@Column(name = "date_debut")
	private Date datedebut;
	
	@Column(name = "date_fin")
	private Date datefin;

	@Column(name = "nbr_travaux")
	private Integer nbrtravaux;
	
	@Column(name = "id_individu")
	private String idindividu;
	
	@Column(name = "nbr_individu")
	private Integer nbrindividu;
	
	@Column(name = "reference")
	private String reference;
	
	@Column(name = "lieu")
	private String lieu;
	
	@Column(name = "type_travaux")
	private String typetravaux;
	
	@Column(name = "id_dmd_logbat")
	private Integer iddmdlogbat;
	
	@Column(name = "id_statue")
	private Integer idstatue;
	
	@Column(name = "id_travaux")
	private Integer idtravaux;
	
	@Transient
	DmdTravauxMod demande;
	
	@Transient
	String travaux;
	
	@Transient
	Integer page;
	
	
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getTravaux() {
		return travaux;
	}

	public void setTravaux(String travaux) {
		this.travaux = travaux;
	}

	public Integer getIdtravaux() {
		return idtravaux;
	}

	public void setIdtravaux(Integer idtravaux) {
		this.idtravaux = idtravaux;
	}

	public DmdTravauxMod getDemande() {
		return demande;
	}

	public void setDemande(DmdTravauxMod demande) {
		
		
		this.demande = demande;
	}

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
