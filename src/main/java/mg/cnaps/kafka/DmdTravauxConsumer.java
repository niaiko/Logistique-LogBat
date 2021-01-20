/*package mg.cnaps.kafka;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.DmdTravauxMod;
import mg.cnaps.models.ReferenceParam;
import mg.cnaps.services.ActiviteService;
import mg.cnaps.services.DmdTravauxService;
import mg.cnaps.utils.DateUtil;
import mg.cnaps.utils.ReferenceUtil;

@Component
public class DmdTravauxConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(DmdTravauxConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	DmdTravauxService service;
	
	@Autowired
	ActiviteService serviceact;
	

	@KafkaListener(topics = "listealldmdReq")
	public void liste(ConsumerRecord<?, ?> record) throws Exception {
		try {
			log.info("pageliste: "+record.value().toString());
			List<DmdTravauxMod> liste = service.getAll(Integer.parseInt(record.value().toString()));
			log.info("listecou: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listealldmdRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listealldmdRes", e.getMessage());
		}
		
	}
	
	@KafkaListener(topics = "nbrpagedmdReq")
	public void nombredepage(ConsumerRecord<?, ?> record) throws Exception {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpagedmdRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpagedmdRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutdmdlogbatReq")
	public void ajout(ConsumerRecord<?, ?> record) throws Exception {
		try {
			log.info("insert--> : "+record.value().toString());
			service.save(om.readValue(record.value().toString(), DmdTravauxMod.class));
			producer.send(record.key().toString(), "ajoutdmdlogbatRes", "{\"success\":true}");
		} catch (Exception e) {
			producer.send(record.key().toString(), "ajoutdmdlogbatRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "referenceDmdTravauxReq")
	public void demandeReference(ConsumerRecord<?, ?> record) throws Exception {
		try {
			log.info("Reference: " + record.value().toString());
			ReferenceParam param = om.readValue(record.value().toString(), ReferenceParam.class);
			resultat = ReferenceUtil.getReferenceDemande(param.getPrestation(), service.seqDemande(), param.getDr());
			producer.send(record.key().toString(), "referenceDmdTravauxRes", om.writeValueAsString(resultat));
		}
		catch(Exception e) {
			producer.send(record.key().toString(), "referenceDmdTravauxRes", e.getMessage());
		}
	}

	@KafkaListener(topics = "supprdmdReq")
	public void suppr(ConsumerRecord<?, ?> record) throws Exception {
		try {
			service.delete(om.readValue(record.value().toString(), DmdTravauxMod.class));
			producer.send(record.key().toString(), "supprdmdRes", "succes");
		} catch (Exception e) {
			producer.send(record.key().toString(), "supprdmdRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "updatevalidationdmdlogbatReq")
	public void updatevalidationdmd(ConsumerRecord<?, ?> record) {
		try {
			log.info("insert: "+record.value().toString());
			DmdTravauxMod dvm = om.readValue(record.value().toString(), DmdTravauxMod.class);
			log.info("insert222: "+om.writeValueAsString(dvm));
			service.updatevalidationdmd(dvm.getIddmdlogbat());
			producer.send(record.key().toString(), "updatevalidationdmdlogbatRes", om.writeValueAsString(dvm));
			log.info("Send: "+record.key().toString());
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "updatevalidationdmdlogbatRes", e.getMessage());
			
		}
	}
	
	@KafkaListener(topics = "listevalidationdmdlogReq")
	public void listevalidationdmdlog(ConsumerRecord<?, ?> record) {
		try {
			List<DmdTravauxMod> access = new ArrayList<>();
			List<DmdTravauxMod> io = service.listevalidationdmdlog();
			log.info(om.writeValueAsString(io));
		
			for(int i = 0; i<io.size();i++) {
				int nbr = serviceact.countactivitebyid(io.get(i).getIddmdlogbat());
				log.info("te : "+nbr);
				if(nbr == 0 ) {
					
					access.add(io.get(i));
				}
			}
			producer.send(record.key().toString(), "listevalidationdmdlogRes", om.writeValueAsString(access));
			log.info("Send: "+io);
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listevalidationdmdlogRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "listenonvalidationdmdlogReq")
	public void listenonvalidationdmdlog(ConsumerRecord<?, ?> record) {
		try {
			
			String page = record.value().toString();
			log.info(""+ page);
			List<DmdTravauxMod> io = service.listenonvalidationdmdlog(new PageRequest(Integer.parseInt(page), 10));
			log.info("vita v?");
			producer.send(record.key().toString(), "listenonvalidationdmdlogRes", om.writeValueAsString(io));
			log.info("Send: "+io);
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listenonvalidationdmdlogRes", e.getMessage());
			
		}
	}
	
	@KafkaListener(topics = "updateactiviteencoursReq")
	public void updateactiviteencours(ConsumerRecord<?, ?> record) {
		try {
			log.info(record.value().toString());
			DmdTravauxMod param = om.readValue(record.value().toString(), DmdTravauxMod.class);
			service.updateactiviteencours(param.getIddmdlogbat(),DateUtil.sqlDateNow());
			producer.send(record.key().toString(), "updateactiviteencoursRes", record.value().toString());
		} catch (Exception e) {
			producer.send(record.key().toString(), "updateactiviteencoursRes", e.getMessage());
		}
	}
	
	
}*/
