package tn.esprit.project.Service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import tn.esprit.project.Entities.OtpStatus;
import tn.esprit.project.Entities.User;
import tn.esprit.project.SecurityConfig.TwilioConfig;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOTPService {
    @Autowired
    private TwilioConfig twilioConfig;

    Map<String, String> otpMap = new HashMap<>();

    public Mono<User> sendOTPForPasswordReset(User user) {

        try {
            PhoneNumber to = new PhoneNumber(user.getPhonenumbr());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            String otp = generateOTP();
            String otpMessage = "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";
            Message message = Message
                    .creator(to, from,
                            otpMessage)
                    .create();
            otpMap.put(user.getUsername(),otp);
             user = new User(OtpStatus.DELIVERED, otpMessage);
        } catch (Exception ex) {
             user = new User(OtpStatus.FAILED, ex.getMessage());
        }
        return Mono.just(user);
    }

    public Mono<String> validateOTP(String userInputOtp, String username) {
        if (userInputOtp.equals(otpMap.get(username))) {
            otpMap.remove(username,userInputOtp);
            return Mono.just("Valid OTP please proceed with your transaction !");
        } else {
            return Mono.error(new IllegalArgumentException("Invalid otp please retry !"));
        }
    }

    //6 digit otp
    private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
   }

}
