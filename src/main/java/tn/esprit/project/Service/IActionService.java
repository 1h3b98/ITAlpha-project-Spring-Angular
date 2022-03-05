package tn.esprit.project.Service;

import java.util.List;

import tn.esprit.project.Entities.Action;
import tn.esprit.project.Entities.Event;

public interface IActionService {
	 Action addAction(Action a,long userId,long eventId);
	 Action updateAction(Action a);
	 void deleteAction(long actionId);
	 List<Action> getAllAction();
	 Action findAction(long actionId);
}
