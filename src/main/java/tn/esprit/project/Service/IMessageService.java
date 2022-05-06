package tn.esprit.project.Service;
import java.util.List;

import tn.esprit.project.Entities.*;
public interface IMessageService {
	Message AjouterMessage(Message Messagee,long idsender,long idreceiver);
	Message updateMessage (Message Messagee);
	void DeleteMessage(long idMessage );

	List<Message> ShowMessagesbetween2user(long id1,long id2);
	Message ShowMessage (long idMessage);
	Message between2users(long idsender,long idreceiver);
	List<Message> triMessage(long id1);
	List<User> listUsermessage(long iduser);
	User ShowUser (long idMessage);

}
