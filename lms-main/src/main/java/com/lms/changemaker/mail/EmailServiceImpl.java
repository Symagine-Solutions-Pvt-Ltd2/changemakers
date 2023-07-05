package com.lms.changemaker.mail;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.lms.changemaker.entity.User;

import freemarker.template.Template;
import freemarker.template.TemplateException;


@Service("EmailService")
public class EmailServiceImpl implements EmailService {

    public static final String NOREPLY_ADDRESS = "shayan.freelance0930@gmail.com";

    @Autowired
    private JavaMailSender emailSender;

//    @Autowired
//    private SimpleMailMessage template;
    
    @Autowired
    private SpringTemplateEngine thymeleafTemplateEngine;
    
    @Autowired
    private FreeMarkerConfigurer freemarkerConfigurer;
    
    @Value("classpath:/mail-logo.png")
    private Resource resourceFile;

    
    
    public static Map<String, Object> sendRegistraionMail(String name, String email, String password, String url, String portal) {
		Map<String, Object> templateModel = new HashMap<String, Object>();
		templateModel.put("recipientName",name);
		templateModel.put("portal",portal);
		templateModel.put("loginuri",url);
		templateModel.put("email",email);
		templateModel.put("password",password);
		return templateModel;
	}

    public static Map<String, Object> sendResetPasswordMail(String name,String email,String password,String url,String fromportal,String toPortal) {
        Map<String, Object> templateModel = new HashMap<String, Object>();
        templateModel.put("recipientName",name);
        templateModel.put("portal",fromportal);
        templateModel.put("toPortal",toPortal);
        templateModel.put("loginuri",url);
        templateModel.put("email",email);
        templateModel.put("password",password);
        return templateModel;
    }

    
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               String ...templateModel) {
       // String text = String.format(template.getText(), templateModel);  
       // sendSimpleMessage(to, subject, text);
    }

    @Override
    public void sendMessageWithAttachment(String to,
                                          String subject,
                                          String text,
                                          String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            // pass 'true' to the constructor to create a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(NOREPLY_ADDRESS);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void sendMessageUsingThymeleafTemplate(
        String to, String subject, Map<String, Object> templateModel,String template)
            throws MessagingException {
                
        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);
        
        String htmlBody = thymeleafTemplateEngine.process(template, thymeleafContext);

        sendHtmlMessage(to, subject, htmlBody);
    }

    @Override
    public void sendMessageUsingFreemarkerTemplate(
        String to, String subject, Map<String, Object> templateModel)
            throws IOException, TemplateException, MessagingException {

        Template freemarkerTemplate = freemarkerConfigurer.getConfiguration().getTemplate("template-freemarker.ftl");
        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);

        sendHtmlMessage(to, subject, htmlBody);
    }
    
    public void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(NOREPLY_ADDRESS);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        emailSender.send(message);
    }
   
}