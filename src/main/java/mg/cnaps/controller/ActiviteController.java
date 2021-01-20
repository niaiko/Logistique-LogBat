package mg.cnaps.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.kafka.Producer;
import mg.cnaps.models.ActiviteMod;
import mg.cnaps.models.ParamActivite;
import mg.cnaps.models.ParamActiviteMod;
import mg.cnaps.models.TypeTravauxMod;
import mg.cnaps.repository.ActiviteRepository;
import mg.cnaps.services.ActiviteService;
import mg.cnaps.services.DmdTravauxService;
import mg.cnaps.services.TypeTravauxService;
import mg.cnaps.utils.DateUtil;

@RestController
@CrossOrigin("*")
public class ActiviteController {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(ActiviteController.class);
	@Autowired
	Producer producer;

	@Autowired
	ActiviteService service;

	@Autowired
	DmdTravauxService servicedmd;

	@Autowired
	TypeTravauxService serviceType;

	@Autowired
	ActiviteRepository repository;

	// maka liste activités
	@PostMapping(value = "/listeallactivite/{page}")
	public ResponseEntity<Object> getAllListeActivite(@PathVariable int page) {

		try {
			log.info("pageliste get all: ");
			Pageable pageable = new PageRequest(page - 1, 10);
			List<ActiviteMod> liste = service.findallactivite(pageable);
			log.info("nbr liste : " + liste.size());
			for (int i = 0; i < liste.size(); i++) {
				TypeTravauxMod io = serviceType.getdonneetypetravaux(liste.get(i).getIdtravaux());
				liste.get(i).setTravaux(io.getLibelletravaux());
			}
			log.info("liste: " + om.writeValueAsString(liste));
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/listeactivitebyreference")
	public ResponseEntity<Object> listeByRef(@RequestBody ActiviteMod param) {
		try {
			log.info("reference: " + param.getReference());
			log.info("type travaux: " + param.getTypetravaux());
			log.info("id travaux: " + param.getIdtravaux());
			if (param.getIdtravaux() == 0) {
				log.info("ato io zw");
				List<ActiviteMod> liste = service.getActiviteByReference1(param.getTypetravaux(),
						new PageRequest(param.getPage() - 1, 10));
				log.info("liste tsisy idtravaux : " + om.writeValueAsString(liste));
				for (int i = 0; i < liste.size(); i++) {
					TypeTravauxMod io = serviceType.getdonneetypetravaux(liste.get(i).getIdtravaux());
					liste.get(i).setTravaux(io.getLibelletravaux());
				}
				log.info("liste activite" + om.writeValueAsString(liste));
				return new ResponseEntity<>(liste, HttpStatus.OK);
			} else {
				List<ActiviteMod> liste = service.getActiviteByReference(param.getIdtravaux(), param.getTypetravaux(),
						new PageRequest(param.getPage() - 1, 10));
				for (int i = 0; i < liste.size(); i++) {
					TypeTravauxMod io = serviceType.getdonneetypetravaux(liste.get(i).getIdtravaux());
					log.info("liste misy idtravaux : " + om.writeValueAsString(liste));
					liste.get(i).setTravaux(io.getLibelletravaux());
				}
				log.info("liste activite" + om.writeValueAsString(liste));
				return new ResponseEntity<>(liste, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// maka nombre de pages
	@PostMapping(value = "/nbrpageactivite")
	public ResponseEntity<Object> nombrePages() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Ajout activité
	@PostMapping(value = "/ajoutactivitelogbat")
	public ResponseEntity<Object> saveActivite(@RequestBody ActiviteMod param) {
		try {
			ActiviteMod io = new ActiviteMod();
			io.setIdactivite(Integer.valueOf(String.valueOf(service.seqDemande())));
			io.setDatedebut(DateUtil.sqlDateNow());
			io.setIdcslogbat(param.getIdcslogbat());
			io.setNbrtravaux(param.getNbrtravaux());
			io.setIdindividu(param.getIdindividu());
			io.setNbrindividu(param.getNbrindividu());
			io.setReference(param.getReference());
			io.setLieu(param.getLieu());
			io.setTypetravaux(param.getTypetravaux());
			io.setIdstatue(2);
			io.setIddmdlogbat(param.getIddmdlogbat());
			io.setIdtravaux(param.getIdtravaux());
			service.save(io);
			return new ResponseEntity<>(io, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	// delete activity
	@DeleteMapping(value = "/suppractivite")
	public void deleteActivite(@RequestBody ActiviteMod am) {
		try {
			service.delete(am);
			log.info("Données supprimer");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(value = "rechercheactivite")
	public ResponseEntity<Object> recherche() {
		try {
			ParamActiviteMod param = new ParamActiviteMod();
			List<ActiviteMod> access = service.getActiviteByDateDebutFin(param.getDatedebut(), param.getDatefin());
			resultat = om.writeValueAsString(access);
			log.info("res: " + resultat);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/listeactiviteencours")
	public ResponseEntity<Object> listeActiviteEnCours(@RequestBody ParamActivite activite) {
		try {
			Pageable pageable = new PageRequest(activite.getPage(), activite.getSize());
			Page<ActiviteMod> page = repository.listeactiviteencours(pageable);
			activite.setActivite(page.getContent());
			activite.setNbrPage(page.getTotalPages());
			resultat = om.writeValueAsString(activite);
			log.info("nbrligne: " + resultat);
			return new ResponseEntity<>(resultat, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/listeactiviteterminer")
	public ResponseEntity<Object> terminer(@RequestBody ParamActivite activite) {
		try {
			Pageable pageable = new PageRequest(activite.getPage(), activite.getSize());
			Page<ActiviteMod> page = repository.listeactiviteTerminer(pageable);
			activite.setActivite(page.getContent());
			activite.setNbrPage(page.getTotalPages());
			return new ResponseEntity<>(activite, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
