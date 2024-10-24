package com.spring_boot_mybatis.project.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//db 질의에 사용될 데이터가 저장될 vo 클래스 이므로 필드명은 db 테이블의 컬럼명과 동일하게 사용하는것이 효율적
//application-config.xml에 component 태그 이용해서 자동 bean 등록했음(DI 기능 사용 가능)
public class ProductVO {
	private String prdNo;
	private String prdName;
	private int prdPrice;
	private String prdCompany;
	private int prdStock;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date prdDate;
	public String getPrdNo() {
		return prdNo;
	}
	public void setPrdNo(String prdNo) {
		this.prdNo = prdNo;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public int getPrdPrice() {
		return prdPrice;
	}
	public void setPrdPrice(int prdPrice) {
		this.prdPrice = prdPrice;
	}
	public String getPrdCompany() {
		return prdCompany;
	}
	public void setPrdCompany(String prdCompany) {
		this.prdCompany = prdCompany;
	}
	public int getPrdStock() {
		return prdStock;
	}
	public void setPrdStock(int prdStock) {
		this.prdStock = prdStock;
	}
	public Date getPrdDate() {
		return prdDate;
	}
	public void setPrdDate(Date prdDate) {
		this.prdDate = prdDate;
	}
	
	
	
}
