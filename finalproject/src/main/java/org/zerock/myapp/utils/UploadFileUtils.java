package org.zerock.myapp.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class UploadFileUtils {
	
	public static String fileUpload(String uploadPath, String fileName, byte[] fileData, String ymdPath) throws Exception {
		
		UUID uid = UUID.randomUUID();
		log.trace("\t+ ☆★ uid : {}", uid);
		
		String newFileName = uid + "_" + fileName;
		String imgPath = uploadPath + ymdPath;
		log.trace("\t+ ☆★ newFilewName : {}", newFileName);
		log.trace("\t+ ☆★ imgPath : {}", imgPath);
		
		File target = new File(imgPath, newFileName);
		FileCopyUtils.copy(fileData, target);
		log.trace("\t+ ☆★ target : {}", target);
		
		File imge = new File(imgPath + File.separator + newFileName);
		log.trace("\t+ ☆★ imge : {}", imge);
		
		return newFileName;
	}//fileUpload
	
	public static String calcPath(String uploadPath) {
		  Calendar cal = Calendar.getInstance();
		  String yearPath = "\\" + cal.get(Calendar.YEAR);
		  String monthPath = yearPath + "\\" + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		  String datePath = monthPath + "\\" + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		  makeDir(uploadPath, yearPath, monthPath, datePath);
		  log.trace("\t+ ☆★ cal : {}", cal);
		  log.trace("\t+ ☆★ monthPath : {}", monthPath);
		  log.trace("\t+ ☆★ yearPath : {}", yearPath);
		  log.trace("\t+ ☆★ datePath : {}", datePath);

		  return datePath;
	 }//calcPath
	
	private static void makeDir(String uploadPath, String ... paths) {
		
		if(new File(paths[paths.length -1]).exists()) { return; }
		for(String path : paths) {
			File dirPath = new File(uploadPath  + path);
			log.trace("\t+ ☆★ dirPath : {}", dirPath);
			
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}//if
		}//for
		
	}//makeDir

}//end class