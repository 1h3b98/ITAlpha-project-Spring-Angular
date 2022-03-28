package tn.esprit.project.SecurityConfig;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String trialNumber;

}
