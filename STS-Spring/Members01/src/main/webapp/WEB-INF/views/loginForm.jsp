<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>loginForm</title>
    <!-- 공통 스타일 CSS -->
    <link rel="stylesheet" href="resources/css/main.css" />
    <!-- form 스타일 CSS -->
    <link rel="stylesheet" href="resources/css/form.css" />
    
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
        <div>
          <!-- http://localhost:8080 -->
          <a href="/">MEMBERS</a>
        </div>
        <ul>
          <!-- http://localhost:8080/controller/join -->
          <li><a href="join">회원가입</a></li>
          <!-- http://localhost:8080/controller/login -->
          <li><a href="login">로그인</a></li>
        </ul>
      </div>

      <!-- pageTitle -->
      <div id="pageTitle">
        <h1>loginForm.jsp - 로그인 페이지</h1>
      </div>

      <!-- pageContents -->
      <div id="pageContents">
        <!-- 아이디, 비밀번호 전송 form -->
        <form action="login" method="post" id="memberForm">
          <table>
            <thead>
              <tr>
                <th colspan="2">로그인</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>아이디</td>
                <td>
                  <input type="text" name="mid" />
                  <div id="idMsg"></div>
                </td>
              </tr>
              <tr>
                <td>비밀번호</td>
                <td>
                  <input type="text" name="mpw" />
                  <div></div>
                </td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <th colspan="2"><input type="submit" value="로그인" /></th>
              </tr>
            </tfoot>
          </table>
        </form>
      </div>
    </div>
    <script type="text/javascript" src="resources/js/memberForm.js"></script>
  </body>
</html>













