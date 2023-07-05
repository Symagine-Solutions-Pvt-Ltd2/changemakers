package com.lms.changemaker.serviceimpl;

import java.util.*;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.lms.changemaker.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.changemaker.repository.StudentRepository;
import com.lms.changemaker.mail.EmailServiceImpl;
import com.lms.changemaker.service.StudentService;
import com.lms.changemaker.utilities.JwtTokenUtil;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static com.lms.changemaker.constants.IConstants.THYMLEAF_PASSWORD_RESET_TEMPLATE;
import static com.lms.changemaker.constants.IConstants.THYMLEAF_RESGISTRATION_TEMPLATE;


@Service
public class StudentServiceImpl implements StudentService {

	

@Autowired
private StudentRepository studentRepository;


@Autowired
private SchoolServiceImpl schoolServiceImpl;


@Autowired
private ProgramServiceImpl programServiceImpl;
	
@Autowired
private ModuleServiceImpl moduleServiceImpl;

@Autowired
private ProgramAssigmentServiceImpl implModuleAssigmentServiceImpl;

@Autowired
private ProgramProgressServiceImpl programProgressServiceImpl;


@Autowired
private PasswordEncoder bcryptEncoder;

@Autowired
private JwtTokenUtil jwtTokenUtil;

@Autowired
private ProgramAssigmentServiceImpl programAssigmentServiceImpl;


@Autowired
private EmailServiceImpl emailServiceImpl;

@Autowired
private CertificateDetailServiceImpl certificateDetailService;

@Autowired
private PlasticItemServiceImpl plasticItemServiceImpl;

@Autowired
private ScoreServiceImpl scoreServiceImpl;

@Autowired
private SpringTemplateEngine thymeleafTemplateEngine;
	
@Override
@Transactional
public Student insertStudent(Student teacherEntity) {
	return studentRepository.save(teacherEntity);
}


@Override
public List<Student> listAllStudents() {
	return studentRepository.findAll();
}

@Override
public Optional<Student> findStudentById(int teacherId) {
	return studentRepository.findById(teacherId);
}




@Override
public Student findByEmail(String email) {
	return studentRepository.findStudentByEmail(email);
}




@Override
@Transactional
public int saveStudentToken(String token,String email) {
	
	return studentRepository.saveStudentToken(token,email);
}



@Override
@Transactional
public int registerStudent(Student studentForm, School schoolLogin) {
	
	if(findByEmail(studentForm.getStudentEmail())==null){

	    Map<String, Object> templateModel = EmailServiceImpl.sendRegistraionMail(studentForm.getStudentFirstName()+studentForm.getStudentLastName(),studentForm.getStudentEmail(),studentForm.getStudentPassword(),"https://www.changemakers-plastic.com/student/login","Student");
	      studentForm.setStudentSchoolId(schoolLogin.getSchoolId());
		  studentForm.setStandardId(studentForm.getSelectedStandard().getStandardId());
		  studentForm.setStudentPassword(bcryptEncoder.encode(studentForm.getStudentPassword()));
		  Student student=insertStudent(studentForm);
	 	try {
	 		if(student!=null) {
	 		    int x=schoolLogin.getStudentRegisterCount();
	 			schoolServiceImpl.saveStudentCount(x+1,schoolLogin.getSchoolId());
				programAssigmentServiceImpl.updateProgramAssignByUserIdSchoolId(student.getStudentId(),student.getStudentSchoolId(),schoolLogin.getUserId());
				
	 			emailServiceImpl.sendMessageUsingThymeleafTemplate(studentForm.getStudentEmail(),"Login credentails for Student Portal", templateModel,THYMLEAF_RESGISTRATION_TEMPLATE);
                
				return 1;
	 		}
		} catch (MessagingException e) {
	
			e.printStackTrace();
		}
	 
	}
	
	
	return 0;
}




@Override
@Transactional
public RegisterStudentResponse registerAndAutoLoginStudent(Student studentForm) {
	
		String message="";
	int status=0;
	RegisterStudentResponse registerStudentResponse=new RegisterStudentResponse();

	try {
		if(findByEmail(studentForm.getStudentEmail())!=null){
			message="Student Email Id already exists.";
			status=0;
		} else if(!studentForm.getPasswordConfirm().equals(studentForm.getStudentPassword())){
			message="Passwords does not match.";
			status=0;
		} else {
			Map<String, Object> templateModel = EmailServiceImpl.sendRegistraionMail(studentForm.getStudentFirstName()+" "+studentForm.getStudentLastName(),studentForm.getStudentEmail(),studentForm.getStudentPassword(),"https://www.changemakers-plastic.com/student/login","Student");
			School school=schoolServiceImpl.findSchoolByCode(studentForm.getSchoolCode());


			if(school!=null){
				studentForm.setStudentSchoolId(school.getSchoolId());
				studentForm.setStandardId(studentForm.getSelectedStandard().getStandardId());
				studentForm.setStudentSchoolId(school.getSchoolId());
				studentForm.setStudentPassword(bcryptEncoder.encode(studentForm.getStudentPassword()));
				Student student=insertStudent(studentForm);


				if(student!=null) {

					int x=school.getStudentRegisterCount();
					schoolServiceImpl.saveStudentCount(x+1,school.getSchoolId());

					List<ProgramAssignedDetail> assignedProgramDetails = programServiceImpl.getAssignedProgramDetails(school.getUserId(), school.getSchoolId());
					assignedProgramDetails.forEach(pa->{
						programAssigmentServiceImpl.saveProgramAssigment(new ProgramAssigment(student.getStudentId(),pa.getProgId(),school.getUserId(),school.getSchoolId(),new Date().toString()));
					});
					registerStudentResponse.setStudent(student);
					autologin(student);
					emailServiceImpl.sendMessageUsingThymeleafTemplate(studentForm.getStudentEmail(),"Login credentails for Student Portal", templateModel,THYMLEAF_RESGISTRATION_TEMPLATE);
					message="New Account created.";
					status=1;
				}

			}  else{
				message="School code doesnt exists";
				status=0;
			}

		}
		
	} catch (Exception e) {
		e.printStackTrace();
		message="Some error occured.Kindly retry again.";
		status=0;
	}


	registerStudentResponse.setMessage(message);
	registerStudentResponse.setStatus(status);
	return registerStudentResponse;
}




@Override
public void autologin(Student student) {

			
			if(findByEmail(student.getStudentEmail())!=null) {
				student = findByEmail(student.getStudentEmail());
				String enterPass =student.getStudentPassword();
				boolean match= bcryptEncoder.matches(enterPass, student.getStudentPassword());
				if(match) {
					final String token = jwtTokenUtil.generateToken(student.getStudentEmail());
					student.setStudentToken(token);
					saveStudentToken(token,student.getStudentEmail());
				}
			}
			
			
			
			
			
}





@Override
public List<ProgramAssigment> getModuleList(Student student,int moduleId) {


	
   List<ProgramAssigment> details= implModuleAssigmentServiceImpl.getProgramAssigmentByStudentId(student.getStudentId());

   details.forEach(pa-> pa.setModule(moduleServiceImpl.findModuleById(moduleId).get()));

	return details;
}





@Override
public List<Student> findStudentBySchoolId(int schoolId) {
	return studentRepository.findStudentBySchoolId(schoolId);
}




@Override
public List<Student> getStudentList(int schoolId) {

	School school=schoolServiceImpl.getSchoolById(schoolId).get();
	List<Student> liststudents=studentRepository.findStudentBySchoolId(schoolId);
	liststudents.forEach(st->{


		st.setCertificateCount(certificateDetailService.getCertificateDetailByStudentId(st.getStudentId()).size());
		List<ProgramAssignedDetail> assignedProgramDetailsByStudentId = programServiceImpl.getAssignedProgramDetailsByStudentId(st.getStudentId(), school.getUserId(), schoolId);
		//List<ProgramAssigment> assigments=implModuleAssigmentServiceImpl.getProgramAssigmentByStudentId(st.getStudentId());
		assignedProgramDetailsByStudentId.forEach(as->{
	    	List<ProgramProgress> programProgresses=programProgressServiceImpl.getProgramProgressByStuProg(as.getProgId(),st.getStudentId(),100);

			if(assignedProgramDetailsByStudentId.size()>0)
				as.setRemainProgram(assignedProgramDetailsByStudentId.size()-programProgresses.size());

			if(programProgresses.size()>0)
				as.setProgress(programProgresses.stream().skip(programProgresses.size() - 1).findFirst().get().getProgress());
			else
				as.setProgress(0);
	    });
		st.setAssigments(assignedProgramDetailsByStudentId);
	    st.setProgCount(assignedProgramDetailsByStudentId.size());
	});
	System.out.println(liststudents);
	return liststudents;
}





@Override
@Transactional
public int changePassword(@Valid Student studentForm) {

	  
	 Student student= studentRepository.findById(studentForm.getStudentId()).get();
	  Map<String, Object> templateModel = EmailServiceImpl.sendResetPasswordMail(student.getStudentFirstName()+" "+student.getStudentLastName(),student.getStudentEmail(),studentForm.getStudentPassword(),"http://localhost:8081/student/login","School","Student");
       student.setStudentPassword(bcryptEncoder.encode(studentForm.getStudentPassword()));
	  Student studentnew=insertStudent(student);
	  System.out.println(studentnew.toString());

	try {
		emailServiceImpl.sendMessageUsingThymeleafTemplate(studentnew.getStudentEmail(),"Login credentails for Student Portal", templateModel,THYMLEAF_PASSWORD_RESET_TEMPLATE);
	} catch (MessagingException e) {
		e.printStackTrace();
	}

	return 1;


}


