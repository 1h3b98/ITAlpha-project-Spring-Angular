package tn.esprit.project.Service;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;

@Service
@Slf4j
public class QuizService implements IQuizService {
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	ScoreRepository scoreRep;
	
	@Override
	public Quiz AjouterQuiz(Quiz Qz) {
		return quizRepository.save(Qz);

	}
	@Override
	public Quiz updateQuiz(Quiz Qz) {
		return quizRepository.save(Qz);
	}
	@Override
	public void DeleteQuiz(long idQz) {
		quizRepository.deleteById(idQz);
		
	}
	@Override
	public List<Quiz> ShowQuizs() {
		List<Quiz> Quizs = null;
		try {
		
			Quizs = (List<Quiz>)quizRepository.findAll();
		for (Quiz project : Quizs) {
		log.debug(" User : " + project.toString());
		}
		}
		catch (Exception e) {log.error("Error in retrieveProject : " + e);}
		
		
	return Quizs;
	}
	@Override
	public Quiz ShowQuiz(long idQz) {
		Quiz quize = quizRepository.findById(idQz).get();

		return quize;
	}

	public int calculescore(long idQz) {
		int correct = 0;
		Quiz quize = quizRepository.findById(idQz).get();

		for(Qquestion q: quize.getQuestions())
			if(q.getCorrectNumbr() == q.getChose())
				correct++;
		
		return correct;
	}
	
	public Score saveScore(long idQuiz,long Iduser) {
		Quiz quize = quizRepository.findById(idQuiz).get();

		Score saveScore = new Score();
		int score=calculescore(idQuiz);
		saveScore.setUserscore(score);
		saveScore.setIduser(Iduser);
		saveScore.setQuiz(quize);
	
		return scoreRep.save(saveScore);
	}

	public List<Score> getTopScore() {
		List<Score> sList = scoreRep.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
		
		return sList;
	}
	
	
	
	
	
	
	
	
	
	
	
}
