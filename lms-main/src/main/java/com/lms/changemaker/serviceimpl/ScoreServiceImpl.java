package com.lms.changemaker.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;


import com.lms.changemaker.entity.*;
import com.lms.changemaker.entity.Module;
import com.lms.changemaker.utilities.PDFUtil;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.changemaker.repository.ScoreRepository;
import com.lms.changemaker.service.ScoreService;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring5.SpringTemplateEngine;



@Service
public class ScoreServiceImpl implements ScoreService {

	

@Autowired
private ScoreRepository scoreRepository;

	@Autowired
	private SchoolServiceImpl schoolService;

	@Autowired
	private ModuleServiceImpl moduleServiceImpl;


@Autowired
private CertificateDetailServiceImpl certificateDetailService;

@Autowired
private ProgramServiceImpl programServiceImpl;

@Autowired
private S3BucketStorageServiceImpl s3BucketStorageService;

	@Autowired
	private SpringTemplateEngine springTemplateEngine;

@Override
@Transactional
public Score addScore(Score score) {
	
	return scoreRepository.save(score);
}

@Override
public List<Score> findAllScores() {
	return scoreRepository.findAll();
}

@Override
public Score getScoreByStudentModule(int studentId, int moduleId) {
	return scoreRepository.getQuizScoreByStudentModule(studentId, moduleId);
}


@Override
@Transactional
public Score saveStudentModuleScore(int studentId, int moduleId, int scoreQuiz,int scoreGame) {
	Score st_score=null;
	if(getScoreByStudentModule(studentId, moduleId)==null) {
		 st_score=addScore(new Score(moduleId,studentId,scoreGame,scoreQuiz));
	}else {
		Score scoreByStudentModule = getScoreByStudentModule(studentId, moduleId);

		if (scoreQuiz>=0){
			scoreByStudentModule.setScoreQuiz(scoreQuiz);
		}
		if(scoreGame>=0){
			scoreByStudentModule.setScoreGame(scoreGame);
		}
		st_score=addScore(scoreByStudentModule);
	}
	return st_score;
}




@Override
@Transactional
public Score completeModule(int studentId, int moduleId, int score,Student student) {

	Score st_score= saveStudentModuleScore(studentId,moduleId,score,0);
	
	int progId= moduleServiceImpl.findModuleById(moduleId).get().getProgId();
	List<Module> moduleList= moduleServiceImpl.findModuleByProgId(progId);
	School school=schoolService.getSchoolById(student.getStudentSchoolId()).get();
	Program program=programServiceImpl.findProgramById(progId).get();

	Module lastModule = moduleList.get(moduleList.size() - 1);

	System.out.println(lastModule.getModuleId()+"--///--"+moduleId);
	if(lastModule.getModuleId()==moduleId){

		try {

			File file = new PDFUtil().generatePdfFromHtml(student, program, moduleList.size(), springTemplateEngine);
			String s = s3BucketStorageService.uploadCertificate(file);
			if (certificateDetailService.getCertificateDetailByStuProg(progId,student.getStudentId())==null){
				certificateDetailService.addCertificateDetailProg(new CertificateDetail(student.getStudentId(),
						student.getStudentSchoolId(),
						school.getUserId(),
						progId,s));

			}


		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}


	return st_score;
}

	@Override
	public List<Score> getQuizScoreByStudent(int studentId) {
		return scoreRepository.getQuizScoreByStudent(studentId);
	}


}
