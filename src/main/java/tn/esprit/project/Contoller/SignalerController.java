package tn.esprit.project.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Signaler;
import tn.esprit.project.Service.SignalerService;

@RestController
@RequestMapping("/signalerforum")
public class SignalerController {
    @Autowired
    SignalerService ss;
    @PostMapping("/{idf}/{idu}")
    public void SignalerForum(@RequestBody Signaler signaler , @PathVariable("idf") Long idForum , @PathVariable("idu") Long iduser){
        ss.SignalerForum(signaler,idForum,iduser);
    }
}
