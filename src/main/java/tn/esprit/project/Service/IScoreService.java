package tn.esprit.project.Service;
import java.util.List;

import tn.esprit.project.Entities.*;
public interface IScoreService {
	Score AjouterScoreusers(long idevent,long idquiz);
	Score updateScore (Score sc);
	  void DeleteScore(long idSc );
	  Score ShowScore (long idQz);
	  int ShowuserScoreQuiz (long idQz);
	void  answerquestion(long iduser,long idquestion, int chose);
}
