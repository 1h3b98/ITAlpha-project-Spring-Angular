package tn.esprit.project.Contoller;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.project.Entities.Qquestion;
import tn.esprit.project.Entities.Quiz;
import tn.esprit.project.Service.QuestionService;
import tn.esprit.project.Service.QuizService;

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
@RequestMapping("/questions")

public class QquestionController {
	@Autowired
	QuestionService questionService;

	
	@PostMapping("/add-question/{id-Quiz}")
	public void addQuestion(@RequestBody Qquestion p, @PathVariable("id-Quiz") Long idQuiz) {
		 questionService.AjouterQuestionAndaffect(p,idQuiz);
	}
	@PutMapping("/modify-Qquestion")
	public Qquestion modifyQuestion(@RequestBody Qquestion question) {
		return questionService.UpdateQestion(question);
	}
	@DeleteMapping("/remove-Qquestion/{question-id}")
	public void removeQuestion(@PathVariable("question-id") Long idquestion) {
		questionService.DeleteQestion(idquestion);
	}
		@GetMapping("/retrieve-all-questions-for-questionnaire/{id}")
	public List<Qquestion> retrieveallQuestionsForQuizz(@PathVariable("id") Long id) {
	List<Qquestion> listQuestions = questionService.retrieveallQuestionsForQuiz(id);
	return listQuestions;
	}
		@GetMapping("/retrievequestion/{id}")
		public Qquestion retrieveaQuestion(@PathVariable("id") Long id) {
		Qquestion question = questionService.showquestion(id);
		return question;
		}
}