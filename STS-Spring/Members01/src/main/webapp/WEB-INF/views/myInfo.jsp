<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Home</title>
    <!-- 공통 스타일 CSS -->
    <link rel="stylesheet" href="resources/css/main.css" />
    <!-- 회원 정보 table CSS -->
    <link rel="stylesheet" href="resources/css/memberInfo.css" />

  </head>
  <body>
    <div class="container">
      <!-- pageMenu -->
      <div id="pageMenu">
        <div>
          <a href="/">MEMBERS</a>
        </div>
        <ul>
          <!-- 로그인이 되어 있지 않은 경우 -->
          <c:if test="${sessionScope.loginMid == null }">
            <li><a href="/join">회원가입</a></li>
            <li><a href="login">로그인</a></li>
          </c:if>
          <!-- 로그인이 되어 있는 경우 -->
          <c:if test="${sessionScope.loginMid != null }">
            <li><a href="myInfo">내정보</a></li>
            <li><a href="logout">로그아웃</a></li>
          </c:if>
        </ul>
      </div>

      <!-- pageTitle -->
      <div id="pageTitle">
        <h1>myInfo - 내 정보 페이지</h1>
      </div>

      <!-- pageContents -->
      <div id="pageContents">
        <h2 style="text-align: center; margin-top: 50px">내 정보 확인</h2>
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
              <td>${minfo.mid }</td>
              <td>${minfo.mpw }</td>
              <td>${minfo.mname }</td>
              <td>${minfo.mbirth }</td>
            </tr>
          </tbody>
          <tfoot>
          	<tr>
          		<th colspan="4" style="padding-top:15px;"> 
          		    <button type="button" onclick="goModifyForm()">정보수정</button>
          			<button type="button" onclick="goHome()">메인으로</button>
          		 </th>
          	</tr>
          </tfoot>
        </table>
      </div>
    </div>
    <script type="text/javascript">
    	function goModifyForm(){
    		location.href = "/modify" ;
    		// http://localhost:8080/modify - get
    	}
    
    	function goHome(){
    		location.href = "/";
    	}
    </script>
  </body>
</html>







