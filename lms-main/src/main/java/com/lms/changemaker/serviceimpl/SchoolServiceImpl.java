package com.lms.changemaker.serviceimpl;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.changemaker.repository.SchoolRepository;
import com.lms.changemaker.entity.*;
import com.lms.changemaker.mail.EmailServiceImpl;
import com.lms.changemaker.service.SchoolService;
import com.lms.changemaker.utilities.JwtTokenUtil;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static com.lms.changemaker.constants.IConstants.THYMLEAF_PASSWORD_RESET_TEMPLATE;
import static com.lms.changemaker.constants.IConstants.THYMLEAF_RESGISTRATION_TEMPLATE;
import static com.lms.changemaker.utilities.Utilities.distinctByKey;

@Service
@Slf4j
public class SchoolServiceImpl implements SchoolService {

	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	
	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@Autowired
	private SpringTemplateEngine thymeleafTemplateEngine;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@Autowired
	private ProgramServiceImpl programServiceImpl;

	@Autowired
	private CertificateDetailServiceImpl certificateDetailServiceImpl;
	
	@Autowired
	private ProgramAssigmentServiceImpl programAssigmentServiceImpl;
	
	@Override
	@Transactional
	public School addSchool(School school) {
		return schoolRepository.save(school);
	}

	@Override
	public List<School> getSchools() {		
		return schoolRepository.findAll();
	}


	@Override
	@Transactional
	public School autologinSchool(School school) {

		try {
			School schoolLogin=schoolRepository.findSchoolByEmail(school.getContactEmail());;
			String enterPass =school.getPassword();
			boolean match= bcryptEncoder.matches(enterPass, schoolLogin.getPassword());
			if(match) {
				final String token = jwtTokenUtil.generateToken(school.getContactEmail());
				saveSchoolToken(token,schoolLogin.getContactEmail());
				return schoolLogin;
			}
		}   catch (Exception ex){
			ex.printStackTrace();
		}

	return null;
				
	}
	
	
	

	@Override
	public Optional<School> getSchoolById(int schoolId) {
		
		return schoolRepository.findById(schoolId);
	}

	@Override
	public School findSchoolByEmail(String email) {
		
		return schoolRepository.findSchoolByEmail(email);
	}

	@Override
	@Transactional
	public int saveSchoolToken(String token, String email) {
		return schoolRepository.saveSchoolToken(token, email);
	}





	@Override
	@Transactional
	public int editSchool(School schoolForm, User user) {
		System.out.println("schoolForm = " + schoolForm + ", user = " + user);
		    School school=getSchoolById(schoolForm.getSchoolId()).get();
		    school.setSchoolName(schoolForm.getSchoolName());
		    school.setContactName(schoolForm.getContactName());
		    school.setContactEmail(schoolForm.getContactEmail());
		    school.setContactPhone(schoolForm.getContactPhone());
		
			addSchool(school);
			return 1;
	}

	public static int getDigitCount(BigInteger number) {
		double factor = Math.log(2) / Math.log(10);
		int digitCount = (int) (factor * number.bitLength() + 1);
		if (BigInteger.TEN.pow(digitCount - 1).compareTo(number) > 0) {
			return digitCount - 1;
		}
		return digitCount;
	}

