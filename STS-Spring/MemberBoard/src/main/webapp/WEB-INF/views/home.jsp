<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Home</title>
    <link rel="stylesheet" href="/resources/css/main.css" />
    <!-- GET http://localhost:8080/controller/style.css net::ERR_ABORTED 404 (Not Found) -->
    <style>
      #pageContents > p {
        text-align: center;
      }
    </style>
    <c:if test="${msg != null}">
      <script>
        alert("${msg}");
      </script>
    </c:if>
    <script
      src="https://kit.fontawesome.com/af375df1cb.js"
      crossorigin="anonymous"
    ></script>
  </head>
  <body>
    <!-- 메뉴, 설명, 컨텐츠를 감싸는 컨테이너로 div 생성 -->
    <div class="container">
      <div id="pageMenu">
        <!-- 프로젝트이름, 메뉴 -->
        <div><a href="/">MemberBoard</a></div>
        <!-- menu.jsp 호출 -->
        <%@ include file="/WEB-INF/views/includes/menu.jsp" %>
      </div>

      <div id="pageTitle">
        <!-- 페이지 파일이름, 페이지 설명 -->
        <h1>home.jsp 메인 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <p>로그인 한 아이디 : ${sessionScope.loginId }</p>
        <img
          src="resources/boardFileUpload/41c82966-b7f4-4745-ba87-436ef8622f37.jpg"
          alt=""
        />
      </div>
    </div>
  </body>
</html>
