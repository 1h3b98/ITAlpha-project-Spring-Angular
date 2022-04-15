package com.example.pispring;

import com.example.pispring.SecurityConfig.TwilioConfig;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class PiSpringApplication {
    @Autowired
    private TwilioConfig twilioConfig;
    @PostConstruct
    public void initTwilio(){
        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }
    public static void main(String[] args) {
        SpringApplication.run(PiSpringApplication.class, args);
//        SpringApplicationBuilder app = new SpringApplicationBuilder(PiBackendApplication.class);
//        app.run();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
