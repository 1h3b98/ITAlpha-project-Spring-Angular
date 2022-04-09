package tn.esprit.project;

import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tn.esprit.project.SecurityConfig.TwilioConfig;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class PiBackendApplication extends SpringBootServletInitializer {
    @Autowired
    private TwilioConfig twilioConfig;
    @PostConstruct
    public void initTwilio(){
        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }
    public static void main(String[] args) {
        SpringApplication.run(PiBackendApplication.class, args);
//        SpringApplicationBuilder app = new SpringApplicationBuilder(PiBackendApplication.class);
//        app.run();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(PiBackendApplication.class);
//    }


//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new Role(null,"ROLE_USER"));
//            userService.saveRole(new Role(null,"ROLE_ADMIN"));
//             userService.saveUser(new User(null,"1234","john",new ArrayList<>()));
//            userService.saveUser(new User(null,"1234","amine",new ArrayList<>()));
//            userService.saveUser(new User(null,"1234","ahmed",new ArrayList<>()));
//            userService.addRoleToUser("john","ROLE_USER");
//            userService.addRoleToUser("ahmed","ROLE_ADMIN");
//            userService.addRoleToUser("john","ROLE_ADMIN");
//            userService.addRoleToUser("amine","ROLE_USER");
//
//        };
    }

