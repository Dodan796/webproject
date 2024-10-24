package com.spring_boot_mybatis.project.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring_boot_mybatis.project.model.ProductVO;
import com.spring_boot_mybatis.project.service.ProductService;

@Controller
public class ProductController {
	//DI
	@Autowired
	ProductService service;
	
	//메뉴 페이지 연결
	@RequestMapping("/")
	public String viewIndex() {
		return "index";
	}
	
	//전체 상품 조회 요청에 대한 처리 메소드
	@RequestMapping("/product/listAllProduct")
	public String listAllProduct(Model model) {
		ArrayList<ProductVO> prdList =  service.listAllProduct(); //컨트롤러에서 service 객체 메소드 호출하고 반환 값 저장
		model.addAttribute("prdList",prdList);
		return "product/productListView";
	}
	
	//상품 등록 폼페이지 요청에 대한 처리 메소드
	@RequestMapping("/product/newProductForm")
	public String newProductForm() {
		return "product/newProductForm";
	}
	
	//상품 등록 기능 요청에 대한 처리 메소드
	//insert 구문을 진행하게 됨 반환 값이 없음 : insert된 상품을 포함한 전체 상품 조회 요청으로 포워딩 진행
	//전달된 파라미터 command 객체를 이용해서 일괄 처리(VO 객체 변수 필드에 한번에 저장)
	//ProductVO 클래스 필드명과 입력 form의 input태그의 name이 동일해야 함 
	@RequestMapping("/product/insertProduct")
	public String insertProduct(ProductVO prd) {
		//컨트롤러 -> 서비스 메소드 호출(컨트롤러가 받은 객체 data 그대로 전달하게 됨)
		service.insertProduct(prd); //반환 값은 없으므로 저장 필요 없음. 단, 해당 메소드가 정상 종료되면 포커스가 현재 위치로 반환되면서  해당 코드 실행이 완료 됨
		//insert된 상품 포함 전체 상품 조회 url을 요청(redirect 사용)
		return "redirect:/product/listAllProduct";
		//return "redirect:./listAllProduct"; //이렇게 해도 됨
		//return "product/productListView"; //단순하게 view 페이지만 요청하므로 model 객체가 전송되지 않음. 데이터 출력 안됨
	}
	
	//상품 상세정보 페이지 요청은 전체 상품 조회 list에서 상품번호 클릭하면 요청이되게끔 구성(url 마지막에 상품번호가 추가되어서 전달됨)
	@RequestMapping("/product/detailViewProduct/{prdNo}")
	public String detailViewProduct(@PathVariable String prdNo , Model model) {
		//상품번호 전달하고 해당 상품 정보 받아오기
	    ProductVO prd= service.detailViewProduct(prdNo);
	    model.addAttribute("prd",prd);
	    
	    return "product/productDetailView"; //상품 상세정보 뷰페이지
	}
	
	//상품 정보 수정화면으로 이동(수정하기 위해 먼저 기존 입력한 상품 상세정보를 화면(입력폼)에 출력
	@RequestMapping("/product/updateForm/{prdNo}")
	public String updateProductForm(@PathVariable String prdNo, Model model) {
		//상세보기 기능과 동일하게 진행, 뷰 페이지만 변경
	    ProductVO prd= service.detailViewProduct(prdNo);
	    model.addAttribute("prd",prd);
	    
	    return "product/updateProductForm"; //상품 정보 수정 뷰페이지		
	}
	
	//상품정보 수정 처리 요청에 대한 처리 메소드
	@RequestMapping("/product/updateProduct")
	public String updateProduct(ProductVO prd) {
		service.updateProduct(prd); //반환값 없음, 특별히 표현할 내용 없으므로 제대로 수정되었는지 전체 상품 조회 재 요청
		return "redirect:./listAllProduct"; //전체 상품 조회 페이지로 redirect
	}
	
	//상품정보 삭제
	@RequestMapping("/product/deleteProduct/{prdNo}")
	public String deleteProduct(@PathVariable String prdNo) {
		service.deleteProduct(prdNo);
		return "redirect:/product/listAllProduct";
	}
	
	//상품 번호 중복 확인1 : ajax 기능을 이용한 요청일 경우 @ResponseBody를 사용해서 data를 직접 클라이언트에게 전달
	//ajax post 방식
	@ResponseBody
	@RequestMapping("/product/prdNoCheck")
	public String prdNoCheck(@RequestParam("prdNo") String prdNo) {
		//상품번호 사용 가능 여부 db 통해서 확인해야 함, 관련 메소드 service, dao에 추가 해야 함
		//Controller <-> Service <-> DAO
		
		String prdNo_result = service.prdNoCheck(prdNo);
		System.out.println(prdNo_result);
		String result="available"; //기본값 : 상품번호 사용 가능
		
		if(prdNo_result!=null)//상품번호가 테이블에 존재하면
			result="no_available";
		
		return result;
	}
	
	//상품 번호 중복 확인2 : ajax 기능을 이용한 요청일 경우 @ResponseBody를 사용해서 data를 직접 클라이언트에게 전달
	//ajax get 방식
	@ResponseBody
	@RequestMapping("/product/prdNoCheck1/{prdNo}")
	public String prdNoCheck1(@PathVariable String prdNo) {
		//상품번호 사용 가능 여부 db 통해서 확인해야 함, 관련 메소드 service, dao에 추가 해야 함
		//Controller <-> Service <-> DAO
		
		String prdNo_result = service.prdNoCheck(prdNo);
		System.out.println(prdNo_result);
		String result="available"; //기본값 : 상품번호 사용 가능
		
		if(prdNo_result!=null)//상품번호가 테이블에 존재하면
			result="no_available";
		
		return result;
	}	

