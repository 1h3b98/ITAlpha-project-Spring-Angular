package tn.esprit.project.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PasswordresetResponse {
    @Enumerated(EnumType.STRING)
    private OtpStatus status;
    private String message;
}
