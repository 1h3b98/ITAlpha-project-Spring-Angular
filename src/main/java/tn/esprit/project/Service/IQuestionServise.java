package tn.esprit.project.Service;

import tn.esprit.project.Entities.Qquestion;
import tn.esprit.project.Entities.Quiz;
import java.util.List;

public interface IQuestionServise {
	void AjouterQuestionAndaffect(Qquestion Q,long id);
	  void DeleteQestion(long idQ );
	  Qquestion UpdateQestion (Qquestion Q);
	  void answerquestion(long idquestion,long iduser,int chose);
	  public List<Qquestion> retrieveallQuestionsForQuiz(Long idquiz);
	  Qquestion showquestion (long idq);
}
