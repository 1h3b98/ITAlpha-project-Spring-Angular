package tn.esprit.project.Contoller;

import org.springframework.web.bind.annotation.RestController;

import tn.esprit.project.Entities.*;
import tn.esprit.project.Service.*;

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
@RequestMapping("/Quiz")

public class QuizController {
	@Autowired
	QuizService quizservice;
	@GetMapping("/retrieve-all-Quiz")
	public List<Quiz> getAllQuizq() {
		List<Quiz> listQuiz = quizservice.ShowQuizs();
		return listQuiz;
	}
	@PostMapping("/add-Quiz/{idevent}")
	public Quiz addQuiz(@RequestBody Quiz p,@PathVariable("idevent") Long idevent) {
		return quizservice.AjouterQuiz(p,idevent);
	}
	@PutMapping("/modify-Quiz")
	public Quiz modifyQuiz(@RequestBody Quiz quiz) {
		return quizservice.updateQuiz(quiz);
	}
	@DeleteMapping("/remove-Quiz/{Quiz-id}")
	public void removeQuiz(@PathVariable("Quiz-id") Long quizid) {
		quizservice.DeleteQuiz(quizid);
	}
	@GetMapping("/retrieve-quizs/{user-id}")
	public List<Quiz>getquizss(@PathVariable("user-id") Long iduser) {
		List<Quiz> listQuiz = quizservice.ShowQuizuser(iduser);
		return listQuiz;
	}
	
	@GetMapping("/show-Quiz/{quiz-id}")
	public Quiz getQuiz(@PathVariable("quiz-id") Long idQz) {
		Quiz qQuiz=quizservice.ShowQuiz(idQz);
		return qQuiz;
	}
	
	
	
	
	
	
	
	@GetMapping("/retrieve-result")
	public int getscore(@PathVariable("Quiz-id") Long quizid) {
		int score =quizservice.calculescore(quizid);
		return score;
	}

	
	
	
	
}
