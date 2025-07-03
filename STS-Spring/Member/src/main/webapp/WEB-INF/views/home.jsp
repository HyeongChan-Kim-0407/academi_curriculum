<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Home</title>
    <link rel="stylesheet" href="resources/css/main.css" />
    <!-- GET http://localhost:8080/controller/style.css net::ERR_ABORTED 404 (Not Found) -->
    <style>
      #pageContents > p {
        text-align: center;
      }
    </style>
  </head>
  <body>
    <!-- 메뉴, 설명, 컨텐츠를 감싸는 컨테이너로 div 생성 -->
    <div class="container">
      <div id="pageMenu">
        <!-- 프로젝트이름, 메뉴 -->
        <div><a href="/">Member</a></div>
        <ul>
        <li> <a href="memberList">회원목록</a> </li>
        <c:if test="${sessionScope.loginId == null }">
          <li><a href="login">로그인</a></li>
          <!-- http://localhost:8080/controller/login -->
          <li><a href="join">회원가입</a></li>
          <!-- http://localhost:8080/controller/join -->
          </c:if>
          <c:if test="${sessionScope.loginId != null }">
          <li> <a href="memberInfo">회원정보</a> </li>
          <li> <a href="logout">로그아웃</a> </li>
          </c:if>
        </ul>
      </div>

      <div id="pageTitle">
        <!-- 페이지 파일이름, 페이지 설명 -->
        <h1>home.jsp 메인 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <p>로그인 된 아이디 : ${sessionScope.loginId}</p>
      </div>
    </div>
  </body>
</html>
