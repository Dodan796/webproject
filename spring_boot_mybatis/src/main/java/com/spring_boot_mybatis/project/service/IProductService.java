package com.spring_boot_mybatis.project.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring_boot_mybatis.project.model.ProductVO;

public interface IProductService {
	//Service 메소드는 DAO의 관련 메소드 호출 data 전달
	//현재 프로젝트에서 해당 컨텐츠(상품관리)에 어떤 기능을 추가할 것인지 결정해 놓은 인터페이스
	//프로젝트 규격지정에 사용(꼭 필요한건 아니지만 생성하면 프로젝트 가독성이 높아지고 개발자들간의 소통에 효율적임)
	ArrayList<ProductVO> listAllProduct();//전체 상품 조회,where 절 필요 없음
	void insertProduct(ProductVO prdVO);//1개 상품 정보 등록
	void updateProduct(ProductVO prdVO);//1개 상품 정보 수정(특정 컬럼 수정 접근이 아닌 전체 컬럼 수정 접근방식 사용), 조건절에 필요한 기본키prdNo는 prdVO 객체 안에 포함되어 있음
	void deleteProduct(String prdNo);//1개 상품 정보 삭제, 조건절에 사용할 기본키 data 매개변수로 받음(1개의 특정 레코드만 삭제 가능)
	ProductVO detailViewProduct(String prdNo);//1개 상품 상세 상품 조회, 특정 상품 표현할 prdNo가 있어야 함(where 절 필요)
	String prdNoCheck(String prdNo); //상품번호 문자 전달받아서 사용여부 확인하는 기능(where 절 필요)
	ArrayList<ProductVO> productSearch(HashMap<String, Object> map); //상품검색
}
