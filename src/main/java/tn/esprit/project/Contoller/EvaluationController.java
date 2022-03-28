package tn.esprit.project.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.project.Service.EvaluationService;

import java.util.Map;

@RestController
@RequestMapping("/Evaluation")
public class EvaluationController {


    @Autowired
    EvaluationService es;
    @GetMapping("gettri")
    @ResponseBody
    public Map<Long, Long> triByPoints(){
        return es.triByPoints();
    }

}
