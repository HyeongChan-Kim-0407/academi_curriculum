<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Home</title>
    <link rel="stylesheet" href="resources/css/main.css" />
    <link rel="stylesheet" href="resources/css/memberInfo.css" />
    <!-- GET http://localhost:8080/controller/style.css net::ERR_ABORTED 404 (Not Found) -->
    <style>
      #pageContents > p {
        text-align: center;
      }
      #pageContents > table {
        margin: 0 auto;
      }
      button {
        width: 100%;
        max-width: 200px;
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
          <c:if test="${sessionScope.loginId == null }">
            <li><a href="login">로그인</a></li>
            <!-- http://localhost:8080/controller/login -->
            <li><a href="join">회원가입</a></li>
            <!-- http://localhost:8080/controller/join -->
          </c:if>
          <c:if test="${sessionScope.loginId != null }">
            <li><a href="memberInfo">회원정보</a></li>
            <li><a href="logout">로그아웃</a></li>
          </c:if>
        </ul>
      </div>

      <div id="pageTitle">
        <!-- 페이지 파일이름, 페이지 설명 -->
        <h1>memberInfo.jsp 회원정보 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <h2 style="text-align: center; margin-top: 50px">회원 정보</h2>
        <table id="memberInfo">
          <thead>
            <tr>
              <th>아이디</th>
              <th>비밀번호</th>
              <th>이름</th>
              <th>생년월일</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>${mInfo.mid}</td>
              <td>${mInfo.mpw}</td>
              <td>${mInfo.mname}</td>
              <td>${mInfo.mbirth}</td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <th colspan="4" style="padding-top: 15px">
                <button type="button" onclick="goModifyForm()">정보수정</button>
                <button type="button" onclick="goHome()">메인으로</button>
              </th>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
    <script type="text/javascript">
      function goHome() {
        location.href = "/"; // 주소창의 주소를 /로 요청
      }
      function goModifyForm() {
        location.href = "/modify";
        // http://localhost:8080/modify - get
      }
    </script>
  </body>
</html>
