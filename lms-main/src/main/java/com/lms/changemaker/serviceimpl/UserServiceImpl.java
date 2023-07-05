package com.lms.changemaker.serviceimpl;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import com.lms.changemaker.entity.*;
import com.lms.changemaker.service.CertificateDetailService;
import com.lms.changemaker.utilities.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lms.changemaker.repository.UserRepository;
import com.lms.changemaker.mail.EmailServiceImpl;
import com.lms.changemaker.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static com.lms.changemaker.constants.IConstants.THYMLEAF_PASSWORD_RESET_TEMPLATE;
import static com.lms.changemaker.constants.IConstants.THYMLEAF_RESGISTRATION_TEMPLATE;
import static com.lms.changemaker.utilities.Utilities.distinctByKey;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	SchoolServiceImpl schoolServiceImpl;

	@Autowired
	StudentServiceImpl studentServiceImpl;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private ProgramAssigmentServiceImpl programAssigmentServiceImpl;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;

	@Autowired
	private CertificateDetailService certificateDetailServiceImpl;

	@Autowired
	private ProgramServiceImpl programServiceImpl;

	@Autowired
	private SpringTemplateEngine thymeleafTemplateEngine;

/*	@Autowired
	private GoogleTranslateServiceImpl googleTranslateService;*/

	@Override
	@Transactional
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findUserByUserId(int userId) {
		return userRepository.findById(userId);
	}



	@Override
	public User findUserByUserName(String username) {
		return userRepository.getUserByUserName(username);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.getUserByUserEmail(email);
	}

	@Override
	public List<User> findAllSubAdmins(int type) {
		return userRepository.getUserByType(type);
	}

	@Override
	public int autologin(User user) {

		// authenticate(studentForm.getStudentEmail(),
		// studentForm.getStudentPassword());
		final User userLogin = findUserByUserName(user.getUsername());
		if (user.getUsername().equals(userLogin.getUsername())) {
			return 1;
		}
		return 0;

	}

	@Override
	@Transactional
	public int autologinSubadmin(User user) {

		if(findUserByEmail(user.getEmail())==null)
			return 0;

		final User userLogin = findUserByEmail(user.getEmail());
		String enterPass =user.getPassword();
		boolean match= bcryptEncoder.matches(enterPass, userLogin.getPassword());
		System.out.println("//// schoolLogin :  "+enterPass);
		System.out.println("enterPass_bool ::: "+match);
		if(match) {
			final String token = jwtTokenUtil.generateToken(userLogin.getEmail());
			saveUserToken(token,userLogin.getUserId());
			return 1;
		}
		return 0;
	}
	    
		@Override
		public void saveUserToken(String token, int userId) {

		userRepository.saveUserToken(token,userId);
	}


	@Override
	@Transactional
	public int changePassword(User user) {
		User user1= userRepository.findById(user.getUserId()).get();
		Map<String, Object> templateModel = EmailServiceImpl.sendResetPasswordMail(user1.getName(),user1.getEmail(),user.getPassword(),"http://localhost:8081/subadmin/login","Admin","Sub Admin");
		user1.setPassword(bcryptEncoder.encode(user.getPassword()));
		User studentnew=addUser(user1);
		System.out.println(studentnew.toString());

		try {
			emailServiceImpl.sendMessageUsingThymeleafTemplate(studentnew.getEmail(),"Login credentails for Sub Admin Portal", templateModel,THYMLEAF_PASSWORD_RESET_TEMPLATE);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return 1;
	}

    @Override
    public String getAssignedPrograms(User user) {

		List<ProgramAssignedDetail> assignedProgramDetails = programServiceImpl.getAssignedProgramDetailsByUserID(user.getUserId());

		log.debug("/////////"+assignedProgramDetails.toString());

		Map<String, Object> templateModel = new HashMap();
		templateModel.put("programList",assignedProgramDetails);

		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateModel);
		return thymeleafTemplateEngine.process("subadmin/school_assignprogram.html", thymeleafContext);
    }

	@Override
	@Transactional
	public void editAssignProg(EditProgramAssignSubAdmin editProgramAssignSubAdmin, User user) {

		editProgramAssignSubAdmin.programAssigns.forEach(pa->{

			List<Student> studentList = studentServiceImpl.getStudentList(editProgramAssignSubAdmin.schoolId);

			if (studentList.size() > 0) {
				studentList.forEach(t->{
					programAssigmentServiceImpl.updateAssignProgramToStudent(t.getStudentId(),editProgramAssignSubAdmin.schoolId,user.getUserId(),Integer.parseInt(pa));
				});

			}else{
				programAssigmentServiceImpl.assignProgramToSchool(editProgramAssignSubAdmin.schoolId,user.getUserId(),Integer.parseInt(pa));
			}

		});
		

		List<ProgramAssigment> collect = programAssigmentServiceImpl.getProgramAssigmentByUserId(user.getUserId()).stream().filter(t -> !editProgramAssignSubAdmin.programAssigns.contains(String.valueOf(t.getProgId()))).collect(Collectors.toList());
		collect.forEach(programAssigment -> programAssigmentServiceImpl.updateAssignProgramToStudent(0,0,user.getUserId(),programAssigment.getProgId()));

		System.out.println("///"+collect.toString());
	}


	@Override
	@Transactional
	public String editSubAdmin(User userForm) {


		try {
			User user= findUserByEmail(userForm.getEmail());

				userForm.setType(2);
				userForm.setPassword(user.getPassword());
				userForm.setUsername(user.getUsername());
				userForm.setPhone(user.getPhone());
				userForm.setToken(user.getToken());
				User addedUser = addUser(userForm);

			List<ProgramAssigment> programAssigment= programAssigmentServiceImpl.getProgramAssigmentByUserId(addedUser.getUserId());

			if(programAssigment!=null||programAssigment.size()>0)
				programAssigmentServiceImpl.deleteByProgAssign(programAssigment);

			List<School> schoolList= schoolServiceImpl.findSchoolByUserId(user.getUserId());
			if(schoolList!=null&&schoolList.size()>0){
					schoolList.forEach(sc->{
						System.out.println("-----------------------");
						System.out.println("School:::::::"+sc.toString());
						List<Student> studentList=studentServiceImpl.findStudentBySchoolId(sc.getSchoolId());
						System.out.println("School:::::::"+studentList.toString());
						System.out.println("-----------------------");
						        if(studentList.size() > 0){
									studentList.forEach(st->{

										userForm.getPrograms().forEach(pr -> {
											ProgramAssigment programAsaa=new ProgramAssigment(st.getStudentId(),pr.getProgId(),addedUser.getUserId(),sc.getSchoolId());
											programAssigmentServiceImpl.saveProgramAssigment(programAsaa);

										});
									});


								}   else{
									userForm.getPrograms().forEach(pr -> {

										ProgramAssigment programAsaa=new ProgramAssigment(0,pr.getProgId(),addedUser.getUserId(),sc.getSchoolId());
										programAssigmentServiceImpl.saveProgramAssigment(programAsaa);

									});
								}

					});
				}  else{
					userForm.getPrograms().forEach(pr -> {
						ProgramAssigment programAsaa=new ProgramAssigment(0,pr.getProgId(),addedUser.getUserId(),0);
						programAssigmentServiceImpl.saveProgramAssigment(programAsaa);

					});
				}
			

			return "Sub Admin added successfully";

		}catch (Exception e) {
			e.printStackTrace();
		}


		return "Error occured.Could not add Sub Admin";
	}




	@Override
	@Transactional
	public String registerSubAdmin(User userForm) {
		
		
		try {
			if(findUserByEmail(userForm.getEmail())==null){
				Map<String, Object> templateModel = EmailServiceImpl.sendRegistraionMail(userForm.getName(),userForm.getEmail(),userForm.getPassword(),"https://www.changemakers-plastic.com/subadmin/login","Sub Admin");
				
				userForm.setType(2);
				userForm.setPassword(bcryptEncoder.encode(userForm.getPassword()));
				userForm.setUsername(userForm.getName().split("\\s")[0] + new Random().nextInt(999));
				User addedUser = addUser(userForm);
				
			
				
				
				userForm.getPrograms().stream().forEach(pr -> {
					ProgramAssigment programAssigment=new ProgramAssigment(0,pr.getProgId(),addedUser.getUserId(),0);
					ProgramAssigment newprogramAssigment=programAssigmentServiceImpl.saveProgramAssigment(programAssigment);	
//					int ar[]=new int[1];
//			        ar[0]=newprogramAssigment.getAssignId();
					
				});

				try {
					emailServiceImpl.sendMessageUsingThymeleafTemplate(userForm.getEmail(),
							"Login credentails for Sub Admin Portal", templateModel,THYMLEAF_RESGISTRATION_TEMPLATE);
					
					return "Sub Admin added successfully";
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				return "Sub Admin with an email id already exists";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		
		return "Error occured.Could not add Sub Admin";
	}


	
	@Override
	public List<User> getSubAdminDashBoardList(Locale locale) {

		List<User> users = findAllSubAdmins(2);
		users.forEach(t1 -> {
		//	t1.setName(googleTranslateService.translate(t1.getName(),locale));
			//t1.setOrganizationName(googleTranslateService.translate(t1.getOrganizationName(),locale));
			t1.setPrograms(null);
			 //updateprogramAssign(t1);

			List<ProgramAssigment> programAssigments = programAssigmentServiceImpl.getProgramAssigmentByUserId(t1.getUserId()).stream().filter(distinctByKey(ProgramAssigment::getProgId)).collect( Collectors.toList());

			System.out.println("programAssigments:::::::::::::::"+programAssigments.toString());
			
			t1.setProgramAssigments(programAssigments);
			List<School> schools = schoolServiceImpl.findSchoolByUserId(t1.getUserId());
			List<CertificateDetail> certificateDetails= certificateDetailServiceImpl.getCertificateDetailByUserId(t1.getUserId());
			t1.setSchoolsRegistered(schools.size());
			t1.setStudentsRegistered(schools.stream().mapToInt(School::getStudentRegisterCount).sum());
			t1.setCertificatesIssued(certificateDetails.size());

		});
		return users;

	}
	


}
