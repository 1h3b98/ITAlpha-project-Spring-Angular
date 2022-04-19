package tn.esprit.project.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.project.Entities.*;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>  {
	@Query(value ="select * FROM message m where (m.sender_user_id = :iduser1 and m.reciever_user_id = :iduser2) or (m.sender_user_id = :iduser2 and m.reciever_user_id = :iduser1)" ,nativeQuery = true)
			public List<Message>retrievemessagebetween2users(@Param("iduser1")Long id1,@Param("iduser2")Long id2);
	}
