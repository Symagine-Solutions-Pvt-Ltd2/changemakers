package com.lms.changemaker.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lms.changemaker.repository.VideoRepository;
import com.lms.changemaker.entity.Video;
import com.lms.changemaker.service.VideoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.lms.changemaker.constants.IConstants.VIDEO_ENG;


@Service
public class VideoServiceImpl implements VideoService {

	

@Autowired
private VideoRepository videoRepository;


@Autowired
private S3BucketStorageServiceImpl s3BucketStorageService;

@Override
@Transactional
public Video addVideo(Video video) {
	return videoRepository.save(video);
}

@Override
public List<Video> findAllVideos() {

	return videoRepository.findAll();
}

@Override
public Optional<Video> findVideoById(int videoId) {
	return videoRepository.findById(videoId);
}



	@Override
	@Transactional
	public Video editVideo(Video video) {
		Video video1 = findVideoById(video.getVideoId()).get();
		video1.setVideoTitle(video.getVideoTitle());
		video1.setVideoUrlEnglish(video.getVideoUrlEnglish());
		addVideo(video1);
		return video1;
	}

	@Override
	@Transactional
	public Video saveVideos(Video video, MultipartFile multipartVideoEng) {

		String videoEng=s3BucketStorageService.uploadFormFile(multipartVideoEng,VIDEO_ENG);
		//String videoBen = s3BucketStorageService.uploadFormFile(video,multipartVideoBen, VIDEO_BEN);

		video.setVideoUrlEnglish(videoEng);
		return addVideo(video);
	}

	@Override
	public String saveMultipleVideos(Video video, MultipartFile[] multipartVideoEng) {

	try {
		for (MultipartFile multipartFile : multipartVideoEng) {
			Video tempVid=new Video();
			//tempVid.setVideoTitle(video.getVideoTitle());
			String videoEng=s3BucketStorageService.uploadFormFile(multipartFile,VIDEO_ENG);
			//String videoBen = s3BucketStorageService.uploadFormFile(video,multipartVideoBen, VIDEO_BEN);

			tempVid.setVideoUrlEnglish(videoEng);
			tempVid.setVideoTitle(multipartFile.getOriginalFilename());
			Video v=addVideo(tempVid);
			System.out.println(v.toString());
		}
	}   catch (Exception ex){
		ex.printStackTrace();
		return "An error occured.";
	}


		

		return "Video Uploaded successfully";
	}
}
