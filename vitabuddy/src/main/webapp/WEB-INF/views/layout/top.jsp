<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="<c:url value='/js/login.js'/>" defer></script>

<header>
    <div id="headerBox">
        <div id="logoBox">
            <a href="<c:url value='/'/>"><img src="<c:url value='/image/logo.png'/>" id="logoImg" width="240" height="80"></a>
        </div>
        <div id="topMenu">
            <!-- 쿠키에서 userRole과 userId 값을 가져옴 -->
            <c:set var="userRole" value="${cookie.userRole != null ? cookie.userRole.value : null}" />
            <c:set var="userId" value="${cookie.userId != null ? cookie.userId.value : null}" />

            <!-- 비회원 (userRole이 null일 때) -->
            <c:if test="${userRole == null}">
                <a href="<c:url value='/intro'/>">로그인</a>
                <a href="<c:url value='/member/register'/>">회원가입</a>
            </c:if>

            <!-- 일반 회원 -->
           <c:if test="${userRole == 'ROLE_USER'}">
               <p>${userId}</p>
               <a href="/logout" id="logoutButton">로그아웃</a>
               <a href="<c:url value='/supplement/wishList'/>">
                    <i class="fas fa-solid fa-heart"></i>
               </a>
               <a href="<c:url value='/supplement/cartList'/>">
                    <i class="fa-solid fa-cart-shopping"></i>
               </a>
               <a href="<c:url value='/member/myPage'/>">
                    <i class="fa-solid fa-user"></i>
               </a>
           </c:if>

            <!-- 관리자 -->
            <c:if test="${userRole == 'ROLE_ADMIN'}">
                <p>User ID: ${userId}</p>
                <a href="<c:url value='/logout'/>">로그아웃</a>
                <a href="<c:url value='/admin/dashboard'/>">관리자 대시보드</a>
            </c:if>
        </div>
    </div>
    <hr>
</header>
