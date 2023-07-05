package com.lms.changemaker.service;

import java.util.List;
import java.util.Optional;

import com.lms.changemaker.entity.Video;
import org.springframework.web.multipart.MultipartFile;

public interface VideoService {

	
	Video addVideo(Video module);
		
    List<Video> findAllVideos();
   
    Optional<Video> findVideoById(int videoId);

    Video editVideo(Video video);



    Video saveVideos(Video video, MultipartFile multipartVideoEng);

    String saveMultipleVideos(Video video, MultipartFile[] multipartVideoEng);
}
