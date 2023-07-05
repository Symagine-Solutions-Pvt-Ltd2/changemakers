package com.lms.changemaker.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.lms.changemaker.entity.*;
import com.lms.changemaker.entity.Module;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.lms.changemaker.constants.IConstants;
import com.lms.changemaker.service.JwtStudentDetailsService;
import com.lms.changemaker.serviceimpl.*;
import com.lms.changemaker.utilities.JwtTokenUtil;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.HttpResource;
/*
import com.lms.changemaker.utilities.PDFUtil;
import org.thymeleaf.spring5.SpringTemplateEngine;
import java.io.IOException;
import org.dom4j.DocumentException;
*/
@Controller
@RequestMapping("student")
@Slf4j
public class StudentController {

	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@Autowired
	private ModuleServiceImpl moduleServiceImpl;

	@Autowired
	private SchoolServiceImpl schoolServiceImpl;

	@Autowired
	TaskServiceImpl taskServiceImpl;
	
	@Autowired
	ProgramServiceImpl programServiceImpl;
	
	
	@Autowired
	StandardServiceImpl standardServiceImpl;
	
	@Autowired
	private ProgramAssigmentServiceImpl programAssigmentServiceImpl;

	@Autowired
	private LocaleResolver localeResolver;

	// @Autowired
	// private SpringTemplateEngine springTemplateEngine;

	
	
	@GetMapping("/registration")
	public String registration(Model model, HttpSession httpSession,@ModelAttribute("message") final String message) {

		if (httpSession.getAttribute(IConstants.STUDENT_LOGGED_IN) != null)
			return "redirect:/student/home";
		
		model.addAttribute("studentForm", new Student());
		model.addAttribute("schoolList", schoolServiceImpl.getSchools());
		model.addAttribute("standardList",standardServiceImpl.findAllStandards());
		if(message!=null)
			model.addAttribute("message",message);
		else
			model.addAttribute("message","");
		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@ModelAttribute @Valid Student studentForm, BindingResult bindingResult,
							   HttpSession session,RedirectAttributes redirectAttributes) {
		RegisterStudentResponse registerStudentResponse = studentServiceImpl.registerAndAutoLoginStudent(studentForm);

		if(registerStudentResponse.getStatus()==1){
			session.setAttribute(IConstants.STUDENT_LOGGED_IN,
					studentServiceImpl.findByEmail(studentForm.getStudentEmail()));
			return "redirect:/student/home";
		}
		redirectAttributes.addAttribute("message",registerStudentResponse.getMessage());
		return "redirect:/student/registration";
	}

	@GetMapping("/login")
	public String login(Model model, HttpServletRequest request, HttpSession httpSession) {
		if (httpSession.getAttribute(IConstants.STUDENT_LOGGED_IN) != null)
			return "redirect:/student/home";

		model.addAttribute("studentLoginForm", new Student());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute @Valid Student studentForm, BindingResult bindingResult, HttpSession session) {

		if (session.getAttribute(IConstants.STUDENT_LOGGED_IN) == null) {
			studentServiceImpl.autologin(studentForm);

			session.setAttribute(IConstants.STUDENT_LOGGED_IN,
					studentServiceImpl.findByEmail(studentForm.getStudentEmail()));
		}
		return "redirect:/student/home";
	}

	@GetMapping("/logout")
	public String logout(Model model, HttpSession session) {

		if (session.getAttribute(IConstants.STUDENT_LOGGED_IN) != null) {
			session.removeAttribute(IConstants.STUDENT_LOGGED_IN);
		}
		return "redirect:/student/login";
	}

	@GetMapping("/home")
	public String goToHome(Model model, HttpSession session, HttpServletRequest httpRequest, HttpServletResponse httpResource) {

		if (session.getAttribute(IConstants.STUDENT_LOGGED_IN) == null)
			return "redirect:/student/login";

		List<Language> languageList=new ArrayList<>();
		languageList.add(new Language(0,"English","en"));
		languageList.add(new Language(1,"Bengali","bn"));
	/*	Locale loc = Locale.forLanguageTag("en");
		localeResolver.setLocale(httpRequest,httpResource,loc);*/

		Student student = (Student) session.getAttribute(IConstants.STUDENT_LOGGED_IN);
		System.out.println(localeResolver.resolveLocale(httpRequest).getDisplayName());
		student.setLanguage(localeResolver.resolveLocale(httpRequest).getDisplayName());
		List<ProgramAssignedDetail> list = programServiceImpl.getProgramAssigntoStudent(student.getStudentId(),schoolServiceImpl.getSchoolById(student.getStudentSchoolId()).get().getUserId(),student.getStudentSchoolId());
		model.addAttribute("studentModel", student);
		model.addAttribute("langList", languageList);
		model.addAttribute("studentProgList", list);
		return "student/dashboard";
	}

