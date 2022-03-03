package tn.esprit.project.Entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LikePost implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	LikePostId likepostId;
	Boolean value;
}
