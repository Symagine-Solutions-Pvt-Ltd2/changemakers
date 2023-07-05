package com.lms.changemaker.repository;

import com.lms.changemaker.entity.ProgramAssignedDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lms.changemaker.entity.Program;

import java.util.List;


@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {


    
    @Query("SELECT Distinct  new com.lms.changemaker.entity.ProgramAssignedDetail(p.progId,p.programName,p.description,p.imagePath,pa.studentId,pa.schoolId,pa.userId,pa.startDate,pa.endDate,mslp.limit) FROM Program p " +
            "LEFT JOIN ProgramAssigment pa ON pa.progId = p.progId " +
            "LEFT JOIN MaxStudentLimitProgram mslp ON mslp.progId=p.progId " +
            "where pa.userId =:userId and pa.schoolId =:schoolId")
    public List<ProgramAssignedDetail> getAssignedProgramDetails(@Param("userId") int userId, @Param("schoolId") int schoolId);

    @Query("SELECT Distinct new com.lms.changemaker.entity.ProgramAssignedDetail(p.progId,p.programName,p.description,p.imagePath,pa.studentId,pa.schoolId,pa.userId,pa.startDate,pa.endDate,0) FROM Program p " +
            "LEFT JOIN ProgramAssigment pa ON pa.progId = p.progId " +
            "LEFT JOIN MaxStudentLimitProgram mslp ON mslp.progId=p.progId " +
            "where pa.userId =:userId")
    public List<ProgramAssignedDetail> getAssignedProgramDetailsByUserID(@Param("userId") int userId);


    @Query("SELECT Distinct new com.lms.changemaker.entity.ProgramAssignedDetail(p.progId,p.programName,p.description,p.imagePath,pa.studentId,pa.schoolId,pa.userId,pa.startDate,pa.endDate,0) FROM Program p " +
            "LEFT JOIN ProgramAssigment pa ON pa.progId = p.progId " +
            "LEFT JOIN MaxStudentLimitProgram mslp ON mslp.progId=p.progId " +
            "where pa.studentId =:studentId and pa.userId =:userId and pa.schoolId =:schoolId")
    public List<ProgramAssignedDetail> getAssignedProgramDetailsByStudentId(@Param("studentId") int studentId,@Param("userId") int userId, @Param("schoolId") int schoolId);

    @Query("SELECT p FROM Program p where p.progId in (SELECT pa.progId from ProgramAssigment pa where pa.userId =:userId and pa.schoolId =:schoolId)")
    public List<Program> getAssignedProgramDetails2(@Param("userId") int userId, @Param("schoolId") int schoolId);


}
