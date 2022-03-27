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
public class ScoreService implements IScoreService {
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	ScoreRepository scoreRep;
	@Autowired
	EventRepository eventrepo;
	@Autowired
	UserRepository userrepo;
	@Autowired
	QquestionRepository qquestion;
	@Override
	public Score AjouterScoreusers(long idevent, long idquiz) {
Event event =new Event();
event=eventrepo.findById(idevent).get();
Quiz quiz = quizRepository.findById(idquiz).get();
List<User> users = null;
users=event.getUserL();
Score score =new Score();
for (User user : users) {
	
	score.setUser(user);
	score.setUserscore(0);
	score.setQuiz(quiz);
	scoreRep.save(score);
}
return null;
	}

	@Override
	public Score updateScore(Score sc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DeleteScore(long idSc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Score ShowScore(long idQz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int ShowuserScoreQuiz(long idQz) {
		// TODO Auto-generated method stub
		return 0;
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
				score.setIdScore(score.getIdScore()+qqquestion.getPointNumbr());
				scoreRep.save(score);
			}
			
		}
		
	}

}
