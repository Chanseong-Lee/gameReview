package com.game.review.game.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class UploadController {
	
	//CKEDITOR 파일 업로드 누르고 서버로 전송 누를때 사용
	@RequestMapping(value="/imageUpload", method = RequestMethod.POST)	
	public void imageUpload(HttpServletRequest request,
			HttpServletResponse response,
			MultipartFile upload) throws Exception{
		System.out.println("확인용");
		//랜덤 문자 생성
		UUID uid = UUID.randomUUID();
		
		OutputStream out = null ;  //데이터를 출력할 때
		PrintWriter printWriter = null; //텍스트 출력
		
		//인코딩
		response.setCharacterEncoding("urf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("확인용2" + upload.getOriginalFilename());
//		System.out.println(upload.getBytes());
		try {
			//파일 이름 가져오기
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			System.out.println("fileName :" + fileName);
			//이미지 경로 생성
			String path = "D:/imgUpload" ;
			 System.out.println("path : " + path);
			String ckUploadPath = path + File.separator + uid + "_" + fileName ;
			System.out.println("ckUploadPath :" + ckUploadPath);
			File folder = new File(path);
			
			//해당 디렉토리 확인
			if(!folder.exists()) {
				try {
					folder.mkdir(); //폴더생성
				}catch(Exception e) {
					e.getStackTrace();
				
				}
			}
			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush(); //outputStream에 저장된 데이터를 전송하고 초기화
			System.out.println("확인용3");
	//	    String callback = request.getParameter("CKEditorFuncNum");
		    printWriter = response.getWriter();
		    String fileUrl = "/imgUpload/"+uid+"_"+fileName; //작성화면
		    
		    //업로드시 메세지 출력
         printWriter.println("{\"filename\" : \""+fileName+"\","
         		+ " \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");

         printWriter.flush();
         
   		
         
         

		}catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(out != null) { 
					out.close(); 
				}
				if(printWriter != null) { 
					printWriter.close(); 
				}
			} catch(IOException e) {
				e.printStackTrace(); 
			}
		} 
		return; 
	}
}
       
		
		
	


