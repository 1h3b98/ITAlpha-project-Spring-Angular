package tn.esprit.project.Service;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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
	@Autowired
	EventRepository eventrepo;
	@Autowired
	UserRepository userrepo;
	@Autowired
	ScoreRepository scorerepo;
	
	@Override
	public Quiz AjouterQuiz(Quiz Qz,long idevent) {
		Date date = new Date();
		  Timestamp timestamp2 = new Timestamp(date.getTime());
		  Qz.setQDate(timestamp2);
		/*Event event =new Event();
		event=eventrepo.findById(idevent).get();
		List<User> Users = event.getUserL();
		for (User userr : Users) {
			Score score=new Score();
			score.setUserscore(0);
			score.setUser(userr);
			score.setQuiz(Qz);
			
			scorerepo.save(score);	
			
			Qz.getScores().add(score);
		}
	
		*/
		
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
	public Quiz ShowQuiz(long idQz) {
		Quiz quize = quizRepository.findById(idQz).get();

		return quize;
	}

	@Override
	public List<Quiz> ShowQuizuser(long iduser) {
		User user = new User();
		user=userrepo.findById(iduser).get();
		List<Score> scores = null;
		List<Quiz> Quizs = null;
		
		
			scores = (List<Score>)scorerepo.findAll();
		for (Score score : scores) {
			user=userrepo.findById(iduser).get();
			User u = new User();
		u=score.getUser();
			if(u.equals(user)){
				Quiz quiz = new Quiz();
				 quiz=score.getQuiz();
				Quizs.add(quiz);
		}}
				
		
	return Quizs;
	}
	public List<Quiz> ShowQuizs() {
		List<Quiz> Quizs = null;
		try {
		
			Quizs = (List<Quiz>)quizRepository.findAll();
		for (Quiz project : Quizs) {
		log.debug(" User : " + project.toString());
		}
		}
		catch (Exception e) {log.error("Error in retrieveProject : " + e);}
		
		
	return (List<Quiz>)quizRepository.findAll();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public int calculescore(long idQz) {
		int correct = 0;
		/*Quiz quize = quizRepository.findById(idQz).get();

		for(Qquestion q: quize.getQuestions())
			if(q.getCorrectNumbr() == q.getChose())
				correct++;
		*/
		return correct;
	}
	
	public Score saveScore(long idQuiz,long Iduser) {
	/*	Quiz quize = quizRepository.findById(idQuiz).get();

		Score saveScore = new Score();
		int score=calculescore(idQuiz);
		saveScore.setUserscore(score);
		//saveScore.setIduser(Iduser);
		saveScore.setQuiz(quize);
	
		return scoreRep.save(saveScore);
	}

	public List<Score> getTopScore() {
		List<Score> sList = scoreRep.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
		*/
		return null;
	}
	
	
	
	
	
	
	
	
	
}
