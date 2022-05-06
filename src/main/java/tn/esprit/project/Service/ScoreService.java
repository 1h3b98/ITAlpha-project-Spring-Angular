package tn.esprit.project.Service;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Service
@Slf4j
public class ScoreService implements IScoreService {
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	ScoreRepository scoreRep;
	@Autowired
	EventRepository eventrepo;
	@Autowired
	UserRepo userrepo;
	@Autowired
	QquestionRepository qquestion;
	@Override
	public Score AjouterScoreusers(long idevent, long idquiz) {
//Event event =new Event();
//event=eventrepo.findById(idevent).get();
		Quiz quiz = quizRepository.findById(idquiz).get();
//List<User> users =event.getUserL();
//users=event.getUserL();
		List<User> users=(List<User>) userrepo.findAll();
//List<int>=null;
/*List<String> listt=new ArrayList<String>();
listt.add("Mango");
listt.add("Apple");
listt.add("Banana");  */
//for (String a : listt) {
		for (User user : users) {
			Score score =new Score();

			score.setUser(user);
			score.setUserscore(0);
			score.setQuiz(quiz);
			scoreRep.save(score);
		}
/*score =new Score();
score.setUser(null);
score.setUserscore(0);
score.setQuiz(null);
*/
		return null;
	}

	@Override
	public Score updateScore(Score sc) {
		Score score=scoreRep.save(sc);
		return score;
	}

	@Override
	public void DeleteScore(long idSc) {
		scoreRep.deleteById(idSc);

	}

	@Override
	public Score ShowScore(long idQz) {
		Score score=scoreRep.findById(idQz).get();
		return score;
	}

	@Override
	public Score ShowuserScoreQuiz(long idQz,long iduser) {
		List<Score> sc=scoreRep.showscore(idQz, iduser);
		for(Score score:sc){
			return score;
		}
		return null;
	}

	@Override
	public void answerquestion(long iduser, long idquestion, int chose) {
		List<Score> scores=scoreRep.findAll();
		Qquestion qqquestion = qquestion.findById(idquestion).get();
		int correct=qqquestion.getCorrectNumbr();
		List<Quiz> quizs=(List<Quiz>) quizRepository.findAll();
		Quiz q=new Quiz();
		for(Quiz quiz : quizs){
			if(quiz.getQuestions().contains(qqquestion)){
				q=quiz;
			}
		}
		for (Score score : scores) {
			if(score.getQuiz().getQuizId()==q.getQuizId() && score.getUser().getUserId()==iduser && correct== chose ){
				score.setUserscore(score.getUserscore()+qqquestion.getPointNumbr());
				scoreRep.save(score);
			}

		}

	}


	@Override
	public List<Score> triscore (long idqz){
		List<Score> Showscores = scoreRep.triscore(idqz);
		return Showscores;
	}

	@Override
	public List<User> quizpassornot( long idquiz) {
		int nbrtot=0;
		List<User> users=(List<User>) userrepo.findAll();
		users.clear();
		Quiz quiz=quizRepository.findById(idquiz).get();
		List<Qquestion> questions=quiz.getQuestions();
		for(Qquestion q:questions){
			nbrtot=nbrtot+q.getPointNumbr();
		}
		List<Score> scores=scoreRep.findAll();
		for(Score s:scores){
			if(s.getQuiz().getQuizId()==idquiz){
				if((s.getUserscore()*2)==nbrtot || s.getUserscore()>(nbrtot/2)){
					users.add(s.getUser());
				}
				else{
				}
			}
		}
		return users;
	}

	@Override
	public List<Score> triscoresuser(long iduser) {
		List<Score> Showscores = scoreRep.triuserscore(iduser);
		return Showscores;
	}

	@Override
	public Score AjouterScore(Score score,long iduser,long idquiz) {
		Quiz quiz=quizRepository.findById(idquiz).get();
		User user=userrepo.findById(iduser).get();
		score.setQuiz(quiz);
		score.setUser(user);
		scoreRep.save(score);
		return score;
	}




}
