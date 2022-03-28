package tn.esprit.project.Service;
 import java.util.List;

import tn.esprit.project.Entities.*;
public interface IQuizService {
	
  Quiz AjouterQuiz(Quiz Qz,long idevent);
  Quiz updateQuiz (Quiz Qz);
  void DeleteQuiz(long idQz );
  List<Quiz> ShowQuizs ();
  Quiz ShowQuiz (long idQz);
  
  
  
  
  List<Quiz> ShowQuizuser (long iduser);

  
}
