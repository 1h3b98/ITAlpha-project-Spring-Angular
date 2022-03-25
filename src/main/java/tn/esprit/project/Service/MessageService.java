package tn.esprit.project.Service;
import org.springframework.stereotype.Service;

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
	@Override
	public Message AjouterMessage(Message Messagee) {
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
	public List<Message> ShowMessages() {
		List<Message> Messages = null;
		try {
		
			Messages = (List<Message>)messageRepository.findAll();
		for (Message msg : Messages) {
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