	@Override
	@Transactional
	public String registerSchool(School schoolForm, User user) {
		    String msg="";
		try {
		if(getDigitCount(schoolForm.getContactPhone())<10||getDigitCount(schoolForm.getContactPhone())>10){
			msg="Could not add school. Invalid phone number.";
			return msg;
		}
		if(findSchoolByEmail(schoolForm.getSchoolName())!=null){
			msg="Could not add school. Contact Email Id already exists.";
			return msg;
		}
		
			Map<String, Object> templateModel = EmailServiceImpl.sendRegistraionMail(schoolForm.getContactName(),schoolForm.getContactEmail(),schoolForm.getPassword(),"https://www.changemakers-plastic.com/school/login","School");
			schoolForm.setPassword(bcryptEncoder.encode(schoolForm.getPassword()));
			schoolForm.setUserId(user.getUserId());
			schoolForm.setSchoolCode(String.valueOf(new Random().nextInt(9999))+schoolForm.getContactEmail().substring(1,3));
			School school=addSchool(schoolForm);
			programAssigmentServiceImpl.updateProgramAssignByUserId(school.getSchoolId(),school.getUserId());
			emailServiceImpl.sendMessageUsingThymeleafTemplate(schoolForm.getContactEmail(),"Login credentails for School Portal", templateModel,THYMLEAF_RESGISTRATION_TEMPLATE);
			msg="School added successfully.";
			
			} catch (MessagingException e) {
				e.printStackTrace();
				msg="Could not add school. An error occured. ";
			}
	
		return msg;
	}

	

@Override
public List<School> getSchoolDashBoardList(int userId){
	
	   List<School> schools=findSchoolByUserId(userId);
	   schools.forEach(t1->{
            
	   	    t1.setProgramAssignedDetails(programServiceImpl.getAssignedProgramDetails(userId,t1.getSchoolId()));
	   t1.setCertificateIssued(certificateDetailServiceImpl.getCertificateDetailBySchoolId(t1.getSchoolId()).size());
		List<ProgramProgress>  programProgresses= getSchoolProgressByModule(t1.getSchoolId());

/*
		   List<ProgramAssigment> programAssigments = programServiceImpl.getAssignedProgramDetails(userId,t1.getSchoolId()).stream().filter(distinctByKey(ProgramAssigment::getProgId)).collect( Collectors.toList());
*/


		   if(programProgresses.size()>0)
		t1.setStudentsProgress(programProgresses.stream().skip(programProgresses.size() - 1).findFirst().get().getProgress());
		else
        t1.setStudentsProgress(0);
	   });
	   
	   System.out.println(schools);
	   return schools;
	
}

@Override
public List<School> findSchoolByUserId(int userId) {
	
	return schoolRepository.findSchoolByUserId(userId);
}

@Override
public School findSchoolByCode(String schoolCode) {
	return schoolRepository.findSchoolByCode(schoolCode);
}

@Override
@Transactional
public int saveStudentCount(int studentRegisterCount, int schoolId) {
	
	return schoolRepository.saveStudentCount(studentRegisterCount, schoolId);
}


	@Override
	@Transactional
	public int changePassword(School school) {
		School school1= schoolRepository.findById(school.getSchoolId()).get();
		System.out.println("///schoolnew////"+school1.toString());
		Map<String, Object> templateModel = EmailServiceImpl.sendResetPasswordMail(school1.getContactName(),school1.getContactEmail(),school.getPassword(),"http://localhost:8081/school/login","Sub Admin","School");
		school1.setPassword(bcryptEncoder.encode(school.getPassword()));
		School schoolnew=addSchool(school1);
		System.out.println("///schoolnew////"+schoolnew.toString());

		try {
			emailServiceImpl.sendMessageUsingThymeleafTemplate(schoolnew.getContactEmail(),"Login credentails for School Portal", templateModel,THYMLEAF_PASSWORD_RESET_TEMPLATE);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return 1;
	}

	@Override
	public List<ProgramProgress> getSchoolProgressByModule(int studentSchoolId) {
		return schoolRepository.getSchoolProgressByModule(studentSchoolId);
	}

    @Override
    public String getAssignedPrograms(School school) {

		List<ProgramAssignedDetail> assignedProgramDetails = programServiceImpl.getAssignedProgramDetails(school.getUserId(), school.getSchoolId());
		Map<String, Object> templateModel = new HashMap<String, Object>();
		templateModel.put("programList",assignedProgramDetails);
		templateModel.put("programAssignedDetails",new ArrayList<ProgramAssignedDetail>());

		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateModel);
		return thymeleafTemplateEngine.process("school/school_assignprogram.html", thymeleafContext);
    }

}
