package tn.esprit.project.Service;
import java.util.List;

import tn.esprit.project.Entities.*;
public interface IScoreService {
	Score AjouterScoreusers(long idevent,long idquiz);
	Score updateScore (Score sc);
	void DeleteScore(long idSc );
	Score ShowScore (long idQz);
	Score ShowuserScoreQuiz (long idQz,long iduser);
	void  answerquestion(long iduser,long idquestion, int chose);
	List<User> quizpassornot(long idquiz);
	//List<Score> triscore();
	List<Score> triscore (long idqz);
	List<Score> triscoresuser (long iduser);
	Score AjouterScore(Score score,long iduser,long idquiz);

}
