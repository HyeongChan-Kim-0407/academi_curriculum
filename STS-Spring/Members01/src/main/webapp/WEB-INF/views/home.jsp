<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html> 
  <head>
    <title>Home</title>
    <!-- 공통 스타일 CSS -->
    <link rel="stylesheet" href="resources/css/main.css" />
    
    <c:if test="${resultMsg != null }">
    <script type="text/javascript">
    	alert('${resultMsg}');
    </script>
    </c:if>
    
  </head>
  <body>
    <div class="container">
      <!-- pageMenu -->
      <div id="pageMenu">
        <div><a href="/">MEMBERS</a></div>
        <ul>
          <li> <a href="memberList">회원목록</a> </li>
          <!-- 로그인이 되어 있지 않은 경우 -->
          <c:if test="${sessionScope.loginMid == null }">
          <li><a href="join">회원가입</a></li>
          <li><a href="login">로그인</a></li>
          </c:if>
          <!-- 로그인이 되어 있는 경우 -->
          <c:if test="${sessionScope.loginMid != null }">
          <li> <a href="myInfo">내정보</a> </li>
          <li> <a href="logout">로그아웃</a> </li>
          </c:if>
        </ul>
      </div>

      <!-- pageTitle -->
      <div id="pageTitle">
        <h1>home.jsp - 메인 페이지</h1>
      </div>

      <!-- pageContents -->
      <div id="pageContents">
        <p>로그인 된 아이디 : ${sessionScope.loginMid }</p>
      </div>
    </div>
  </body>
</html>









