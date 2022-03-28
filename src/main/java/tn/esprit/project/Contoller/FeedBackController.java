package tn.esprit.project.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.FeedBack;
import tn.esprit.project.Service.Feed_BackService;

import java.util.List;

@RestController
@RequestMapping("/FeedBack")
public class FeedBackController {
@Autowired
    Feed_BackService fs;

    @PostMapping("/add/{id}/{id_sender}")
    @ResponseBody
    public FeedBack add_Feedback(@RequestBody FeedBack f,@PathVariable("id") Long id_reciever , @PathVariable("id_sender") Long id_sender) {
        return fs.add_Feedback(f, id_reciever,id_sender);
    }

    @GetMapping ("/show/{id}")
    @ResponseBody
    public List<FeedBack> show_Feedbacks_recieved(@PathVariable("id") Long id_reciever){
      return   fs.show_Feedbacks_recieved(id_reciever);
    }

    @GetMapping ("/nbr/{id}")
    @ResponseBody
    public int nbr_feedbacks_recieved(@PathVariable("id") Long id_reciever){
        return fs.nbr_feedbacks_recieved(id_reciever);
    }
    @PostMapping("/Analysis")
    @ResponseBody
    public void FeedbackAnalysis(){
        fs.FeedbackAnalysis();
    }

    }
