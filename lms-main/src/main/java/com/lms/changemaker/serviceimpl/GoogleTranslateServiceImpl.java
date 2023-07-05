package com.lms.changemaker.serviceimpl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.SdkClientException;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;
import com.lms.changemaker.entity.Video;
import com.lms.changemaker.service.GoogleTranslateService;
import com.lms.changemaker.service.S3BucketStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import static com.lms.changemaker.constants.IConstants.*;

/*
@Service
public class GoogleTranslateServiceImpl implements GoogleTranslateService {

    private Logger logger = LoggerFactory.getLogger(GoogleTranslateServiceImpl.class);

    @Autowired
    protected Translate translate;
    

    @Override
    public String translate(String text, String targetLang) {

        Detection detection = translate.detect(text);
        String detectedLanguage = detection.getLanguage();

        if(!detectedLanguage.equals(targetLang)){
            return translate.translate(
                    text,
                    Translate.TranslateOption.sourceLanguage(detectedLanguage),
                    Translate.TranslateOption.targetLanguage(targetLang)).getTranslatedText();
        }
         logger.info(detectedLanguage);
        return text;
    }

    @Override
    public String translate(String text, Locale locale) {

        if(!text.isEmpty()){
            String targetLang=locale.getLanguage();
            Detection detection = translate.detect(text);
            String detectedLanguage = detection.getLanguage();

            if(!detectedLanguage.equals(targetLang)){
                return translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage(detectedLanguage),
                        Translate.TranslateOption.targetLanguage(targetLang)).getTranslatedText();
            }
            logger.info(detectedLanguage);
        }

        return text;
    }
}
*/
