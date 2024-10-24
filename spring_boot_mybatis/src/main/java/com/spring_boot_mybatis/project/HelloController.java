package com.spring_boot_mybatis.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
	//view 페이지 사용하지 않음
	//@ResponseBody //반환값을 HTTP Response Body에 직접 전송
	/*
	 * @RequestMapping("/") //요청 url : http://localhost:8080 
	 * public String home() {
	 * System.out.println("이미지 출력"); //static 파일(resources/static/**) 
	 * //return  "Hello Boot!!"; //문자열을 그대로 브라우저에게 전송 
	 * return "index"; //index.jsp }
	 */
	
	//view 페이지 사용 http://localhost:8080/hello 요청을 처리하는 메소드
	//스프링 부트 appaication run 은 컨텍스트명 사용하지 않음
	@RequestMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("message","안녕하세요");
		return "hello"; //뷰 결정 hello.jsp
	}
	
}
