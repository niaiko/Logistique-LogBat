/*package mg.cnaps.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import mg.cnaps.models.ActiviteMod;
import mg.cnaps.models.ParamActiviteMod;
import mg.cnaps.models.TypeTravauxMod;
import mg.cnaps.services.ActiviteService;
import mg.cnaps.services.DmdTravauxService;
import mg.cnaps.services.TypeTravauxService;
import mg.cnaps.utils.DateUtil;

@Component
public class ActiviteConsumer {

	ObjectMapper om = new ObjectMapper();
	String resultat;

	private static final Logger log = LoggerFactory.getLogger(ActiviteConsumer.class);
	@Autowired
	Producer producer;

	@Autowired
	ActiviteService service;
	
	@Autowired
	DmdTravauxService servicedmd;
	
	@Autowired
	TypeTravauxService serviceType;
	

	@KafkaListener(topics = "listeallactiviteReq")
	public void liste(ConsumerRecord<?, ?> record) throws Exception {
		try {
			log.info("pageliste get all: "+record.value().toString());
			List<ActiviteMod> liste = service.findallactivite(new PageRequest(Integer.parseInt(record.value().toString())-1, 10));
			log.info("nbr liste : "+liste.size());
			for(int i =0; i<liste.size(); i++) {
				TypeTravauxMod io = serviceType.getdonneetypetravaux(liste.get(i).getIdtravaux());
				liste.get(i).setTravaux(io.getLibelletravaux());
			}
			log.info("liste: "+om.writeValueAsString(liste));
			producer.send(record.key().toString(), "listeallactiviteRes", om.writeValueAsString(liste));
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listeallactiviteRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "listeactivitebyreferenceReq")
	public void listeByRef(ConsumerRecord <?, ?> record) throws Exception {
		try {
			log.info("test: " + record.value().toString());
			ActiviteMod param = om.readValue(record.value().toString(), ActiviteMod.class);
			log.info("reference: " + param.getReference());
			log.info("type travaux: " + param.getTypetravaux());
			log.info("id travaux: " + param.getIdtravaux());
			if(param.getIdtravaux() == 0) {
				log.info("ato io zw");
				List<ActiviteMod> liste = service.getActiviteByReference1( param.getTypetravaux(), new PageRequest(param.getPage()-1, 10));
				log.info("liste tsisy idtravaux : "+om.writeValueAsString(liste));
				for(int i =0; i<liste.size(); i++) {
					TypeTravauxMod io = serviceType.getdonneetypetravaux(liste.get(i).getIdtravaux());
					liste.get(i).setTravaux(io.getLibelletravaux());
				}
				log.info("liste activite" + om.writeValueAsString(liste));
				producer.send(record.key().toString(), "listeactivitebyreferenceRes", om.writeValueAsString(liste));
			}
			else {
				List<ActiviteMod> liste = service.getActiviteByReference(param.getIdtravaux(), param.getTypetravaux(), new PageRequest(param.getPage()-1, 10));
				for(int i =0; i<liste.size(); i++) {
					TypeTravauxMod io = serviceType.getdonneetypetravaux(liste.get(i).getIdtravaux());
					log.info("liste misy idtravaux : "+om.writeValueAsString(liste));
					liste.get(i).setTravaux(io.getLibelletravaux());
				}
				log.info("liste activite" + om.writeValueAsString(liste));
				producer.send(record.key().toString(), "listeactivitebyreferenceRes", om.writeValueAsString(liste));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "listeactivitebyreferenceRes", e.getMessage());
			
			
		}
	} 
	
	@KafkaListener(topics = "nbrpageactiviteReq")
	public void nombredepage(ConsumerRecord<?, ?> record) throws Exception {
		try {
			int page = service.nombrepage();
			resultat = om.writeValueAsString(page);
			producer.send(record.key().toString(), "nbrpageactiviteRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "nbrpageactiviteRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "ajoutactivitelogbatReq")
	public void ajout(ConsumerRecord<?, ?> record) throws Exception {
		try {
			log.info("insert: "+record.value().toString());
			ActiviteMod param = om.readValue(record.value().toString(), ActiviteMod.class);
//			DmdTravauxMod param1 = om.readValue(record.value().toString(), DmdTravauxMod.class);
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
			producer.send(record.key().toString(), "ajoutactivitelogbatRes", "{\"success\":true}");
		} catch (Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "ajoutactivitelogbatRes", e.getMessage());                             
		}
	}

	@KafkaListener(topics = "suppractiviteReq")
	public void suppr(ConsumerRecord<?, ?> record) throws Exception {
		try {
			service.delete(om.readValue(record.value().toString(), ActiviteMod.class));
			producer.send(record.key().toString(), "suppractiviteRes", "succes");
		} catch (Exception e) {
			producer.send(record.key().toString(), "suppractiviteRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "rechercheactiviteReq")
	public void recherche(ConsumerRecord<?, ?> record) throws Exception {
		try {
			log.info("rech: "+record.value().toString());
			ParamActiviteMod param = om.readValue(record.value().toString(), ParamActiviteMod.class);
			List<ActiviteMod> access = service.getActiviteByDateDebutFin(param.getDatedebut(), param.getDatefin());
			resultat = om.writeValueAsString(access);
			log.info("res: "+resultat);
			producer.send(record.key().toString(), "rechercheactiviteRes", resultat);
		}
		catch(Exception e) {
			e.printStackTrace();
			producer.send(record.key().toString(), "rechercheactiviteRes", e.getMessage());
		}
	}
	
	@KafkaListener(topics = "listeactiviteencoursReq")
	public void listeactiviteencours(ConsumerRecord<?, ?> record) {
		try {
			List<ActiviteMod> acces = service.listeactiviteencours();
			for(int i =0 ; i< acces.size();i++)
			{
				acces.get(i).setDemande(servicedmd.getBylistebyid(acces.get(i).getIddmdlogbat()));
			}
			resultat = om.writeValueAsString(acces);
			log.info("nbrligne: "+resultat);
			producer.send(record.key().toString(), "listeactiviteencoursRes", resultat);
		} catch (Exception e) {
			producer.send(record.key().toString(), "listeactiviteencoursRes", e.getMessage());
		}
	}
	
}*/
