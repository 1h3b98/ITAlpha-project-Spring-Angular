package tn.esprit.project.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Qvt;
import tn.esprit.project.Entities.QvtAnswer;
import tn.esprit.project.Service.QvtService;

import java.util.List;

@RestController
@RequestMapping("/qvt")
public class QvtContoller {

    @Autowired
    QvtService qs;


    @PostMapping("add/{idu}")
    @ResponseBody
    public void  addQvt(@RequestBody Qvt qvt ,@PathVariable("idu") Long iduser){
        qs.addQvt(qvt,iduser);
    };

    @PostMapping("response/{idq}/{idu}")
    @ResponseBody
    public void responseQvt(@PathVariable("idq")Long idQvt,@PathVariable("idu") Long iduser,@RequestBody QvtAnswer qvtAnswer){
        qs.responseQvt(idQvt, iduser, qvtAnswer);
    }
    @GetMapping("getAnswers/{idq}")
    @ResponseBody
    public List<QvtAnswer> getAnswerByqvt(@PathVariable("idq")Long idQvt){
        return qs.getAnswerByqvt(idQvt);
    }

}
