package com.spring_boot_mybatis.project.file;

import java.io.File;//서버 파일 시스템에 저장하고 찾기 위해 객체 import
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile; //multipart/form-data 타입 저장 객체

@Controller
public class FileUploadController {
	
	//file upload form 요청에 대한 처리
	@RequestMapping("/fileUploadForm")
	public String viewUploadform() {
		return "upload/fileUploadForm"; //jsp페이지 반환 -> 파일전송 요청이 해당 jsp페이지에서 발생하게 됨
	}
	
	//파일전송 요청 처리 메소드
	//(1)1개 파일 업로드 : 파일명 중복 방지하기 위해 파일명 변경(UUID표준서식문자를 파일명 앞에 추가해서 파일명 변경)
	@RequestMapping("/fileUpload")
	public String fileUpload(@RequestParam("uploadFile") MultipartFile file, Model model) throws IOException {
		//1.파일 저장 경로 변수 (문자열형 변수) : 서버측에서 사용할 저장소의 주소(현재 컴퓨터의 파일시스템 c드라이브에 저장할 예정)
		//경로 마지막에 / 있어야 함
		//String uploadPath="C:/springBootWorkspace/upload/";  //경로는 /로 끝나야 함(파일명은 뒤에 추가함)
		String uploadPath = "/usr/local/project/upload/"; // 서버 경로
		
		//2.원본 파일 이름 저장(변경되는 파일명에 원파일명을 포함시키기 위해 변수에 저장)
		String originalFileName = file.getOriginalFilename();//전송된 파일명을 반환
		
		//3.파일명 중복 피하기 위해 UUID 생성 후 파일시스템에 저장할 이름 생성
		UUID uuid = UUID.randomUUID(); //8-4-4-4-12의 5개의 문자그룹을 하이픈으로 구분해서 생성해 줌
		String savedFileName = uuid.toString() + "_" + originalFileName; //변경된 파일명으로 저장소에 저장
		
		//4.파일객체 생성(현재 시스템에 저장하기위한 현재 시스템 사용 파일 객체 : 현재 시스템에 종속되는 파일 객체가 생성)
		//파일 객체 생성자에는 저장경로와파일명을 매개변수로 전달
		File sendFile = new File(uploadPath + savedFileName);
		
		//5.저장소(sendFile)로 파라미터로 전송된파일(file)을 전송
		file.transferTo(sendFile); //클라이언트가 전달한 파일을 현재 시스템에 저장
		
		//클라이언트에게 파일 저장 결과 전송 : 저장 파일명을 view페이지로 전달
		model.addAttribute("originalFileName",originalFileName);
		return "upload/fileUploadResultView"; //view 페이지 결정
	}
	
	//(2) 여러개의 파일 업로드
	//Multi-pard/Formdata 형식의 파일 : MultipartFile 객체변수에 저장 가능
	//여러개의 Multi-pard/Formdata가 전송 되므로 MultipartFile을 원소로 갖는 ArralyList에 저장할 수 있음
	@RequestMapping("/fileUploadMultiple")
	public String fileUploadMultiple(@RequestParam("uploadFileMulti") ArrayList<MultipartFile> files,
										Model model) throws IOException {
		//1.파일 저장경로 설정
		String uploadPath="C:/springBootWorkspace/upload/";  //경로는 /로 끝나야 함(파일명은 뒤에 추가함)
		
		//1-1. 여러개의 파일 이름 저장할 리스트변수 생성 : 결과 페이지에 출력할수 있게 저장
		ArrayList<String> originalFileNameList = new ArrayList<String>();
		
		//여러개 파일 집합을 순회하면서 각 파일의 정보 추출
		for (MultipartFile file :files) {
			//file에는 전송된 파일 1개의 정보가 저장되어 있음
			//2.원본 파일 이름 저장(변경되는 파일명에 원파일명을 포함시키기 위해 변수에 저장)
			String originalFileName = file.getOriginalFilename();//전송된 파일명을 반환
			//원본파일 이름을 리스트에 저장
			originalFileNameList.add(originalFileName);
			//3.파일명 중복 피하기 위해 UUID 생성 후 파일시스템에 저장할 이름 생성
			UUID uuid = UUID.randomUUID(); //8-4-4-4-12의 5개의 문자그룹을 하이픈으로 구분해서 생성해 줌
			String savedFileName = uuid.toString() + "_" + originalFileName; //변경된 파일명으로 저장소에 저장
			//4.파일객체 생성(현재 시스템에 저장하기위한 현재 시스템 사용 파일 객체 : 현재 시스템에 종속되는 파일 객체가 생성)
			//파일 객체 생성자에는 저장경로와파일명을 매개변수로 전달
			File sendFile = new File(uploadPath + savedFileName);
			//5.저장소로 전송
			file.transferTo(sendFile);//1개 파일이 저장소에 저장
		}//전달된 파일 여러개를 한개씩 저장소에 저장하는 코드
		
		model.addAttribute("originalFileNameList",originalFileNameList);
		return "upload/fileUploadMultipleResultVew";
	}
	
	//3. 파일명 변경 없이 upload
	///fileOriginalNameUplode요청에 대항 처리 메소드
	@RequestMapping("/fileOriginalNameUplode")
	public String fileOriginalNameUplode(@RequestParam("uploadFileOrigin") MultipartFile file,
										 Model model) throws IOException{
			//1. 파일 저장 경로 설정 : 디렉터리 생성 후 저장
			String uploadPath = "C:/springBootWorkspace/upload/product_image/";
			
			//2. 원본 파일 이름 저장
			String originFileName = file.getOriginalFilename();
			
			//3. 파일 객체 생성
			File sendFile = new File(uploadPath + originFileName);
			
			//4. 저장소로 전송
			file.transferTo(sendFile);
			
			model.addAttribute("originalFileName",originFileName);
		
		
		return "upload/fileUploadResultView";
	}
	
	//이미지 파일 업로드 폼 요청
	@RequestMapping("/imageFileUploadForm")
	public String imageFileUploadForm() {
		return "upload/imageFileUpload";
	}
	
	//upload 폴더에 이미지 파일 업로드
	//ajax가 요청 -> 문자열 데이터 반환(view 페이지 반환 아님)
	@ResponseBody
	@RequestMapping("/imageFileUpload")
	public String imageFileUpload(@RequestParam("uploadFile") MultipartFile file) throws IOException {
		
		String uploadPath = "c:/springBootWorkspace/upload/";
		
		String originFileName = file.getOriginalFilename();
		
		File sendFile = new File(uploadPath + originFileName);
		
		file.transferTo(sendFile);
		
		String result ="success";
		return result;
	}
	
	//이미지 파일 업로드 폼 요청
	@RequestMapping("/audioFileUploadForm")
	public String audioFileUploadForm() {
		return "upload/audioFileUpload";
	}
	
	//upload 폴더에 이미지 파일 업로드
	//ajax가 요청 -> 문자열 데이터 반환(view 페이지 반환 아님)
	@ResponseBody
	@RequestMapping("/audioFileUpload")
	public String audioFileUpload(@RequestParam("uploadFile") MultipartFile file) throws IOException {
		
		String uploadPath = "c:/springBootWorkspace/upload/";
		
		String originFileName = file.getOriginalFilename();
		
		File sendFile = new File(uploadPath + originFileName);
		
		file.transferTo(sendFile);
		
		String result ="success";
		return result;
	}	
}

























