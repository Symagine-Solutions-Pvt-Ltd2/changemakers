package com.lms.changemaker.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lms.changemaker.constants.IConstants;
import com.lms.changemaker.entity.Student;
import com.lms.changemaker.entity.Teacher;
import com.lms.changemaker.serviceimpl.*;

@Controller
@RequestMapping("teacher")
public class TeacherController {

	
	
	
    @Autowired
	private TeacherServiceImpl teacherServiceImpl;

    
    
    
	@Autowired
	SchoolServiceImpl schoolServiceImpl;
	
	
	
	
	@GetMapping("/home")
    public String goToHome(Model model,HttpSession session) {
	   
	   if(session.getAttribute(IConstants.TEACHER_LOGGED_IN)==null) {
		    model.addAttribute("message","");	
		   return  "redirect:/teacher/login";
	   } 
	   
	  
				   
	    Teacher teacher= (Teacher)session.getAttribute(IConstants.TEACHER_LOGGED_IN);
	   

	    model.addAttribute("teacherModel",teacher);

	   // model.addAttribute("studentModuleList", moduletServiceImpl.findModuleByStudent(student.getStudentId()));
        return "teacher/home";
    }
 
	   @GetMapping("/registration")
	    public String registration(Model model,HttpServletRequest request,@ModelAttribute("message") final String message) {
	        model.addAttribute("teacherForm",new Teacher());
	        model.addAttribute("schoolList",schoolServiceImpl.getSchools());
		    if(message!=null)
			    model.addAttribute("message",message);
			    else
			    model.addAttribute("message","");	
	        return "teacher/registration";
	    }

	   @PostMapping("/registration")
	    public String registration( @ModelAttribute @Valid Teacher teacherForm, BindingResult bindingResult,Model model,RedirectAttributes redirectAttributes) {
		   
		    System.out.println(teacherForm.toString());
		    teacherForm.setTeacherCode(String.valueOf(new Random().nextInt(9999))+teacherForm.getTeacherPhone().substring(1,3));
		    teacherForm.setSchoolId(teacherForm.getTeacherSchool().getSchoolId()); 	  
		    teacherForm.setIsActive(0);
		    teacherServiceImpl.insertTeacher(teacherForm);
		    
		    redirectAttributes.addAttribute("message",IConstants.APPROVAL_MESSAGE);
	        return "redirect:/teacher/registration";
	    } 
	   
	   
	   @GetMapping("/login")
	    public String login(Model model,HttpServletRequest request,HttpSession httpSession,@ModelAttribute("message") final String message) {
		   if(httpSession.getAttribute(IConstants.TEACHER_LOGGED_IN)!=null&&message==null) {
			   return "redirect:/teacher/home";   
		   }
	       
	         if(message!=null)
	         model.addAttribute("message",message);
	         else
	         model.addAttribute("message","");
	         
	         model.addAttribute("teacherLoginForm",new Teacher());
	        return "teacher/login";
	    }
	   
  
	   @PostMapping("/login")
	    public String login( @ModelAttribute @Valid Teacher teacherForm, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {
		   Teacher teacher= teacherServiceImpl.findTeacherByEmail(teacherForm.getTeacherEmail());
		   if(teacher!=null && session.getAttribute(IConstants.TEACHER_LOGGED_IN)==null) {
			   
			  
			   if(teacher.getIsActive()==0) {
				   
				   redirectAttributes.addAttribute("message",IConstants.APPROVAL_MESSAGE);
				   return "redirect:/teacher/login";
			   }else {
				   
				 
				   teacherServiceImpl.login(teacherForm);
				   session.setAttribute(IConstants.TEACHER_LOGGED_IN,teacherServiceImpl.findTeacherByEmail(teacherForm.getTeacherEmail()));   
			   }
			  
			 
		   }
		   return "redirect:/teacher/home";
	    } 
	   
	   
	   @GetMapping("/logout")
	    public String logout(Model model,HttpSession session) {
		   
		   if(session.getAttribute(IConstants.TEACHER_LOGGED_IN)!=null) {
			   session.removeAttribute(IConstants.TEACHER_LOGGED_IN);   
		   }
		   return  "redirect:/teacher/login";
	    }
	   
	   
	   
	   
}
