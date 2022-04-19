package tn.esprit.project.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.project.Entities.*;
@Repository

public interface ScoreRepository extends JpaRepository<Score, Long>  {
	@Query(value ="select * from score s where s.quiz_quiz_id = :idqz order by s.userscore desc",nativeQuery=true)
	public List<Score> triscore(@Param("idqz") Long idqz);
	@Query(value ="select * from score s where (s.quiz_quiz_id = :idqz and s.user_user_id = :iduser)",nativeQuery=true)
	public List<Score> showscore(@Param("idqz") Long idqz,@Param("iduser") Long iduser);
}