	@GetMapping("/program/{prog_id}/content/{module_id}")
	public String content(@PathVariable int module_id,@PathVariable int prog_id, Model model, HttpSession httpSession,HttpServletRequest httpServletRequest) {

		if (httpSession.getAttribute(IConstants.STUDENT_LOGGED_IN) == null)
			return "redirect:/student/login";
		Student student = (Student) httpSession.getAttribute(IConstants.STUDENT_LOGGED_IN);
		List<ProgramAssigment> list = studentServiceImpl.getModuleList(student,module_id);
		List<Module> moduleByProgId = moduleServiceImpl.findModuleByProgId(prog_id);
		System.out.println(list.toString());
		model.addAttribute("studentModuleList", list);

		model.addAttribute("lastModule", moduleByProgId.get(moduleByProgId.size()-1));
		model.addAttribute("taskData", taskServiceImpl.getTaskstraining(module_id));
		model.addAttribute("moduleDetail", moduleServiceImpl.findModuleById(module_id).get());
		model.addAttribute("studentDetail", student);
		model.addAttribute("programModel",programServiceImpl.findProgramById(prog_id).get());
		return "student/training_content";
	}

	@GetMapping("/module/{prog_id}")
	public String goToModule(@PathVariable int prog_id, Model model, HttpSession httpSession) {

		if (httpSession.getAttribute(IConstants.STUDENT_LOGGED_IN) == null)
			return "redirect:/student/login";

		Student student = (Student) httpSession.getAttribute(IConstants.STUDENT_LOGGED_IN);
		List<Module> list = moduleServiceImpl.getAllModuleByProgId(prog_id,student);
		System.out.println(list.toString());
		model.addAttribute("studentModuleList", list);
		model.addAttribute("studentModel", student);
		model.addAttribute("programModel",programServiceImpl.findProgramById(prog_id).get());
		return "student/module";
	}

	@PostMapping(value = "/saveStudentGameScore", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveStudentGameScore(HttpSession session,@RequestBody SaveGameScoreResponse gameScoreResponse,HttpServletRequest httpServletRequest) {
		log.debug(gameScoreResponse.toString());
		Student student = (Student) session.getAttribute(IConstants.STUDENT_LOGGED_IN);
		return	studentServiceImpl.updateGameScore(student,gameScoreResponse);
	}

	@PostMapping(value = "/saveStudentGameQuizScore", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String saveStudentGameQuizScore(HttpSession session,@RequestBody SaveGameQuizScoreResponse gameQuizScoreResponse,HttpServletRequest httpServletRequest) {
		log.debug(gameQuizScoreResponse.toString());
		Student student = (Student) session.getAttribute(IConstants.STUDENT_LOGGED_IN);
		return	studentServiceImpl.updateGameQuizScore(student,gameQuizScoreResponse);
	}

	@GetMapping("/privacy_policy")
	public String privacypolicy(){
		return "student/privacypolicy";

	}

/*
	@GetMapping("/certificate")
	public String certify(Model model, HttpSession session, HttpServletRequest httpRequest, HttpServletResponse httpResource){
		
		// Student student = (Student) session.getAttribute(IConstants.STUDENT_LOGGED_IN);


		model.addAttribute("name", "Anirudra Choudhury");
		model.addAttribute("programName", "Test");
		model.addAttribute("countModule","10");
		model.addAttribute("bg_image","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/certificate_background.jpg");
		model.addAttribute("logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/logo.png");
		model.addAttribute("certfied_logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/certified_logo.PNG");
		model.addAttribute("dev_sup_logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/dev_sup_logo.PNG");
		model.addAttribute("implemented_logo","https://prodlmscertificate.s3.ap-south-1.amazonaws.com/certificatetemplateimage/implemented_logo.PNG");
		try {
			File file = new PDFUtil().generatestaticPdfFromHtml(springTemplateEngine);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}

		return "certificate_template";

	}
	
*/
}
