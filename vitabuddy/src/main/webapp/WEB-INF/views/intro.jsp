<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<c:import url="/WEB-INF/views/layout/head.jsp" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/intro.css'/>">
<script src="<c:url value='/js/login.js'/>"></script>
</head>
<body>
<div id="wrap">
    <!-- 인트로 이미지 -->
    <div class="introTitle">
        <img src="<c:url value='/image/mainImage.png'/>" id="logoImg">
    </div>

    <section>
        <h1 class="main-title">
            WELCOME TO YOUR<br>HEALTH PARTNER VITABUDDY
        </h1>

        <!-- 로그인 폼 -->
        <div class="loginForm">
            <form>
                <label for="id">아이디</label>
                <br>
                <input type="text" id="id" name="id" class="formTxt" placeholder="아이디" required>
                <br>
                <label for="pwd">비밀번호</label>
                <br>
                <input type="password" id="pwd" name="pwd" class="formTxt" placeholder="비밀번호" autocomplete="off" required>
                <br>
                <button type="submit" id="loginSubmit" class="btn btnFilled">로그인</button>
            </form>
        </div>

        <!-- 네이버 로그인 버튼 -->
        <div class="social-login">
            <p>소셜 계정으로 로그인</p>
            <a href="<c:url value='/member/socialLogin/naver'/>">
                <img src="<c:url value='/image/socialLoginbtn/naverLoginbtn.png'/>" alt="네이버 로그인" id="naverLoginBtn">
            </a>
        </div>
    </section>

    <br>
    <section>
        <!-- 회원가입 -->
        <div>
            <p>아직 회원이 아니신가요?</p>
            <a href="<c:url value='/member/register'/>" class="btn btnFilled">
                회원가입
            </a>
        </div>

        <!-- 로그인 하지 않은 상태로 메인 이동 -->
        <div>
            <a href="<c:url value='/'/>">
                둘러보기
            </a>
        </div>
    </section>
</div>
</body>
</html>
