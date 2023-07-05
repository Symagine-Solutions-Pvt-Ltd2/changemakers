package com.lms.changemaker.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.lms.changemaker.entity.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.changemaker.repository.ProgramAssignmentRepository;
import com.lms.changemaker.service.ProgramAssigmentService;
import com.lms.changemaker.entity.Program;
import com.lms.changemaker.entity.ProgramAssigment;
import com.lms.changemaker.entity.ProgramProgress;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProgramAssigmentServiceImpl implements ProgramAssigmentService {

	

@Autowired
private ProgramAssignmentRepository programAssignmentRepository;

@Autowired
private ProgramServiceImpl programServiceImpl;

@Autowired
private UserServiceImpl userServiceImpl;

@Autowired
private ProgramProgressServiceImpl programProgressServiceImpl;

	@Autowired
	private CertificateDetailServiceImpl certificateDetailService;

@Autowired
private ModuleServiceImpl moduleService;

@Override
@Transactional
public void  saveAssignProgress(ProgramAssigment programAssigment, int taskId, double progress, int studentId, int moduleId, int progId){
	//saveProgramAssigment(programAssigment);
	programProgressServiceImpl.addProgramProgressProg(new ProgramProgress(taskId,progress,studentId,moduleId,progId,new Date().toString()));
}

@Override
@Transactional
public ProgramAssigment saveProgramAssigment(ProgramAssigment programAssigment) {
	return programAssignmentRepository.save(programAssigment);
}

@Override
public List<ProgramAssigment> findAllProgramAssigment() {
	
	return programAssignmentRepository.findAll();
}

@Override
public List<ProgramAssigment> getProgramAssigmentByStudentId(int studentId) {
	List<ProgramAssigment> programAssigments=programAssignmentRepository.getProgramAssigmentByStudentId(studentId);
	return setProgramColor(programAssigments);
}


@Override
public Optional<ProgramAssigment> findProgramAssigmentById(int assignId) {
	return programAssignmentRepository.findById(assignId);
}

//@Override
//public int saveProgramProgress(int moduleId, int studentId, double progress) {
//
//	return programAssignmentRepository.saveProgramProgress(moduleId, studentId, progress);
//}

//@Override
//public ProgramAssigment updateAssignProgramProgress(int moduleId, int studentId, double progress) {
//	
//	/*
//	 * ProgramAssigment assigment=getProgramAssigmentByProgramId(moduleId);
//	 * if(assigment.getProgress()<100) { saveProgramProgress(moduleId,
//	 * studentId,assigment.getProgress()+progress);
//	 * assigment=getProgramAssigmentByProgramId(moduleId); }
//	 */
//	
//	return null;
//}

@Override
@Transactional
public int updateProgramAssignToSchool(int assignId, int schoolId) {
	return programAssignmentRepository.updateProgramAssignToSchool(assignId, schoolId);
}

@Override
@Transactional
public int updateProgramAssignToStudent(int assignId, int studentId) {
	return programAssignmentRepository.updateProgramAssignToStudent(assignId, studentId);

}

@Override
public List<ProgramAssigment> getProgramAssigmentByUserId(int userId) {
	
	List<ProgramAssigment> programAssigments=programAssignmentRepository.getProgramAssigmentByUserId(userId);
	
	return setProgramColor(programAssigments);
}

	@Override
	@Transactional
	public int updateProgramAssignByUserId(int schoolId, int userId) {
		return programAssignmentRepository.updateProgramAssignByUserId(schoolId,userId);
	}

	@Override
	@Transactional
	public int updateProgramAssignByUserIdSchoolId(int studentId, int schoolId, int userId) {
	
		return programAssignmentRepository.updateProgramAssignByUserIdSchoolId(studentId,schoolId,userId,new Date().toString());
	}

	@Override
	public List<ProgramAssigment> getModAssigmentBySchoolIdUserId(int userId, int schoolId) {
		return programAssignmentRepository.getModAssigmentBySchoolIdUserId(userId,schoolId);
	}

	@Override
	@Transactional
	public void deleteByProgAssign(List<ProgramAssigment> userIds) {
		 programAssignmentRepository.deleteAllInBatch(userIds);
	}

	@Override
	public ProgramAssigment getModAssigmentByProgUserId(int userId, int progId) {
		return programAssignmentRepository.getModAssigmentByProgUserId(userId,progId);
	}

	@Override
	@Transactional
	public int assignProgramToSchool(int schoolId, int userId, int progId) {
		return programAssignmentRepository.assignProgramToSchool(schoolId,userId,progId);
	}

	@Override
	@Transactional
	public int updateAssignProgramToStudent(int studentId, int schoolId, int userId, int progId) {
		return programAssignmentRepository.updateAssignProgramToStudent(studentId, schoolId, userId, progId);
	}

	@Transactional
	@Override
	public List<ProgramAssigment> setProgramColor(List<ProgramAssigment> programAssigments) {
	

	programAssigments.stream().forEach(pa ->{
	    Program program=programServiceImpl.findProgramById(pa.getProgId()).get();
		List<ProgramProgress> programProgresses=programProgressServiceImpl.getProgramProgressByStuProg(pa.getProgId(),pa.getStudentId(),100);

		
		if(programProgresses.size()>0){
			ProgramProgress programProgress = programProgresses.stream().skip(programProgresses.size() - 1).findFirst().get();


			List<Module> moduleList= moduleService.findModuleByProgId(pa.getProgId());
			if(moduleList.size()==programProgresses.size()) {
				pa.setProgress(programProgress.getProgress());
				if(certificateDetailService.getCertificateDetailByStuProg(pa.getProgId(),programProgress.getStudentId())!=null)
				pa.setCertifyUrl(certificateDetailService.getCertificateDetailByStuProg(pa.getProgId(),programProgress.getStudentId()).getCertificateUrl());
			    else
			    pa.setCertifyUrl("");
			}

		}

		pa.setProgram(program);

		switch (new Random().nextInt(6)) {
		case 0:
			 pa.setColor("background: #4B49AC; border: 0px;");
			break;
		case 1:
			pa.setColor("background: #57B657; border: 0px;");
			break;
		case 2:
			pa.setColor("background: #248AFD; border: 0px;" );
			break;
		case 3:
			pa.setColor("background: #f96868; border: 0px;");
			break;
		case 4:
			pa.setColor("background: #FFC100; border: 0px;");
			break;
		default:
			pa.setColor("background: #FF4747; border: 0px;");
			break;
		}
	});
	
	for(int i=0;i<programAssigments.size();i++) {
		ProgramAssigment m=programAssigments.get(i);
		if(m.getProgress()<100)
		   {
		  m.setIsOngoing(1);
		   break;
	     }	
	}
	return programAssigments;
}




}
