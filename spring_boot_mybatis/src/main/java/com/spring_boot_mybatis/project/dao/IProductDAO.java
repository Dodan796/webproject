package com.spring_boot_mybatis.project.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring_boot_mybatis.project.model.ProductVO;

// 아래 인터페이스 구현 클래스는 DBMS와 연결 되어야 하므로, MyBatis Mapper로 구성-> 자동 클래스로 변환되게 할 예정임
public interface IProductDAO {
	//Service의 기능 모두 DAO에 구성되어 있어야 함
	ArrayList<ProductVO> listAllProduct();//전체 상품 조회,where 절 필요 없음
	void insertProduct(ProductVO prdVO);//1개 상품 정보 등록
	void updateProduct(ProductVO prdVO);//1개 상품 정보 수정(특정 컬럼 수정 접근이 아닌 전체 컬럼 수정 접근방식 사용), 조건절에 필요한 기본키prdNo는 prdVO 객체 안에 포함되어 있음
	void deleteProduct(String prdNo);//1개 상품 정보 삭제, 조건절에 사용할 기본키 data 매개변수로 받음(1개의 특정 레코드만 삭제 가능)
	ProductVO detailViewProduct(String prdNo);//1개 상품 상세 상품 조회, 특정 상품 표현할 prdNo가 있어야 함(where 절 필요)
	String prdNoCheck(String prdNo);//상품번호 중복 확인
	ArrayList<ProductVO> productSearch(HashMap<String,Object> map);//상품검색
}
