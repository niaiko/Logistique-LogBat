package mg.cnaps.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.kafka.Producer;
import mg.cnaps.models.CsLogMod;
import mg.cnaps.services.CsLogService;

@RestController
@CrossOrigin("*")
public class CsLogController {
	
	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(CsLogController.class);
	@Autowired
	Producer producer;

	@Autowired
	CsLogService service;
	
	@PostMapping(value="/listeallcslogbat")
	public ResponseEntity<Object> liste(int page){
		try {
			List<CsLogMod> liste = service.getAll(page);
			log.info("listecou: "+om.writeValueAsString(liste));
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value="/nbrpagecslogbat")
	public ResponseEntity<Object> nombrePages(){
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			return new ResponseEntity<Object>(resultat, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value="/ajoutcslogbat")
	public CsLogMod save(@RequestBody CsLogMod clm){
		try {
			service.save(clm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping(value="/supprcslogbat")
	public void delete(@RequestBody CsLogMod clm) {
		try {
			service.delete(clm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
