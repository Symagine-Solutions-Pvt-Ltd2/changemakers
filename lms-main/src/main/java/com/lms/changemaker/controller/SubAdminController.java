package com.lms.changemaker.controller;

import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.lms.changemaker.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.lms.changemaker.constants.IConstants;
import com.lms.changemaker.service.JwtStudentDetailsService;
import com.lms.changemaker.serviceimpl.*;
import com.lms.changemaker.utilities.JwtTokenUtil;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/subadmin")
@Slf4j
public class SubAdminController {

	   
	   @Autowired
	   private  UserServiceImpl userServiceImpl;
	   
	   
	   @Autowired
	   private ModuleServiceImpl moduletServiceImpl;
	   
	
	   @Autowired
	   private SchoolServiceImpl schoolServiceImpl;
	   
	   @Autowired
	   private ProgramServiceImpl programServiceImpl;
	   
	   @GetMapping("/login")
	    public String login(Model model,HttpServletRequest request,HttpSession httpSession) {
		   if(httpSession.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN)!=null) 
			   return "redirect:/subadmin/home";  
		   

	         model.addAttribute("userLoginForm",new User());
	        return "subadmin/login";
	    }
	   
   
	   @PostMapping("/login")
	    public String login( @ModelAttribute @Valid User userForm, BindingResult bindingResult,HttpSession session) {
		   
		   if(session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN)==null) {
			   if(userServiceImpl.autologinSubadmin(userForm)==1) {
					  session.setAttribute(IConstants.SUB_ADMIN_LOGGED_IN,userServiceImpl.findUserByEmail(userForm.getEmail()));   
					  return "redirect:/subadmin/home";
			   }   
		   }
		   
		   return "redirect:/subadmin/login";
	    }
	@PostMapping("/changePasswordSchool")
	public String changePasswordStu(@ModelAttribute @Valid School school, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {

		if(session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN)!=null) {


			int x=  schoolServiceImpl.changePassword(school);
			redirectAttributes.addAttribute("message","Password has been updated.");
		}

		return "redirect:/subadmin/home";
	}
	   
	   @GetMapping("/home")
	    public String home(Model model,@ModelAttribute("message") final String message,HttpSession session) {
		   
		   if(session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN)==null)
			   return  "redirect:/subadmin/login";
		   
		   User user=  (User) session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN);
		   model.addAttribute("addSchoolForm",new School());
		   model.addAttribute("changePassForm",new School());
		   model.addAttribute("editSchoolForm",new School());
		   model.addAttribute("editAssignProgForm",new EditProgramAssignSubAdmin());
		   model.addAttribute("moduleList",moduletServiceImpl.findAllModules());
		   model.addAttribute("programList",programServiceImpl.getAssignedProgramDetailsByUserID(user.getUserId()));
		   model.addAttribute("schoolList",schoolServiceImpl.getSchoolDashBoardList(user.getUserId()));
		   if(message!=null)
			   model.addAttribute("message",message);
		   else
			   model.addAttribute("message","");

		   return  "subadmin/home";
	    }

	   
	   @GetMapping("/logout")
	    public String logout(Model model,HttpSession session) {
		   
		   if(session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN)!=null) {
			   session.removeAttribute(IConstants.SUB_ADMIN_LOGGED_IN);   
		   }
		   return  "redirect:/subadmin/login";
	    }
	

	@PostMapping(value="/editSchoolAdmin",produces = MediaType.TEXT_HTML_VALUE)
	public String editSchool( @ModelAttribute School school, BindingResult bindingResult,HttpSession session) {

		if(session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN)!=null) {

			User user=  (User) session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN);
			schoolServiceImpl.editSchool(school,user);

			return "redirect:/subadmin/home";

		}

		return "redirect:/subadmin/login";
	}

	@PostMapping(value = "/addSchool",produces = MediaType.TEXT_HTML_VALUE)
	    public String addSubAdminForm( @ModelAttribute  School school, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {
		   
		   if(session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN)!=null) {
			   
			User user=  (User) session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN);
			String msg=schoolServiceImpl.registerSchool(school,user);
			   redirectAttributes.addAttribute("message",msg);
					  return "redirect:/subadmin/home";
			    
		   }
		   
		   return "redirect:/subadmin/login";
	    }
	@GetMapping(value = "/getAssignedProgram")
	@ResponseBody
	public String getAssignedPrograms(HttpSession session) {

		User user=  (User) session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN);
		return userServiceImpl.getAssignedPrograms(user);
	}


	@PostMapping(value="/editAssignProg")
	public String editAssignProg( @ModelAttribute EditProgramAssignSubAdmin editProgramAssignSubAdmin, BindingResult bindingResult,HttpSession session) {

		if(session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN)!=null) {

			User user=  (User) session.getAttribute(IConstants.SUB_ADMIN_LOGGED_IN);
			userServiceImpl.editAssignProg(editProgramAssignSubAdmin,user);

			return "redirect:/subadmin/home";

		}

		return "redirect:/subadmin/login";
	}
	
}
