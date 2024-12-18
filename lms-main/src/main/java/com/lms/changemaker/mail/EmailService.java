package com.lms.changemaker.mail;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import freemarker.template.TemplateException;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);
    void sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        String ...templateModel);
    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);
    

    void sendMessageUsingThymeleafTemplate(
            String to, String subject, Map<String, Object> templateModel, String template)
            throws MessagingException;

    void sendMessageUsingFreemarkerTemplate(String to,
                                            String subject,
                                            Map<String, Object> templateModel)
            throws IOException, TemplateException, MessagingException;
}