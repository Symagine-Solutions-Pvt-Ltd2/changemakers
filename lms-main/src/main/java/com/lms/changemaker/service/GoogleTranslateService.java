package com.lms.changemaker.service;

import com.lms.changemaker.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Locale;

public interface GoogleTranslateService {
    

    String translate(String text,String targetLang);

    String translate(String text, Locale locale);
}
