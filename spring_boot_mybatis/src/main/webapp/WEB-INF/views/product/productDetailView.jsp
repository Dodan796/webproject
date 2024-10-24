<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 정보 조회</title>
</head>
<body>
	<h3>상품 상세 정보</h3>
	<table border="1" width="300">
		<tr><td>상품번호</td><td>${prd.prdNo}</td></tr>
		<tr><td>상품명</td><td>${prd.prdName}</td></tr>
		<tr><td>가격</td><td>${prd.prdPrice}</td></tr>
		<tr><td>제조회사</td><td>${prd.prdCompany}</td></tr>
		<tr><td>상품재고</td><td>${prd.prdStock}</td></tr>
		<tr><td>상품제조일</td><td><fmt:formatDate value="${prd.prdDate}" pattern="YYYY-MM-dd"/></td></tr>	
	</table>
	
	<!-- 상품 수정 연결 - 수정 폼 페이지로 연결 (수정할 상품 번호(상세보기 하고 있는 상품 번호) 전달 -->
	<a href="<c:url value='/product/updateForm/${prd.prdNo}'/>">[상품 정보 수정]</a><br>
	<!-- 상품 삭제 연결, 삭제할것인지 여부 반드시 확인해야함 - 확인 위한 js 함수 호출-->
	<a href="javascript:deleteCheck();">[상품 정보 삭제]</a><br>
	<!-- 삭제 확인 메시지 출력 후 삭제한다고 하면 상품번호 포함 url 요청 -->
	<script>
		function deleteCheck(){
			let answer = confirm("삭제하시겠습니까?");
			if(answer) {
				location.href="/product/deleteProduct/${prd.prdNo}"
			}
		}		
	</script>
	
	<a href="<c:url value='/'/>">[홈으로 이동]</a>
	
	
</body>
</html>