	@Override
	@Transactional
	public int editStudent(Student studentForm, School schoolLogin) {
		System.out.println("studentForm = " + studentForm + ", schoolLogin = " + schoolLogin);
	        Student student =findStudentById(studentForm.getStudentId()).get();
	    	student.setStudentFirstName(studentForm.getStudentFirstName());
	    	student.setStudentLastName(studentForm.getStudentLastName());
	    	student.setStudentEmail(studentForm.getStudentEmail());
		    student.setStandardId(studentForm.getSelectedStandard().getStandardId());
			insertStudent(student);


		return 1;
	}

    @Override
	@Transactional
    public String updateGameScore(Student student, SaveGameScoreResponse gameScoreResponse) {
		System.out.println("student = " + student + ", gameScoreResponse = " + gameScoreResponse);
	    int updateScore=0;
		int score = gameScoreResponse.getCorrectItemId().stream().distinct().collect(
				Collectors.toList())
				.stream()
				.mapToInt(itemId -> plasticItemServiceImpl.findPlasticItemById(itemId).getPoint())
				.sum();

		int scoreMinus= gameScoreResponse.getWrongItemId().stream().distinct().collect(
				Collectors.toList()).stream()
				.mapToInt(itemId -> plasticItemServiceImpl.findPlasticItemById(itemId).getPoint())
				.sum();

		System.out.println(score);
		System.out.println(scoreMinus);
		if(scoreMinus<score) {
			updateScore=score-scoreMinus;
		}

		Score score1 = scoreServiceImpl.saveStudentModuleScore(student.getStudentId(), gameScoreResponse.getModuleId(), 0, updateScore);

		List<PlasticItem> wrongAnswers = gameScoreResponse.getWrongItemId().stream().distinct().collect(
				Collectors.toList())
				.stream()
				.map(itemId -> plasticItemServiceImpl.findPlasticItemById(itemId))
				.collect(Collectors.toList());




		Map<String, Object> templateModel = new HashMap<String, Object>();
		templateModel.put("score",score1);
		templateModel.put("wronganswers",wrongAnswers);

		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateModel);

		return thymeleafTemplateEngine.process("game_score.html", thymeleafContext);

	}

	@Override
	@Transactional
	public String updateGameQuizScore(Student student, SaveGameQuizScoreResponse gameScoreResponse) {

	
		Score score1 = scoreServiceImpl.saveStudentModuleScore(student.getStudentId(), gameScoreResponse.getModuleId(), 0, gameScoreResponse.getScore());
		
		Map<String, Object> templateModel = new HashMap<String, Object>();
		templateModel.put("score",score1); 
		templateModel.put("wronganswers",new ArrayList<PlasticItem>());

		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateModel);

		return thymeleafTemplateEngine.process("game_score.html", thymeleafContext);

	}
}
