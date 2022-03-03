package tn.esprit.project.Entities;

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
public class LikePostId implements Serializable {
        @ManyToOne(fetch = FetchType.EAGER)
        User user;
        @ManyToOne(fetch = FetchType.EAGER)
        Post post;
}
