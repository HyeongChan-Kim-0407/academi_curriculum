<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>loginForm</title>
    <link rel="stylesheet" href="resources/css/main.css" />
    <link rel="stylesheet" href="resources/css/form.css" />
    <style>
      form > table {
        width: 100%;
        max-width: 300px;
        padding-top: 10px;
        margin: 0 auto;
      }
      tbody > tr > td {
        padding-right: 10px;
      }
      tfoot > tr > td {
        padding-top: 10px;
      }
      /* #pageContents > form input[type="submit"] {
        width: 100%;
        padding: 10px;
      } */
      #pageContents > form input {
        width: 100%;
      }
    </style>
    <c:if test="${resultMsg != null}">
      <script>
        alert("${resultMsg}");
      </script>
    </c:if>
  </head>
  <body>
    <!-- 메뉴, 설명, 컨텐츠를 감싸는 컨테이너로 div 생성 -->
    <div class="container">
      <div id="pageMenu">
        <!-- 프로젝트이름, 메뉴 -->
        <div><a href="/">Member</a></div>
        <ul>
          <li><a href="login">로그인</a></li>
          <!-- http://localhost:8080/controller/login -->
          <li><a href="join">회원가입</a></li>
          <!-- http://localhost:8080/controller/join -->
        </ul>
      </div>

      <div id="pageTitle">
        <!-- 페이지 파일이름, 페이지 설명 -->
        <h1>loginForm.jsp 메인 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <form id="memberForm" action="login" method="post">
          <table>
            <tr>
              <td>
                <input type="text" name="mid" placeholder="아이디" />
                <div id="checkmid"></div>
              </td>
            </tr>
            <tr>
              <td>
                <input type="text" name="mpw" placeholder="비밀번호" />
                <div id="checkmpw"></div>
              </td>
            </tr>
            <tfoot>
              <tr>
                <th><input type="submit" value="로그인" /></th>
              </tr>
            </tfoot>
          </table>
        </form>
      </div>
    </div>
    <script
      type="text/javascript"
      src="resources/JavaScript/memberForm.js"
    ></script>
  </body>
</html>
