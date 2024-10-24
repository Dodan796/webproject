package com.spring_boot_mybatis.project;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //외부파일 연결 설정 관련 클래스(일반 설정 : application.property 파일에 설정할 수 없는 내용을 프로그램으로 구성)
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//외부파일 이미지 사용하도록 mapping
		registry.addResourceHandler("/prd_images/**")
		.addResourceLocations("file:///usr/local/project/product_images/"); // 서버 경로
		//.addResourceLocations("file:///C:/springWorkspace/product_images/");
		registry.addResourceHandler("/images/**","/audio/**")
		.addResourceLocations("file:///usr/local/project/upload/"); // 서버 경로
		//.addResourceLocations("file:///C:/springBootWorkspace/upload/");//한개 절대경로에 여러 url 매핑 가능		
	}
	
	

}
