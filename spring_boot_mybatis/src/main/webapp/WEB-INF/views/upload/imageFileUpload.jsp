<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>이미지 파일 업로드</title>
	<script src="<c:url value='/js/jquery-3.7.1.min.js'/>"></script>
	<script src="<c:url value='/js/imageFileUpload.js'/>"></script>
</head>
<body>
	<!-- 파일 업로드  -->
	<h1>이미지 파일 업로드</h1>
	<form id="imageFileForm">
		파일 :<input type="file" id="uploadFile" name="uploadFile"><br><br>
		<input type="submit" value="업로드">	
	</form><br><br>
	
	<!-- 업로드한 이미지 출력 -->
	<h1>업로드한 이미지</h1>
	<div id="imageBox"></div>
	
	<a href="<c:url value='/'/>">index 페이지로 이동</a>
</body>
</html>