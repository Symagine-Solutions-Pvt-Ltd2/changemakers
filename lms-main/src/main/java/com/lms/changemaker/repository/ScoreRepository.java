package com.lms.changemaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.lms.changemaker.entity.Score;


@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

	@Query("SELECT u FROM Score u WHERE u.studentId = :studentId and u.moduleId= :moduleId")
	public	Score getQuizScoreByStudentModule(int studentId,int moduleId);

	@Query("SELECT u FROM Score u WHERE u.studentId = :studentId")
	public	List<Score> getQuizScoreByStudent(int studentId);


	/*@Modifying
	@Query(value="UPDATE Score u set u.scoreQuiz =:score,score  WHERE u.studentId = :studentId and u.moduleId= :moduleId")
	public int saveStudentQuizScore(int score,int studentId,int moduleId);
*/
}
