<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여러 파일 업로드 결과</title>
</head>
<body>
	<h3>여러 개의 파일 업로드 결과</h3>
	다음의 파일을 전송하였습니다<br>
	C:\springBootWorkspace\upload 폴더에서 확인하세요 <br><br?>
	
	<c:forEach items="${originalFileNameList}" var="file">
		${file } <br>
	</c:forEach>
	
	<br><br><br>
	
	<a href="<c:url value='/'/>">메일 화면으로 이동</a>
</body>
</html>