package com.lms.changemaker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.lms.changemaker.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.lms.changemaker.constants.IConstants;
import com.lms.changemaker.serviceimpl.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/school")
public class SchoolController {

	@Autowired
	SchoolServiceImpl schoolServiceImpl;

	   
	   @Autowired
	   UserServiceImpl userServiceImpl;
	   
	   @Autowired
	   StudentServiceImpl studentServiceImpl;
	   
	   @Autowired
	   StandardServiceImpl standardServiceImpl;
	   
	   @Autowired
	   private ModuleServiceImpl moduletServiceImpl;

	@Autowired
	private ProgramServiceImpl programService;
	
	@GetMapping("/login")
    public String login(Model model,HttpServletRequest request,HttpSession httpSession,@ModelAttribute("message") final String message) {
	   if(httpSession.getAttribute(IConstants.SCHOOL_LOGGED_IN)!=null) 
		   return "redirect:/school/home";
	   
		if(message!=null)
			model.addAttribute("message",message);
		else
			model.addAttribute("message","");

		model.addAttribute("schoolLoginForm",new School());
        return "school/login";
    }
   

   @PostMapping("/login")
    public String login(@ModelAttribute @Valid School schoolForm, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
	        String msg="";

	        try {
				if(session.getAttribute(IConstants.SCHOOL_LOGGED_IN)==null) {
					School details= schoolServiceImpl.autologinSchool(schoolForm);
					if(details!=null) {
						session.setAttribute(IConstants.SCHOOL_LOGGED_IN,schoolServiceImpl.findSchoolByEmail(details.getContactEmail()));
						msg="Login Successfull.";
						redirectAttributes.addAttribute("message",msg);
						return "redirect:/school/home";
					}else{
						msg="Email Id does not exists.";
					}
				}

			}   catch (Exception ex){
	        	ex.printStackTrace();
				msg="An Error Occured.";
			}
	   redirectAttributes.addAttribute("message",msg);
	   return "redirect:/school/login";
    } 
   
   
   @GetMapping("/home")
    public String home(Model model,HttpSession session,@ModelAttribute("message") final String message) {
	   
	   if(session.getAttribute(IConstants.SCHOOL_LOGGED_IN)==null)
		   return  "redirect:/school/login";
	   
	   School  school=(School)session.getAttribute(IConstants.SCHOOL_LOGGED_IN);
	   model.addAttribute("schoolDetail",school);
	   model.addAttribute("addStudentForm",new Student());
	   model.addAttribute("editStudentForm",new Student());
	   model.addAttribute("changePassForm",new Student());
	   model.addAttribute("editAssignProg",new ProgramAssignedDetail());
	   model.addAttribute("moduleList",moduletServiceImpl.findAllModules());
	   model.addAttribute("studentList",studentServiceImpl.getStudentList(school.getSchoolId()));
	   model.addAttribute("schoolList",schoolServiceImpl.getSchools());
	   model.addAttribute("standardList",standardServiceImpl.findAllStandards());
	   model.addAttribute("programListSchool",programService.getAssignedProgramDetails(school.getUserId(),school.getSchoolId()));

	   if(message!=null)
		   model.addAttribute("message",message);
	   else
		   model.addAttribute("message","");
	   return  "school/home";
    }

   
   @GetMapping("/logout")
    public String logout(Model model,HttpSession session) {
	   
	   if(session.getAttribute(IConstants.SCHOOL_LOGGED_IN)!=null) {
		   session.removeAttribute(IConstants.SCHOOL_LOGGED_IN);   
	   }
	   return  "redirect:/school/login";
    }
   
   
   
   @PostMapping("/changePasswordStu")
   public String changePasswordStu( @ModelAttribute @Valid Student studentForm, BindingResult bindingResult,HttpSession session) {
	   
	   if(session.getAttribute(IConstants.SCHOOL_LOGGED_IN)!=null) {
		   
		   
		    int x=  studentServiceImpl.changePassword(studentForm);
		    
	   }
	   
	   return "redirect:/school/home";
   } 
   
   @PostMapping("/addStudent")
    public String addStudentForm( @ModelAttribute @Valid Student studentForm, BindingResult bindingResult,HttpSession session) {
	   
	   if(session.getAttribute(IConstants.SCHOOL_LOGGED_IN)!=null) {

		   School  school=(School)session.getAttribute(IConstants.SCHOOL_LOGGED_IN);
		    int x=  studentServiceImpl.registerStudent(studentForm,school);
		    
	   }
	   
	   return "redirect:/school/home";
    }


	@PostMapping("/editStudent")
	public String editStudent( @ModelAttribute @Valid Student studentForm, BindingResult bindingResult,HttpSession session) {

		if(session.getAttribute(IConstants.SCHOOL_LOGGED_IN)!=null) {

			School  school=(School)session.getAttribute(IConstants.SCHOOL_LOGGED_IN);
			int x=  studentServiceImpl.editStudent(studentForm,school);

		}

		return "redirect:/school/home";
	}

	@GetMapping(value = "/getSchoolAssignedProgram")
	@ResponseBody
	public String getAssignedPrograms(HttpSession session) {

		School school = (School) session.getAttribute(IConstants.SCHOOL_LOGGED_IN);
		return schoolServiceImpl.getAssignedPrograms(school);
	}
}
