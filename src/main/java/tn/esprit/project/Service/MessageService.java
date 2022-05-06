package tn.esprit.project.Service;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
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
	UserRepo userrepo;
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

	@Override
	public Message between2users(long idsender, long idreceiver) {

		List<Message> Messages = null;
		try {

			Messages = (List<Message>)messageRepository.retrievemessagebetween2users(idsender,idreceiver);

		}
		catch (Exception e) {log.error("Error in retrieveProject : " + e);}


		int length=Messages.size()-1;
		return Messages.get(length);
	}

	@Override
	public List<Message> triMessage(long id1) {
		List<Message> Messages  =null;
		try {
			Messages = (List<Message>)messageRepository.findallogthemById(id1);
		}
		catch (Exception e) {log.error("Error in retrieveProject : " + e);}
		List<Message> Messagess = new ArrayList<>();

		List<Long> idusers = new ArrayList<>();

		try {
			for (int i = Messages.size(); i-- > 0; ) {
				if(  id1!=Messages.get(i).getReciever().getUserId() && (!idusers.contains(Messages.get(i).getReciever().getUserId()))){

					idusers.add(Messages.get(i).getReciever().getUserId());
					Messagess.add(Messages.get(i));
					System.out.println("Array after adding element: "+idusers.contains(Messages.get(i).getReciever().getUserId()));

				}
				if(  (id1!=Messages.get(i).getSender().getUserId())   && (!(idusers.contains(Messages.get(i).getSender().getUserId())))){
					idusers.add(Messages.get(i).getSender().getUserId());
					Messagess.add(Messages.get(i));
				}
			}}catch (Exception e) {log.error("Error in retrieveProject : " + e);}

		System.out.println("Array after adding element: "+idusers.toString());
		System.out.println("Array after adding element: "+Messages.size());
		System.out.println("Array after adding element: "+Messages.get(6).getReciever().getUserId());
		return Messagess;
	}

	@Override
	public List<User> listUsermessage(long iduser) {
		List<User> Users  = new ArrayList<>();
		Users=userrepo.findallUserMessages(iduser);
		List<Message> messages = new ArrayList<>();
		messages=triMessage(iduser);
		List<User> users2 = new ArrayList<>();

		for(int i = messages.size(); i-- > 0; ){


			if(iduser != messages.get(i).getReciever().getUserId() ){
				long idr=messages.get(i).getReciever().getUserId();
				User user = userrepo.findById(idr).get();
				Users.remove(user);
				users2.add(user);

			}}
		for (User userr:Users) {

			users2.add(userr);
		}


		return users2;


	}

	@Override
	public User ShowUser(long idMessage) {
		User userr=userrepo.findById(idMessage).get();
		return userr;
	}









}
















