package com.spring_boot_mybatis.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//프로젝트는 springboot 규칙을 따르는 프로그램으로 등록
@SpringBootApplication
@ComponentScan(basePackages= {"com.spring_boot_mybatis.project"})
@MapperScan(basePackages= {"com.spring_boot_mybatis.project"})
public class SpringBootMybatisApplication {

	//website 시작 지점
	public static void main(String[] args) {
		//서버 시작
		//스프링부트는 반드시 main() 포함하는 java 파일이 있어야 함
		SpringApplication.run(SpringBootMybatisApplication.class, args);
	}

}
