package tn.esprit.project.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikeCommentId implements Serializable {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    User user1;
    @ManyToOne(fetch = FetchType.EAGER)
    Comment comment1;
}
