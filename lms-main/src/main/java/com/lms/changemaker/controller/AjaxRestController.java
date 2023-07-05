package com.lms.changemaker.controller;

import java.io.*;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.lms.changemaker.entity.*;
import com.lms.changemaker.entity.Module;
import com.lms.changemaker.service.ProgramService;
import com.lms.changemaker.serviceimpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.lms.changemaker.constants.IConstants;
import com.lms.changemaker.utilities.PDFUtil;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@RestController
public class AjaxRestController {

	@Autowired
	private ScoreServiceImpl scoreServiceImpl;

	@Autowired
	SchoolServiceImpl schoolServiceImpl;

	@Autowired
	private PlasticItemServiceImpl plasticItemServiceImpl;

	

	@Autowired
	private ProgramProgressServiceImpl programProgressServiceImpl;


	
	public static String certifyPath="/data/";





	 @GetMapping("/modules/{moduleId}/student/{studentId}/addscore/{score}")
	    public ResponseEntity<Score> addscore(@PathVariable Map<String, String> map,Model model,HttpSession httpSession) {

		     Student student= (Student)httpSession.getAttribute(IConstants.STUDENT_LOGGED_IN);
		     Score td=  scoreServiceImpl.completeModule(
		    		 Integer.parseInt(map.get("studentId").strip()),
		    		 Integer.parseInt(map.get("moduleId").strip()),
		    		 Integer.parseInt(map.get("score").strip()),
		    		 student);

		    
	        return  new ResponseEntity<>(td,HttpStatus.OK);
	    }
	

	    @GetMapping("/program/{progId}/modules/{moduleId}/student/{studentId}/saveProgress/{progress}/taskId/{taskId}")
	    public ResponseEntity<ProgramProgress> saveProgress(@PathVariable Map<String, String> map,Model model,HttpSession httpSession) {
	    	ProgramProgress td=  programProgressServiceImpl.updateorinsertTaskProgress(
		    		 Integer.parseInt(map.get("studentId").strip()),
		    		 Integer.parseInt(map.get("progId").strip()),
		    		 Integer.parseInt(map.get("moduleId")),
		    		 Integer.parseInt(map.get("taskId")),
		    		 Double.parseDouble(map.get("progress")));
	        return  new ResponseEntity<>(td,HttpStatus.OK);
	    }

	@PostMapping("/editGameDragDrop")
	public ResponseEntity<String> editGameDragDrop(@RequestParam("imagePlasticDragDrop") MultipartFile multipartPlasticImage, @ModelAttribute @Valid PlasticItem plasticItem, BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
		String msg="";
		if(session.getAttribute(IConstants.ADMIN_LOGGED_IN)!=null) {
			msg = plasticItemServiceImpl.editPlasticItemForDragDropGame(plasticItem, multipartPlasticImage);
			redirectAttributes.addAttribute("message",msg);

		}
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}


	
}
