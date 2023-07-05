package com.lms.changemaker.service;

import com.lms.changemaker.entity.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;

public interface S3BucketStorageService {
    

   

    String uploadFormFile(MultipartFile file, String bucketType);

    ByteArrayOutputStream downloadFile(String keyName, String bucketName);

    String uploadCertificate(File file);

    String uploadFormImage(MultipartFile file);
}
