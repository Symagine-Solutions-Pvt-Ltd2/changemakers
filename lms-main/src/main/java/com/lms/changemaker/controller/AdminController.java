package com.lms.changemaker.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.lms.changemaker.entity.*;
import com.lms.changemaker.entity.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lms.changemaker.constants.IConstants;
import com.lms.changemaker.file.FileStorageServiceImpl;
import com.lms.changemaker.serviceimpl.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

	   
	   @Autowired
	   private UserServiceImpl userServiceImpl;
	   
	   @Autowired
	   private FileStorageServiceImpl fileStorageService;
	   
	   @Autowired
	   private ModuleServiceImpl moduletServiceImpl;
	   
		@Autowired
		private VideoServiceImpl videoServiceImpl;

		@Autowired
		private GameServiceImpl gameServiceImpl;
		
		@Autowired
		private QuizServiceImpl quizServiceImpl;
		
		@Autowired
		private ProgramServiceImpl programServiceImpl;
		
		@Autowired
	    private LocaleResolver localeResolver;

		@Autowired
		private PlasticItemServiceImpl plasticItemServiceImpl;
		
	
		@Autowired
		private StandardServiceImpl standardServiceImpl;

		@Autowired
		private PlasticCategoryServiceImpl plasticCategoryServiceImpl;
	   


	/*@Autowired
	private Translate translate;*/
	   
	   @GetMapping("/login")
	    public String login(Model model,HttpServletRequest request,HttpSession httpSession) {
		   if(httpSession.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) 
			   return "redirect:/admin/home";  
		   

	         model.addAttribute("userLoginForm",new User());
	        return "admin/login";
	    }
	   
   
	   @PostMapping("/login")
	    public String login( @ModelAttribute @Valid User userForm, BindingResult bindingResult,HttpSession session) {
		   
		   if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)==null) {
			   if(userServiceImpl.autologin(userForm)==1) {
					  session.setAttribute(IConstants.ADMIN_LOGGED_IN,userServiceImpl.findUserByUserName(userForm.getUsername()));   
					  return "redirect:/admin/home";
			   }   
		   }
		   
		   return "redirect:/admin/login";
	    } 
	   
	   
	   @GetMapping("/home")
	    public String home(Model model,HttpSession session,@ModelAttribute("message") final String message,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) {
		   
		   if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)==null)
			   return  "redirect:/admin/login";

		
		   System.out.println(localeResolver.resolveLocale(httpServletRequest).getLanguage());
		   Locale locale=localeResolver.resolveLocale(httpServletRequest);
		      localeResolver.setLocale(httpServletRequest,httpServletResponse,new Locale("en"));
		   List<Language> languageList=new ArrayList<>();
		   languageList.add(new Language(0,"English","en"));
		   languageList.add(new Language(1,"Bengali","bn"));

	
		   User  user= (User) session.getAttribute(IConstants.ADMIN_LOGGED_IN);
		   user.setLanguage(locale.getDisplayName());
		   user.setLanguageCode(locale.getLanguage());
		   model.addAttribute("addSubAdminForm",new User());
		   model.addAttribute("plasticDragDropCategoryList", plasticCategoryServiceImpl.findPlasticCategoriesByGameId(1));
		   model.addAttribute("plasticSegregationCategoryList", plasticCategoryServiceImpl.findPlasticCategoriesByGameId(4));
		   model.addAttribute("editSubAdminForm",new User());
		   model.addAttribute("addProgramForm",new Program());
		   model.addAttribute("editProgramForm",new Program()); 
		   model.addAttribute("addGameForm",new Game()); 
		   model.addAttribute("editGameForm",new Game());
		   model.addAttribute("addVideoForm",new Video());
		   model.addAttribute("editVideoForm",new Video());
		   model.addAttribute("addQuizForm",new Quiz());
		   model.addAttribute("editQuizForm",new Quiz());
		   model.addAttribute("changePassForm",new User());
		   model.addAttribute("addModuleForm",new com.lms.changemaker.entity.Module());
		   model.addAttribute("programList",programServiceImpl.findAllPrograms());
		   model.addAttribute("subAdminList",userServiceImpl.getSubAdminDashBoardList(locale));
		   model.addAttribute("videoList",videoServiceImpl.findAllVideos());
		   model.addAttribute("gameList",gameServiceImpl.findAllGames());
		   model.addAttribute("quizList",quizServiceImpl.findAllQuizs());
		   model.addAttribute("langList",languageList);
		   model.addAttribute("userModel",user);
		   model.addAttribute("addGameDragDropForm",new PlasticItem());
		   model.addAttribute("editDragDropItemForm",new PlasticItem());
		   model.addAttribute("dragDropPlasticItemList",plasticItemServiceImpl.findPlasticItemByGameId(1));
		   model.addAttribute("segregationPlasticItemList",plasticItemServiceImpl.findPlasticItemByGameId(4));
		   model.addAttribute("weightCalulatorPlasticItemList",plasticItemServiceImpl.findPlasticItemByGameId(2));
		   model.addAttribute("listFindObjectPlasticItems",plasticItemServiceImpl.findPlasticItemByGameId(3));
		   model.addAttribute("standardList",standardServiceImpl.findAllStandards());
		   if(message!=null)
			    model.addAttribute("message",message);
			    else
			    model.addAttribute("message","");	
		   
		   return  "admin/home";
	    }
	@PostMapping("/changePasswordUser")
	public String changePasswordStu( @ModelAttribute @Valid User user, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {

		if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {


			int x=  userServiceImpl.changePassword(user);
			redirectAttributes.addAttribute("message","Password has been updated.");
		}

		return "redirect:/admin/home";
	}

	@PostMapping("/getModuleByProgram")
	     @ResponseBody
	    public String getModuleByProgram(HttpSession session,@RequestParam("progId") String progId,HttpServletRequest httpServletRequest) {
		 if(progId.isEmpty())
			 return null;
		Locale locale=localeResolver.resolveLocale(httpServletRequest);
		   return moduletServiceImpl.getModuleListByProg(Integer.parseInt(progId),locale) ;
	    }



	@PostMapping("/getItemsByGame")
	@ResponseBody
	public String getItemsByGame(HttpSession session,@RequestParam("gameId") String gameId,HttpServletRequest httpServletRequest) {

		return  plasticItemServiceImpl.getAllPlasticItemByGame(Integer.parseInt(gameId));

	}
	   
	   @GetMapping("/logout")
	    public String logout(Model model,HttpSession session) {
		   
		   if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {
			   session.removeAttribute(IConstants.ADMIN_LOGGED_IN);   
		   }
		   return  "redirect:/admin/login";
	    }

	@PostMapping("/editSubAdmin")
	public String editSubAdmin( @ModelAttribute @Valid User userForm, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {

		if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {


			String msg=userServiceImpl.editSubAdmin(userForm);
			redirectAttributes.addAttribute("message",msg);

			return "redirect:/admin/home";

		}

		return "redirect:/admin/login";
	}



	@PostMapping("/addSubAdmin")
	    public String addSubAdminForm( @ModelAttribute @Valid User userForm, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {
		   
		   if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {

			          System.out.println(userForm.getPrograms().toString());
			          String msg=userServiceImpl.registerSubAdmin(userForm);
			          redirectAttributes.addAttribute("message",msg);
				 
					  return "redirect:/admin/home";
			    
		   }
		   
		   return "redirect:/admin/login";
	    } 
	   
	   
	   @PostMapping("/addModule")
	    public String addModule(@RequestParam("moduleImg") MultipartFile multipartModuleFile, @RequestParam("deepDiveFile") MultipartFile multipartDeepDiveFile,@ModelAttribute @Valid Module addModuleForm, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {
		   
		   if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {

              String msg= moduletServiceImpl.addnewModule(multipartModuleFile,multipartDeepDiveFile,addModuleForm);
               redirectAttributes.addAttribute("message",msg);
					  return "redirect:/admin/home";
			    
		   }
		   
		   return "redirect:/admin/login";
	    } 
	   
	   @PostMapping("/addProgram")
	    public String addProgram(@RequestParam("progImg") MultipartFile multipartProgramFile,@ModelAttribute @Valid Program addProgramForm, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {
		   
		   if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {



             String msg= programServiceImpl.addnewProgram(multipartProgramFile,addProgramForm);
              redirectAttributes.addAttribute("message",msg);
					  return "redirect:/admin/home";
			    
		   }
		   
		   return "redirect:/admin/login";
	    } 
	   
	   @PostMapping("/editProgramForm")
	    public String editProgramForm(@RequestParam("progImg") MultipartFile multipartProgramFile,@ModelAttribute @Valid Program addProgramForm, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {
		   
		   if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {
			   

					
            String msg= programServiceImpl.editProgram(multipartProgramFile,addProgramForm);
             redirectAttributes.addAttribute("message",msg);
					  return "redirect:/admin/home";
			    
		   }
		   
		   return "redirect:/admin/login";
	    }

	@PostMapping("/editVideo")
	public String editVideo(@ModelAttribute @Valid Video video, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {

		if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {

			System.out.println(video.toString());
			Video video1= videoServiceImpl.editVideo(video);
			redirectAttributes.addAttribute("message","Video has been updated");
			return "redirect:/admin/home";

		}

		return "redirect:/admin/login";
	}
	@PostMapping("/editGame")
	public String editGame(@ModelAttribute @Valid Game game, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {

		if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {

			gameServiceImpl.editVideo(game);
			redirectAttributes.addAttribute("message","Game has been updated");
			return "redirect:/admin/home";

		}

		return "redirect:/admin/login";
	}
	   @PostMapping("/addVideo")
	    public String addVideo(@ModelAttribute @Valid Video video,@RequestParam("videoEnglish") MultipartFile[] multipartVideoEng, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {
		     String msg="";
		   if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {
			   System.out.println(multipartVideoEng.length);
			  if(multipartVideoEng[0].isEmpty()){
				   msg="Could not upload video.Video file missing";
			     }
				  else{
						msg=videoServiceImpl.saveMultipleVideos(video,multipartVideoEng);
					}

		   }
		    else
		    	msg="Session Expired.Please login again.";

		    
		   redirectAttributes.addAttribute("message",msg);
		   return "redirect:/admin/home";
	    } 
	   
	   @PostMapping("/addGame")
	    public String addGame(@ModelAttribute @Valid Game game, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {
		   
		   if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {
			   

					
          gameServiceImpl.addGame(game);
           redirectAttributes.addAttribute("message","Game has been added.");
					  return "redirect:/admin/home";
			    
		   }
		   
		   return "redirect:/admin/login";
	    } 
	   
	   @PostMapping("/addQuiz")
	    public String addQuiz(@ModelAttribute @Valid Quiz quiz, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {
		   
		   if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {
			   System.out.println("Quiz///"+quiz.toString());
          quizServiceImpl.addNewQuiz(quiz);
          redirectAttributes.addAttribute("message","Quiz has been added.");
					  return "redirect:/admin/home";
			    
		   }
		   
		   return "redirect:/admin/login";
	    }  
	@PostMapping("/editQuiz")
	public String editQuiz(@ModelAttribute @Valid Quiz quiz, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {

		if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {
			System.out.println("Quiz///"+quiz.toString());
			quizServiceImpl.editNewQuiz(quiz);
			redirectAttributes.addAttribute("message","Quiz has been updated.");
			return "redirect:/admin/home";

		}

		return "redirect:/admin/login";
	}

	@PostMapping("/addGameDragDrop")
	public String addGameDragDrop(@RequestParam("imagePlasticDragDrop") MultipartFile multipartPlasticImage,@ModelAttribute @Valid PlasticItem plasticItem, BindingResult bindingResult,HttpSession session,RedirectAttributes redirectAttributes) {

		if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {
			System.out.println("multipartPlasticImage = " + multipartPlasticImage + ", plasticItem = " + plasticItem + ", bindingResult = " + bindingResult + ", session = " + session + ", redirectAttributes = " + redirectAttributes);
			String msg = plasticItemServiceImpl.savePlasticItemForDragDropGame(plasticItem, multipartPlasticImage);
			redirectAttributes.addAttribute("message",msg);
			return "redirect:/admin/home";

		}

		return "redirect:/admin/login";
	}

	
	
	

}
