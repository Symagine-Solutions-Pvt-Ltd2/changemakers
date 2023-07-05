package com.lms.changemaker.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.lms.changemaker.utilities.PDFUtil;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lms.changemaker.entity.School;
import com.lms.changemaker.entity.Student;
import com.lms.changemaker.serviceimpl.*;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.IOException;



@Controller
public class MainController {









	@GetMapping("/privacy_policy")
    public String privacy_policy() {
        return "landing_privacypolicy";
    }

    @GetMapping("/imprint")
    public String imprint() {
        return "imprint";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    
	@GetMapping("/admin")
    public String admin() {
        return "admin/home";
    }


    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    /*@GetMapping("/pdf")
    public String pdf(Model model, HttpSession session, @ModelAttribute("message") final String message) {

        *//*try {
           *//**//* new PDFUtil().generatePdfFromHtmlTest(springTemplateEngine);*//**//*
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }*//*

        return "index";
    }*/
}
