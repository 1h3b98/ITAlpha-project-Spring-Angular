package tn.esprit.project.Service;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;

@Service
@Slf4j
public class MessageService implements IMessageService {
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	UserRepository userrepo;
	@Override
	public Message AjouterMessage(Message Messagee,long idsender,long idreceiver) {
		User sender=userrepo.findById(idsender).get();
		User receiver=userrepo.findById(idreceiver).get();
		Messagee.setSender(sender);
		Messagee.setReciever(receiver);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Messagee.setMDate(timestamp);
		return messageRepository.save(Messagee);
	}

	@Override
	public Message updateMessage(Message Messagee) {
		return messageRepository.save(Messagee);

	}

	@Override
	public void DeleteMessage(long idMessage) {
		messageRepository.deleteById(idMessage);

		
	}
 
	@Override
	public List<Message> ShowMessagesbetween2user(long iduser1,long iduser2) {
		//User user1=userrepo.findById(iduser1).get();
		//User user2=userrepo.findById(iduser2).get();
		List<Message> Messages = null;
		try {
		
			Messages = (List<Message>)messageRepository.retrievemessagebetween2users(iduser1,iduser2);
		for (Message msg : Messages) {
			if(iduser1==msg.getReciever().getUserId()){
			msg.setViewed(true);}
		log.debug(" User : " + msg.toString());
		}
		}
		catch (Exception e) {log.error("Error in retrieveProject : " + e);}
		
		
	return Messages;
	}

	@Override
	public Message ShowMessage(long idMessage) {
		Message msg = messageRepository.findById(idMessage).get();

		return msg;
	}

}
