package com.lms.changemaker.serviceimpl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.lms.changemaker.entity.Video;
import com.lms.changemaker.service.S3BucketStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import static com.amazonaws.services.s3.internal.Constants.MB;
import static com.lms.changemaker.constants.IConstants.*;

@Service
public class S3BucketStorageServiceImpl implements S3BucketStorageService {

    private Logger logger = LoggerFactory.getLogger(S3BucketStorageServiceImpl.class);

    @Autowired
    protected TransferManager transferManager;

    @Value("${application.bucket.image}")
    private String bucketImage;

    @Value("${application.bucket.videoEnglish}")
    private String bucketVideoEnglish;

    @Value("${application.bucket.videoBengali}")
    private String bucketVideoBengali;

    @Value("${application.bucket.certificate}")
    private String bucketCertificate;

    @Value("${cloud.aws.region.static}")
    private String region;

    public String getFilePath(String bucketName,String fileName){

        return "https://"+bucketName+".s3."+region+".amazonaws.com/"+fileName;
    }

    public String getVideoFilePath(String bucketName,String fileName){
        return "https://"+bucketName.split("/")[0]+".s3."+region+".amazonaws.com"+File.separator+bucketName.split("/")[1]+File.separator+fileName;
    }

    
    @Override
    public String uploadFormFile(MultipartFile file, String bucketType) {

        String bucket="";
        switch (bucketType) {
            case IMAGE:
                bucket = bucketImage;
                break;
            case VIDEO_ENG:
                bucket = bucketVideoEnglish;
                break;
            case VIDEO_BEN:
                bucket = bucketVideoBengali;
                break;
        }
       
        int oneDay = 1000 * 60 * 60 * 24;
        Date oneDayAgo = new Date(System.currentTimeMillis() - oneDay);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        try {

           // transferManager.abortMultipartUploads(bucket, oneDayAgo);
           
            System.out.println("Object upload complete");

            PutObjectRequest request = new PutObjectRequest(bucket,file.getOriginalFilename(), file.getInputStream(), metadata);
            Upload upload = transferManager.upload(request);
            upload.waitForCompletion();
            upload.addProgressListener((ProgressListener) progressEvent -> {
                String transferredBytes = "Uploaded bytes: " + progressEvent.getBytesTransferred();
                logger.info(transferredBytes);
            });
            if(upload.isDone()){
                return   getVideoFilePath(bucket,file.getOriginalFilename());
            }
            request.setGeneralProgressListener(progressEvent -> {

                System.out.println("Transferred bytes:getBytes " + progressEvent.toString());

            });
        } 
        catch (IOException | SdkClientException | InterruptedException e) {
            e.printStackTrace();
        }
        catch (AmazonClientException amazonClientException) {
            System.out.println("Unable to upload file, upload aborted.");
            amazonClientException.printStackTrace();
        }

        return  null;
    }

    /**
     * Downloads file using amazon S3 client from S3 bucket
     *
     * @param keyName
     * @return ByteArrayOutputStream
     */

    @Override
    public ByteArrayOutputStream downloadFile(String keyName, String bucketName) {
     /*   try {
            S3Object s3object = transferManager.getObject(new GetObjectRequest(bucketName, keyName));

            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            return outputStream;
        } catch (IOException ioException) {
            logger.error("IOException: " + ioException.getMessage());
        } catch (AmazonServiceException serviceException) {
            logger.info("AmazonServiceException Message:    " + serviceException.getMessage());
            throw serviceException;
        } catch (AmazonClientException clientException) {
            logger.info("AmazonClientException Message: " + clientException.getMessage());
            throw clientException;
        }*/

        return null;
    }




    @Override
    public String uploadCertificate(File file) {
        transferManager.upload(new PutObjectRequest(bucketCertificate, file.getName(), file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

   return  getFilePath(bucketCertificate,file.getName());

    }

    @Override
    public String uploadFormImage(MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            transferManager.upload(new PutObjectRequest(bucketImage, file.getOriginalFilename(), file.getInputStream(),metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  getFilePath(bucketImage,file.getOriginalFilename());

    }
}
