package com.lms.changemaker.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
 

@Data
@NoArgsConstructor
public class Mail {
 
    private String mailFrom;
 
    private String mailTo;
 
    private String mailCc;
 
    private String mailBcc;
 
    private String mailSubject;
 
    private String mailContent;
 
    private String contentType;
 
    private List < Object > attachments;
 
 
 
}