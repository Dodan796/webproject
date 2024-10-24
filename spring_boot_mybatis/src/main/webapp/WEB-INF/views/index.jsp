<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>MyBatis사용 DB 연동</h3>
	<h4>상품 관리 시스템</h4>
	<br>
	<a href="/product/listAllProduct">전체 상품 조회</a><br>
	<a href="<c:url value='/product/listAllProduct'/>">전체 상품 조회</a><br>
	<a href="<c:url value='/product/newProductForm'/>">상품 등록</a><br>
	
	<!-- 이미지 내부 경로 설정 후 작업 : servlet-context.xml에 resources 태그 mapping 속성 값으로 사용 가능 -->
<%-- 	<img src="/mybatis/resources/image/apple.png"><br>
	<img src="<c:url value='/resources/images/apple.png'/>"><br> --%>
	<img src="/image/apple.png"><br>
	<img src="<c:url value='/image/apple.png'/>"><br>

	
	<h3>상품 검색</h3>
	<a href="<c:url value='/product/productSearchForm1'/>">[상품 검색 1]]</a><br>
	<a href="<c:url value='/product/productSearchForm2'/>">[상품 검색 2]]</a><br>
	
	<h3>파일 업로드</h3>
	<a href="<c:url value='/fileUploadForm'/>">파일 업로드</a><br>
	<a href="<c:url value='/fileDownloadList'/>">파일 다운로드</a><br>
	<a href="<c:url value='/imageFileUploadForm'/>">이미지 파일 업로드</a><br>
	<a href="<c:url value='/audioFileUploadForm'/>">audio 파일 업로드</a><br>
</body>
</html>




