package com.lms.changemaker.repository;


import com.lms.changemaker.entity.ProgramStandardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramStandardDetailRepository  extends JpaRepository<ProgramStandardDetail, Integer> {


    
}
