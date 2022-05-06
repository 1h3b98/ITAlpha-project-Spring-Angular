package tn.esprit.project.Registration;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.User;

@RestController
@RequestMapping(path = "/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    @PostMapping("/add")
    public String register(@RequestBody User user) {
        return registrationService.register(user);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {

        return registrationService.confirmToken(token);
    }

}