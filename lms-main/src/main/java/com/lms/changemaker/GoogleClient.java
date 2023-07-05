package com.lms.changemaker;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.google.api.services.sqladmin.SQLAdminScopes;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import static com.amazonaws.services.s3.internal.Constants.MB;


/*
@Configuration
public class GoogleClient {


    @Value("classpath:json/lms.json")
    Resource resourceFile;



    @Bean
    @Primary
    public Translate googleTranslate() {

        GoogleCredentials credentials = null;
        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream(resourceFile.getFile()))
                    .createScoped(Collections.singleton(SQLAdminScopes.CLOUD_PLATFORM));
            credentials.refreshIfExpired();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return  TranslateOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
*/
