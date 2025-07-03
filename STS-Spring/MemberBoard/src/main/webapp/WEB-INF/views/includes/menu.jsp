<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<ul>
  <li><a href="/boardList">게시판</a></li>
  <c:if test="${sessionScope.loginId == null }">
    <li><a href="/login">로그인</a></li>
    <!-- http://localhost:8080/controller/login -->
    <li><a href="/join">회원가입</a></li>
    <!-- http://localhost:8080/controller/join -->
  </c:if>
  <c:if test="${sessionScope.loginId != null }">
    <li><a href="memberInfo">회원정보</a></li>
    <li><a href="logout">로그아웃</a></li>
  </c:if>
</ul>
