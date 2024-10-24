<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 폼</title>
</head>
<body>
	<h1>파일 업로드</h1>
	<form id="fileUplodeForm" method="post" action="<c:url value='/fileUpload'/>" enctype="multipart/form-data">
		파일 :<input type="file" id="uploadFile" name="uploadFile"><br><br>
		<input type="submit" value="업로드">	
	</form>
	
	<hr>
	<H1>여러 파일 업로드</H1>
	<form id="fileUplodeFormMulti" method="post" action="<c:url value='/fileUploadMultiple'/>" enctype="multipart/form-data">
		파일 :<input type="file" id="uploadFile" name="uploadFileMulti" multiple="multiple"><br><br>
		<input type="submit" value="업로드">	
	</form>	
	
	<hr>
	<h1>파일 업로드(이름 변경 없이 업로드)</h1>
	<form id="fileOriginalNameUplodeForm" method="post" action="<c:url value='/fileOriginalNameUplode'/>" enctype="multipart/form-data">
		파일 :<input type="file" id="uploadFileOrigin" name="uploadFileOrigin"><br><br>
		<input type="submit" value="업로드">	
	</form>	

</body>
</html>