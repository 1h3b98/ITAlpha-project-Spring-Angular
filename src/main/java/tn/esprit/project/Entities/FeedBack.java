package tn.esprit.project.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedBack {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;
    String content;
    @Temporal(TemporalType.DATE)
    Date date;
    @JsonIgnore
    @ManyToOne
    private User sender;
@JsonIgnore
    @ManyToOne
    private User reciever;



}
