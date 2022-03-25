package tn.esprit.project.Service;
import java.util.List;

import tn.esprit.project.Entities.*;
public interface IMessageService {
	Message AjouterMessage(Message Messagee);
	Message updateMessage (Message Messagee);
	  void DeleteMessage(long idMessage );
	  
	  List<Message> ShowMessages ();
	  Message ShowMessage (long idMessage);
}
