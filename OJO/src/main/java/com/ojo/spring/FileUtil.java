package com.ojo.spring;

import java.io.File;
import java.util.Iterator;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Component
public class FileUtil {
	

	private static String getRandomString() {
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	
	public static String updateImg(MultipartHttpServletRequest mpRequest) throws Exception {
		String filePath = "C:/Users/jack0/spring/workspace/OJO2/src/main/webapp/WEB-INF/profileimage/";
//		String filePaths = "C:/Users/jack0/spring/sts-bundle/pivotal-tc-server/instances/base-instance/wtpwebapps/OJO2/WEB-INF/profileimage/";
		
		File file = new File(filePath);
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		String userprofile = "";
		
		
		if(file.exists() == false) {
			file.mkdirs();
		}
		System.out.println();
		
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				userprofile = storedFileName;
			}
		}
		
		System.out.println("성공");
		System.out.println(userprofile);
		return userprofile;
		
	}





}
