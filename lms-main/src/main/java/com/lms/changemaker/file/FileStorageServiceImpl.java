package com.lms.changemaker.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lms.changemaker.service.FileStorageService;

import com.lms.changemaker.utilities.UFileNotFoundException;
import com.lms.changemaker.utilities.UFileStorageException;





@Service
public class FileStorageServiceImpl implements FileStorageService{

	private  Path getImageModulePath,getFileDeepDivePath,getFileProgramPath;


	@PostConstruct
	public void initialize() {
		init();
	}
	
	
    public void init() {
		String getImageModule = "src\\main\\resources\\static\\upload\\module_files";
		this.getImageModulePath = Paths.get(getImageModule)
            .toAbsolutePath().normalize();
		String getFileDeepDive = "src\\main\\resources\\static\\upload\\module_files";
		this.getFileDeepDivePath = Paths.get(getFileDeepDive)
                .toAbsolutePath().normalize();
		String getFileProgram = "src\\main\\resources\\static\\upload\\program_files";
		this.getFileProgramPath = Paths.get(getFileProgram)
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.getImageModulePath);
            Files.createDirectories(this.getFileDeepDivePath);
            Files.createDirectories(this.getFileProgramPath);
        } catch (Exception ex) {
            throw new UFileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

 
  
    


	@Override
	public String storeModuleImage(MultipartFile file) {
		 // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new UFileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.getImageModulePath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new UFileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}
	
	@Override
	public String storeProgramImage(MultipartFile file) {
		 // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new UFileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.getFileProgramPath.resolve(fileName);
            System.out.println("fileName ://///"+targetLocation);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new UFileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}
	

	@Override
	public String storeDeepDiveFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new UFileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.getFileDeepDivePath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new UFileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
	}





	@Override
	public Resource loadModuleImage(String fileName) {
		 try {
			 
				Path filePath = this.getImageModulePath.resolve(fileName).normalize();
				Resource resource = new UrlResource(filePath.toUri());
				if (resource.exists()) {
					return resource;
				} else {
					throw new UFileNotFoundException("File not found " + fileName);
				}
			} catch (MalformedURLException ex) {
				throw new UFileNotFoundException("File not found " + fileName, ex);
			}
	}

	@Override
	public Resource loadProgramImage(String fileName) {
		 try {
			 
				Path filePath = this.getFileProgramPath.resolve(fileName).normalize();
				Resource resource = new UrlResource(filePath.toUri());
				if (resource.exists()) {
					return resource;
				} else {
					throw new UFileNotFoundException("File not found " + fileName);
				}
			} catch (MalformedURLException ex) {
				throw new UFileNotFoundException("File not found " + fileName, ex);
			}
	}



	@Override
	public Resource loadDeepDiveImage(String fileName) {
		 try {
			 
				Path filePath = this.getFileDeepDivePath.resolve(fileName).normalize();
				Resource resource = new UrlResource(filePath.toUri());
				if (resource.exists()) {
					return resource;
				} else {
					throw new UFileNotFoundException("File not found " + fileName);
				}
			} catch (MalformedURLException ex) {
				throw new UFileNotFoundException("File not found " + fileName, ex);
			}
	}

}