	//상품 번호 중복 확인3 : ajax 기능을 이용한 요청일 경우 @ResponseBody를 사용해서 data를 직접 클라이언트에게 전달
	//fetch() :  get 방식
	@ResponseBody
	@RequestMapping("/product/prdNoCheck2/{prdNo}")
	public String prdNoCheck2(@PathVariable String prdNo) {
		//상품번호 사용 가능 여부 db 통해서 확인해야 함, 관련 메소드 service, dao에 추가 해야 함
		//Controller <-> Service <-> DAO
		
		String prdNo_result = service.prdNoCheck(prdNo);
		System.out.println(prdNo_result);
		String result="available"; //기본값 : 상품번호 사용 가능
		
		if(prdNo_result!=null)//상품번호가 테이블에 존재하면
			result="no_available";
		
		return result;
	}	
	
	//상품 번호 중복 확인4 :  비동기 통신 기능을 이용한 요청일 경우 @ResponseBody를 사용해서 data를 직접 클라이언트에게 전달
	//fetch() :  post방식인 경우에는 json 형식의 통신데이터 생성해서 통신 header에 포함시켜서 전송
	//fetch 함수는 실제 전달되는 파라미터 body키에 저장해서 전송 : 컨트롤러에서는 @RequestBody를 통해서 추출, @RequestParam 에러남
	@ResponseBody
	@RequestMapping("/product/prdNoCheck3")
	public String prdNoCheck3(@RequestBody String prdNo) {
		//상품번호 사용 가능 여부 db 통해서 확인해야 함, 관련 메소드 service, dao에 추가 해야 함
		//Controller <-> Service <-> DAO
		
		String prdNo_result = service.prdNoCheck(prdNo);
		System.out.println(prdNo_result);
		String result="available"; //기본값 : 상품번호 사용 가능
		
		if(prdNo_result!=null)//상품번호가 테이블에 존재하면
			result="no_available";
		
		return result;
	}	
	
	//상품 번호 중복 확인5 : ajax 기능을 이용한 요청일 경우 @ResponseBody를 사용해서 data를 직접 클라이언트에게 전달
	//axios() :  get 방식
	@ResponseBody
	@RequestMapping("/product/prdNoCheck4/{prdNo}")
	public String prdNoCheck4(@PathVariable String prdNo) {
		//상품번호 사용 가능 여부 db 통해서 확인해야 함, 관련 메소드 service, dao에 추가 해야 함
		//Controller <-> Service <-> DAO
		
		String prdNo_result = service.prdNoCheck(prdNo);
		System.out.println(prdNo_result);
		String result="available"; //기본값 : 상품번호 사용 가능
		
		if(prdNo_result!=null)//상품번호가 테이블에 존재하면
			result="no_available";
		
		return result;
	}
	
	//상품 번호 중복 확인6 : ajax 기능을 이용한 요청일 경우 @ResponseBody를 사용해서 data를 직접 클라이언트에게 전달
	//axios() :  post 방식
	@ResponseBody
	@RequestMapping("/product/prdNoCheck5")
	public String prdNoCheck5(@RequestBody HashMap<String,String> map) {
		//상품번호 사용 가능 여부 db 통해서 확인해야 함, 관련 메소드 service, dao에 추가 해야 함
		//Controller <-> Service <-> DAO
		
		String prdNo_result = service.prdNoCheck(map.get("prdNo"));
		System.out.println(prdNo_result);
		String result="available"; //기본값 : 상품번호 사용 가능
		
		if(prdNo_result!=null)//상품번호가 테이블에 존재하면
			result="no_available";
		
		return result;
	}
	
	//상품 검색 -----------------------------------------------
	//상품 검색 폼 열기 요청 처리
	@RequestMapping("/product/productSearchForm1")
	public String viewProductSearchForm1() {
		return "product/productSearchForm1";
	}
	
	//상품검색 : 방법 1 (ajax, @ResponseBody 사용)
	//ArrayList<ProductVO> 반환 (모델객체에 추가해서 반환해야 함)-> ajax에서는 배열처럼 사용할 수 있음
	@ResponseBody
	@RequestMapping("/product/productSearch1")
	public ArrayList<ProductVO> productSearch1(@RequestParam HashMap<String,Object> param, Model model){
		ArrayList<ProductVO> prdList = service.productSearch(param);
		model.addAttribute("prdList", prdList);
		
		return prdList;
	}
	
	//상품 검색 폼2 열기 요청 처리
	@RequestMapping("/product/productSearchForm2")
	public String viewProductSearchForm2() {
		return "product/productSearchForm2";
	}
	
	//상품검색 : 방법 2 (ajax 사용 요청, 반환도 ajax로 반환)
	//view 페이지 반환(서버가 jsp 코드 해석하고 최종 html로 바꾼 태그 데이터 반환)
	//@ResponseBody 사용하지 않기 때문에 서버에는 view페이지를 찾게됨
	@RequestMapping("/product/productSearch2")
	public String productSearch2(@RequestParam HashMap<String,Object> param, Model model){
		
		ArrayList<ProductVO> prdList = service.productSearch(param);
		model.addAttribute("prdList", prdList); //모델에 포함해서 뷰로 전달됨
		
		return "product/productSearchResultView";//뷰페이지가  ajax로 반환
	}	
}



















