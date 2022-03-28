package tn.esprit.project.Service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;

import java.util.List;
import java.util.Set;
@Service

public class QuestionService implements IQuestionServise {
	@Autowired
	QquestionRepository  qquestionRepository;
	@Autowired
	QuizRepository quizRepository;

	


	@Override
	public void DeleteQestion(long idQ) {
		qquestionRepository.deleteById(idQ);
		
	}

	@Override
	public Qquestion UpdateQestion(Qquestion Q) {
		return qquestionRepository.save(Q);

	}

	@Override
	
	public void AjouterQuestionAndaffect(Qquestion Q, long idQuiz) {
		 Quiz qz = quizRepository.findById(idQuiz).orElse(null);
		// Q.setQuizs(qz);
		 qz.getQuestions().add(Q);
		 qquestionRepository.save(Q);

	}

	@Override
	public void answerquestion(long idquestion, long iduser, int chose) {
		Qquestion question=qquestionRepository.findById(idquestion).get();
		if(chose==question.getCorrectNumbr()){
			Score score = new Score();
			//score=question.getQuiz().getScores();
			
		}		
	}

	

	@Override
	public List<Qquestion> retrieveallQuestionsForQuiz(Long idquiz) {
		Quiz q=quizRepository.findById(idquiz).get();
		
		
		
		return q.getQuestions();
	}

	@Override
	public Qquestion showquestion(long idq) {
		Qquestion q = qquestionRepository.findById(idq).get();
 return q;
	}
	


	
	
}
