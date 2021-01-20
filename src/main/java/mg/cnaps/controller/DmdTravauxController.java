package mg.cnaps.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import mg.cnaps.models.DmdTravauxMod;
import mg.cnaps.models.ReferenceParam;
import mg.cnaps.repository.DmdTravauxRepository;
import mg.cnaps.services.ActiviteService;
import mg.cnaps.services.DmdTravauxService;
import mg.cnaps.utils.DateUtil;
import mg.cnaps.utils.ReferenceUtil;

@RestController
@CrossOrigin("*")
public class DmdTravauxController {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(DmdTravauxController.class);
	@Autowired
	Producer producer;

	@Autowired
	DmdTravauxService service;

	@Autowired
	ActiviteService serviceact;

	@Autowired
	DmdTravauxRepository repository;

	@PostMapping(value = "/listealldmd")
	public ResponseEntity<Object> liste() {
		try {
			List<DmdTravauxMod> liste = repository.findAll();
			log.info("listecou: " + om.writeValueAsString(liste));
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/nbrpagedmd")
	public ResponseEntity<Object> nbrPages() {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	

	@PostMapping(value = "/ajoutdmdlogbat")
	public ResponseEntity<Object> ajout(@RequestBody DmdTravauxMod dmdTravaux) {
		try {
			log.info("insert--> : ");
			service.save(dmdTravaux);
			return new ResponseEntity<>(dmdTravaux, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/referenceDmdTravaux")
	public ResponseEntity<Object> demandeReference(@RequestBody ReferenceParam param) {
		try {
			resultat = ReferenceUtil.getReferenceDemande(param.getPrestation(), service.seqDemande(), param.getDr());
			return new ResponseEntity<>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/supprdmd")
	public void delete(@RequestBody DmdTravauxMod dmdT) {
		try {
			service.delete(dmdT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(value = "/updatevalidationdmdlogbat")
	public void update(@RequestBody DmdTravauxMod dvm) {
		try {
			log.info("insert222: " + om.writeValueAsString(dvm));
			service.updatevalidationdmd(dvm.getIddmdlogbat());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping(value = "/listevalidationdmdlog")
	public ResponseEntity<Object> listeValidationDemandeLog() {
		try {
			List<DmdTravauxMod> access = new ArrayList<>();
			List<DmdTravauxMod> io = service.listevalidationdmdlog();
			log.info(om.writeValueAsString(io));

			for (int i = 0; i < io.size(); i++) {
				int nbr = serviceact.countactivitebyid(io.get(i).getIddmdlogbat());
				log.info("te : " + nbr);
				if (nbr == 0) {

					access.add(io.get(i));
				}
			}
			log.info("Send: " + io);
			return new ResponseEntity<>(access, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/listenonvalidationdmdlog/{page}")
	public ResponseEntity<Object> listenonvalidedmdlog(@PathVariable int page) {
		try {
			Pageable pageable = new PageRequest(page - 1, 10);
			List<DmdTravauxMod> io = service.listenonvalidationdmdlog(pageable);
			log.info("vita v?");
			log.info("Send: " + io);
			return new ResponseEntity<>(io, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/updateactiviteencours")
	public ResponseEntity<Object> updateactiviteencours(@RequestBody DmdTravauxMod param) {
		try {
			service.updateactiviteencours(param.getIddmdlogbat(), DateUtil.sqlDateNow());
			return new ResponseEntity<>(param, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/updatestatuedmd")
	public ResponseEntity<Object> updatestatuedmd(@RequestBody DmdTravauxMod param) {
		try {
			service.updatestatuedmd(param.getIddmdlogbat());
			return new ResponseEntity<Object>(param, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
