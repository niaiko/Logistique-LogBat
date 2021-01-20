package mg.cnaps.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.kafka.Producer;
import mg.cnaps.models.TypeTravauxMod;
import mg.cnaps.services.TypeTravauxService;

@RestController
@CrossOrigin("*")
public class TypeTravauxController {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(TypeTravauxController.class);
	@Autowired
	Producer producer;

	@Autowired
	TypeTravauxService service;

	@PostMapping(value="/listetypetravaux")
	public ResponseEntity<Object> liste() {
		try {
			List<TypeTravauxMod> liste = service.getAll(1);
			log.info("liste: " + om.writeValueAsString(liste));
			return new ResponseEntity<>(liste, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
