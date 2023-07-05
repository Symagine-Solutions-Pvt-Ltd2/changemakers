package com.lms.changemaker.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.changemaker.repository.ProgramProgressRepo;
import com.lms.changemaker.entity.ProgramProgress;
import com.lms.changemaker.service.ProgramProgressService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProgramProgressServiceImpl implements ProgramProgressService {

	

@Autowired
private ProgramProgressRepo programProgressRepo;

@Override
@Transactional
public ProgramProgress addProgramProgressProg(ProgramProgress programAssigment) {
	return programProgressRepo.save(programAssigment);
}

@Override
public List<ProgramProgress> findAllProgramProgress() {
	return programProgressRepo.findAll();
}

@Override
public List<ProgramProgress> getProgramProgressByStudentId(int studentId) {
	return programProgressRepo.getProgramProgressByStudentId(studentId);
}

	@Override
	public List<ProgramProgress> getProgramProgressByStudentIdModuleId(int studentId, int moduleId) {
		return programProgressRepo.getProgramProgressByStudentIdModuleId(studentId,moduleId);
	}

	@Override
public List<ProgramProgress> getProgramProgressByProgramId(int progId) {
	return programProgressRepo.getProgramProgressByProgId(progId);
}

@Override
public Optional<ProgramProgress> findProgramProgressById(int assignId) {
	return programProgressRepo.findById(assignId);
}

@Override
public List<ProgramProgress> getProgramProgressByModuleId(int moduleId) {
	return programProgressRepo.getProgramProgressByModuleId(moduleId);
}

@Override
@Transactional
public int saveTaskProgress(int taskId, int studentId, double progress) {
	// TODO Auto-generated method stub
	return programProgressRepo.saveTaskProgress(taskId,studentId,progress);
}


@Override
@Transactional
public ProgramProgress updateorinsertTaskProgress(int studentId,int progId,int moduleId,int taskId, double progress){
	ProgramProgress programProgress;
	if(getProgramProgressByStudentIdTaskId(studentId,taskId)!=null) {
		saveTaskProgress(taskId,studentId, progress);
		programProgress=getProgramProgressByStudentIdTaskId(studentId,taskId);
	}else {
		programProgress=addProgramProgressProg(new ProgramProgress(taskId, progress,studentId,moduleId,progId,new Date().toString()));
	}
	
	return programProgress;
}

@Override
public ProgramProgress findProgramProgressByTaskId(int taskId) {
	return programProgressRepo.getProgramProgressByTaskId(taskId);
}

	@Override
	public ProgramProgress getProgramProgressByStudentIdTaskId(int studentId, int taskId) {
		return programProgressRepo.getProgramProgressByStudentIdTaskId(studentId,taskId);
	}

	@Override
public List<ProgramProgress> getProgramProgressByStuProg(int progId, int studentId,double progress) {
	return programProgressRepo.getProgramProgressByStuProg(progId, studentId,progress);
}

}
