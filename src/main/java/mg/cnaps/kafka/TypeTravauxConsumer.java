/*package mg.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.TypeTravauxMod;
import mg.cnaps.services.TypeTravauxService;

@Component
public class TypeTravauxConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(TypeTravauxConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	TypeTravauxService service;
	

	@KafkaListener(topics = "listetypetravauxReq")
	public void liste(ConsumerRecord<?, ?> record) throws Exception {
		try {
			log.info("pageliste: "+record.value().toString());
			List<TypeTravauxMod> liste = service.getAll(1);
			log.info("liste: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listetypetravauxRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listetypetravauxRes", e.getMessage());
		}
	}
	
}*/
