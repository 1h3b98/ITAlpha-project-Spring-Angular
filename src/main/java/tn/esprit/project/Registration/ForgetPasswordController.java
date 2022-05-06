package tn.esprit.project.Registration;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class ForgetPasswordController {
    @PutMapping("/resetPassword/{newpass}/{username}")
    public void RestPassword(@PathVariable("newpass") String NewPassword,@PathVariable("username") String username) {
        forgetPasswordService.RestPassword(NewPassword,username);
     //   return "resetpassword";
    }
    @GetMapping("/ForgetPassword/{username}")
    public String  ForgetPassword(@PathVariable("username") String username) {
        forgetPasswordService.ForgetPassword(username);
        return "forgetpassword";
    }
    private final ForgetPasswordService forgetPasswordService;
}
