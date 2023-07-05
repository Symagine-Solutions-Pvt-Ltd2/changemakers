package com.lms.changemaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.lms.changemaker.entity.Module;


@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

	
	@Query("SELECT u FROM Module u WHERE u.progId = :progId")
	List<Module> findModuleByProgId(int progId);

	
	
	@Query("SELECT u FROM Module u WHERE u.progId = :progId")
	List<Module> findAllModuleDetails(int progId);
}
