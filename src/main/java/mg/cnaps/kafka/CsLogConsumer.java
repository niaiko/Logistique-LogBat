/*package mg.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.CsLogMod;
import mg.cnaps.services.CsLogService;

@Component
public class CsLogConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(CsLogConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	CsLogService service;
	

	@KafkaListener(topics = "listeallcslogbatReq")
	public void liste(ConsumerRecord<?, ?> record) throws Exception {
		try {
			log.info("pageliste: "+record.value().toString());
			List<CsLogMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("listecou: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeallcslogbatRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			producer.send(record.key().toString(), "listeallcslogbatRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "nbrpagecslogbatReq")
	public void nombredepage(ConsumerRecord<?, ?> record) throws Exception {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagecslogbatRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagecslogbatRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutcslogbatReq")
	public void ajout(ConsumerRecord<?, ?> record) throws Exception {
		try {
			log.info("insert: "+record.value().toString());
			service.save(om.readValue(record.value().toString(), CsLogMod.class));
			producer.send(record.key().toString(), "ajoutcslogbatRes", "success");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajoutcslogbatRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprcslogbatReq")
	public void suppr(ConsumerRecord<?, ?> record) throws Exception {
		try {
			service.delete(om.readValue(record.value().toString(), CsLogMod.class));
			producer.send(record.key().toString(), "supprcslogbatRes", "succes");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprcslogbatRes", e.getMessage());
		}
	}
	
//	@KafkaListener(topics = "findbyrechcslogReq")
//	public void findbydestinatairedatecou(ConsumerRecord<?, ?> record) {
//		try {
////			log.info("rech1: "+record.value().toString());
//			CsLogMod param = om.readValue(record.value().toString(), CsLogMod.class);
//			log.info("rech1: "+param.getDestinataire());
//			log.info("rech2: "+param.getDateInsert());
//			List<CsLogMod> acces = service.getByDestinataireDateCou(param.getDestinataire(),param.getDateInsert());
//			resultat = om.writeValueAsString(acces);
//			log.info("nbrligne: "+resultat);
//			producer.send(record.key().toString(), "findbyrechcslogRes", resultat);
//		} catch (Exception e) {
//			producer.send(record.key().toString(), "findbyrechcslogRes", e.getMessage());
//		}
//	}
}*/
