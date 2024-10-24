<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 다운로드 목록</title>
</head>
<body>
	<h1>다운로드 가능한 파일 목록</h1><!-- 현재 페이지 요청할 때 파일 목록이 서버로부터 전달되어야 함 -->
	<c:forEach items="${filelist}" var="file">
		<a href="<c:url value='/fileDownload/${file }'/>">${file } 파일 다운로드</a><br>
	</c:forEach>
</body>
</html>