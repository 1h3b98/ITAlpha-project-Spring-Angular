package tn.esprit.project.Registration;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.project.Entities.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;
    private String token;
    private LocalDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name="confirmation_token")
    private String confirmationToken;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    @OneToOne
    private User user;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }


    public ConfirmationToken(User userEntity) {
        this.user = userEntity;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }


}
