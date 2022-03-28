package tn.esprit.project.Contoller;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.ScoreRepository;
import tn.esprit.project.Repository.UserRepository;
import tn.esprit.project.Service.*;
import javax.mail.MessagingException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping("/score")

public class ScoreController {
	@Autowired
	ScoreService scoreService;
	@Autowired
	EmailService emailService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ScoreRepository scorerep;
	@PostMapping("/add-score/{event-id}/{quiz-id}")
	public Score addScore(@PathVariable("event-id") Long eventid,@PathVariable("quiz-id") Long quizid) {
		return scoreService.AjouterScoreusers(eventid, quizid);
	}
	
	@PutMapping("/answer-question/{user-id}/{question-id}/{chose}")
	public void answerquestion(@PathVariable("user-id") long iduser,@PathVariable("question-id") long idquestion,@PathVariable("chose") int chose)
	{
		scoreService.answerquestion(iduser, idquestion, chose);
	}
	@PostMapping("/sendemail/{user-id}/{body}/{subject}")
	public void sendemail(@PathVariable("user-id") long userid,@PathVariable("body") String body,@PathVariable("subject") String subject,@RequestBody String certif) throws MessagingException {
		String usermail=userRepository.findById(userid).get().getEmail();
		emailService.sendEmailWithAttachment(usermail, body, subject,certif);
	}
	@GetMapping("/triscore/{idqz}")
	public List<Score> retrieveallQuestionsForQuizz(@PathVariable("idqz") Long idqz) {
	List<Score> listScore = scoreService.triscore(idqz);
			return listScore;
	}
	@GetMapping("/showuserscore/{idqz}/{iduser}")
	public int retrieveallQuestionsForQuizz(@PathVariable("idqz") Long idqz,@PathVariable("iduser") Long iduser) {
	int score=scoreService.ShowuserScoreQuiz(idqz, iduser);
	return score;
	}

	@PutMapping("/modify-score")
	public Score modifyQuiz(@RequestBody Score score) {
		return scoreService.updateScore(score);
		
	}
	
	@DeleteMapping("/remove-score/{score-id}")
	public void removeQuiz(@PathVariable("score-id") Long scoreid) {
		scoreService.DeleteScore(scoreid);
	}

	@GetMapping("/show-score/{score-id}")
	public Score getQuiz(@PathVariable("score-id") Long idscore) {
		Score score=scoreService.ShowScore(idscore);
		return score;
	}
	
	
}
