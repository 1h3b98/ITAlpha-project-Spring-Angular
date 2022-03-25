package tn.esprit.project.Service;
 import java.util.List;

import tn.esprit.project.Entities.*;
public interface IQuizService {
	
  Quiz AjouterQuiz(Quiz Qz);
  Quiz updateQuiz (Quiz Qz);
  void DeleteQuiz(long idQz );
  
  List<Quiz> ShowQuizs ();
  Quiz ShowQuiz (long idQz);
  int calculescore(long idQz);
  Score saveScore(long idQuiz,long Iduser);
  public List<Score> getTopScore();
}
