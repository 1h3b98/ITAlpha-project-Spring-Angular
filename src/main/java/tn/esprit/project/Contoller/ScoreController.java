package tn.esprit.project.Contoller;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.ScoreRepository;
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
	EmailService1 emailService;

	@Autowired
	ScoreRepository scorerep;
	@PostMapping("/add-scoress/{event-id}/{quiz-id}")
	public void addScoress(@PathVariable("event-id") Long eventid,@PathVariable("quiz-id") Long quizid) {
		scoreService.AjouterScoreusers(eventid, quizid);
	}

	@PostMapping("/add-score/{user-id}/{quiz-id}")
	public Score addScore(@RequestBody Score score,@PathVariable("user-id") Long iduser,@PathVariable("quiz-id") Long quizid) {
		return scoreService.AjouterScore(score,iduser, quizid);
	}


	@PutMapping("/answer-question/{user-id}/{question-id}/{chose}")
	public void answerquestion(@PathVariable("user-id") long iduser,@PathVariable("question-id") long idquestion,@PathVariable("chose") int chose)
	{
		scoreService.answerquestion(iduser, idquestion, chose);
	}
	@PostMapping("/sendemail/{q-id}/{subject}/{body}")
	public void sendemail(@PathVariable("q-id") long qid,@PathVariable("body") String body,@PathVariable("subject") String subject,@RequestBody String certif) throws MessagingException {
		//String usermail=userRepository.findById(userid).get().getEmail();
		List<User> users=scoreService.quizpassornot(qid);
		for(User user:users){
			emailService.sendEmailWithAttachment(user.getEmail(), body, subject,certif);}
	}
	@GetMapping("/triscore/{idqz}")
	public List<Score> triscoreparquiz(@PathVariable("idqz") Long idqz) {
		List<Score> listScore = scoreService.triscore(idqz);
		return listScore;
	}
	@GetMapping("/showuserscorequiz/{idqz}/{iduser}")
	public Score retrieveauserscore(@PathVariable("idqz") Long idqz,@PathVariable("iduser") Long iduser) {
		Score score=scoreService.ShowuserScoreQuiz(idqz, iduser);

		return score;
	}

	@PutMapping("/modify-score")
	public Score modifyQuiz(@RequestBody Score score) {
		return scoreService.updateScore(score);

	}

	@DeleteMapping("/remove-score/{score-id}")
	public void removescore(@PathVariable("score-id") Long scoreid) {
		scoreService.DeleteScore(scoreid);
	}

	@GetMapping("/show-score/{score-id}")
	public Score showscoresss(@PathVariable("score-id") Long idscore) {
		Score score=scoreService.ShowScore(idscore);
		return score;
	}


	@GetMapping("/user-quiz/{user-id}")
	public List<Score> getuserscore(@PathVariable("user-id") Long iduser) {
		List<Score> score=scoreService.triscoresuser(iduser);
		return  score;
	}


}
