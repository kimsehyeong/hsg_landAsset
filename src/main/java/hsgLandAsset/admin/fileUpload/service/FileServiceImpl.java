package hsgLandAsset.admin.fileUpload.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hsgLandAsset.admin.vo.FileVO;

@Service
public class FileServiceImpl {

	@Value("${file.upload-dir}")
	String uploadPath;
	
	
	public Map<String, Object> saveFile(MultipartFile multipartFile, String tab) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(multipartFile.isEmpty()) return null;
		
		String serverPath = uploadPath;
		
		String fileName = multipartFile.getOriginalFilename();
		String savePath = tab+"/";
		String uploadPath = serverPath+savePath;
		
		File dir = new File(uploadPath);
		if(!dir.exists()) dir.mkdirs();
		
		
		int sIndex = fileName.lastIndexOf(".");
		String prefixname = fileName.substring(0, sIndex);
		String surfixname = fileName.substring(sIndex+1);
		
		String saveName = UUID.randomUUID()+ "." +surfixname;
		
		File uploadFile = new File(uploadPath+saveName);
		multipartFile.transferTo(uploadFile);
		
		if(!uploadFile.exists()) {
			return null;
		}
		
		
		double fileSizeInBytes = multipartFile.getSize();
		double fileSizeInMB = fileSizeInBytes / (1024 * 1024);
		BigDecimal bd = BigDecimal.valueOf(fileSizeInMB);
		bd = bd.setScale(1, RoundingMode.HALF_UP); // Rounding to 1 decimal place
		
		
		String fileSize = bd.toString() + "MB";
	    String filePath = uploadPath ; // File path
		
		
		resultMap.put("fileName", fileName);
		resultMap.put("filePk",saveName);
		resultMap.put("fileSize",fileSize);
		resultMap.put("filePath",filePath);
		
		return resultMap;
	}
	
	public void downloadFile(HttpServletResponse response, FileVO vo, HttpServletRequest request) throws Exception {
//		String path = uploadPath+"/"+vo.getFilePath()+vo.getSavenm();
		String path = uploadPath+vo.getFilePath()+vo.getFilePk();
		System.out.println(vo.getFilePath());
		System.out.println(path);
		
		File file = new File(path);
		if(file.exists()) {
			response.setHeader("Content-Transfer-Encoding", "binary");
//			response.setHeader("Content-Disposition", "attachment; fileName=\"" + vo.getFileName()+"\"");
			
	        String fileName = vo.getFileName();
	        try {
	            String encodedFilename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
	            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFilename + "\"; filename*=UTF-8''" + encodedFilename);
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
			
			
			FileInputStream fileInputStream = new FileInputStream(path);
			OutputStream out = response.getOutputStream();
			
			int read = 0;
			byte[] buffer = new byte[1024];
			while((read = fileInputStream.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}	
			
			fileInputStream.close();
			out.close();
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();
			writer.print("<script>alert('파일이 존재하지 않습니다.'); </script>");
			writer.flush();
			writer.close();
			return;
		}
	}
}
