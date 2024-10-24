package com.spring_boot_mybatis.project.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class FileDownloadController {

	//다운로드 받을 파일 리스트 출력 : upload폴더의 모든 파일 목록 출력
	@RequestMapping("/fileDownloadList")
	public String fileDownloadList(Model model) {
		
		//File 객체를 활용
		//1. 대상폴더의 파일 목록 리스트 추출해서
		File path = new File("C:/springBootWorkspace/upload");
		String[] filelist = path.list();//path 객체 참조 경로의 저장된 모든 파일의 목록을 반환
		
			
		//2. view 페이지에 전달
		model.addAttribute("filelist",filelist);
		
		return "download/fileDownloadListView";
	}
	
	//HttpServletResponse 객체를 활용해서 전송데이터 구성 후 전송
	@RequestMapping("/fileDownload/{file}")
	public void fileDownload(@PathVariable String file, HttpServletResponse response) throws IOException{
		//1. 파일 객체 생성
		File f = new File("C:/springBootWorkspace/upload/",file);
		
		//한글 파일명 인코딩
		String encodedFileName = new String(file.getBytes("UTF-8"),"ISO-8859-1");
		
		//2.파일 다운로드 response 객체 구성 : attachment;filename="사과.jpg"
		response.setContentType("application/download");
		response.setContentLength((int)f.length());
		response.setHeader("Content-Disposition", "attachment;filename=\"" + encodedFileName + "\"");
		
		//3.파일 다운로드(전송) : FileCopyUtils.copy(inputstream, outstream)
		//파일 입력 스트림 객세 생성(시스템에 저장된 다운로드 할 파일)
		FileInputStream fis = new FileInputStream(f);
		//출력 스트림 객체 생성 : 서버->클라이언트로 출력(response객체로부터 생성되어야 함)
		OutputStream os = response.getOutputStream();
		
		//다운로드(서버->클라이언트로 복사)
		//FileCopyUtils : 바이트 기반 입출력 스트림 클래스
		FileCopyUtils.copy(fis, os);		
	}
}








