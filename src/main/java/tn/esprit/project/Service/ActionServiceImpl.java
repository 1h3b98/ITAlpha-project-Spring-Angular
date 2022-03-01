package tn.esprit.project.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.project.Entities.Action;
import tn.esprit.project.Repository.ActionRepository;

@Service
public class ActionServiceImpl implements IActionService {
	@Autowired
	ActionRepository ar;
	@Autowired
	
	public Action addAction(Action a){
		ar.save(a);
		return a;
	}
	@Override
	public Action updateAction(Action a){
		Action actionToupdate=ar.findById(a.getActionId()).orElse(null);
		actionToupdate.setActionType(a.getActionType());
		actionToupdate.setLikeStatus(a.isLikeStatus());
		actionToupdate.setComment(a.getComment());
		actionToupdate.setJoinStatus(a.isJoinStatus());
		
		return ar.save(actionToupdate);
	}
	@Override
	public void deleteAction(long actionId){
		ar.deleteById(actionId);
	}
	@Override
	public List<Action> getAllAction(){
		return (List<Action>) ar.findAll();
	}
	@Override
	public Action findAction(long actionId){
		return ar.findById(actionId).get();
	}
}
