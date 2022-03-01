package tn.esprit.project.Service;

import java.util.List;

import tn.esprit.project.Entities.Action;

public interface IActionService {
	 Action addAction(Action a);
	 Action updateAction(Action a);
	 void deleteAction(long actionId);
	 List<Action> getAllAction();
	 Action findAction(long actionId);
}
