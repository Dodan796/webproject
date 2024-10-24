package com.spring_boot_mybatis.project.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring_boot_mybatis.project.dao.IProductDAO;
import com.spring_boot_mybatis.project.model.ProductVO;

//컨트롤러가 service의 메소드를 호출(호출되면서 넘어오는 데이터를 그대로 포함시켜서) -> service가 dao 동일 기능 메소드를 호출
@Service
public class ProductService implements IProductService{

	//MyBatis 사용하는 경우 dao 의존성 주입 주의
	@Autowired
	@Qualifier("IProductDAO")
	IProductDAO dao;
	
	@Override
	public ArrayList<ProductVO> listAllProduct() {
		// 전체 상품 조회 기능하는  dao메소드 호출(단, dao 객체 생성 또는 DI )
		return dao.listAllProduct();
	}

	@Override
	public void insertProduct(ProductVO prdVO) {
		// 1개 상품 정보 저장을 위한 dao 메소드 호출 : controller에서 전달된 vo 객체 변수 dao로 전달
		dao.insertProduct(prdVO);		
	}

	@Override
	public void updateProduct(ProductVO prdVO) {
		// 상품정보 수정
		dao.updateProduct(prdVO);
		
	}

	@Override
	public void deleteProduct(String prdNo) {
		// 상품 정보 삭제
		dao.deleteProduct(prdNo);		
	}

	@Override
	public ProductVO detailViewProduct(String prdNo) {
		// 상품 상세정보 페이지
		return dao.detailViewProduct(prdNo);
	}

	@Override
	public String prdNoCheck(String prdNo) {
		// DAO 관련 메소드 호출 후 반환 결과 컨트롤러에게 반환
		return dao.prdNoCheck(prdNo);
	}

	@Override
	public ArrayList<ProductVO> productSearch(HashMap<String, Object> map) {
		// dao 관련 메소드 호출하고 반환값 컨트롤러에 리턴
		return dao.productSearch(map);
	}
	
	
}
