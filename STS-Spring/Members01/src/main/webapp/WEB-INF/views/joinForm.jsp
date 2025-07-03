<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>joinForm</title>
    <!-- 공통 스타일 CSS -->
    <link rel="stylesheet" href="resources/css/main.css" />
    <!-- form 스타일 CSS -->
    <link rel="stylesheet" href="resources/css/form.css" />
  </head>
  <body>
    <div class="container">
      <!-- pageMenu -->
      <div id="pageMenu">
        <div>
          <a href="/">MEMBERS</a>
        </div>
        <ul>
          <li><a href="join">회원가입</a></li>
          <li><a href="login">로그인</a></li>
        </ul>
      </div>

      <!-- pageTitle -->
      <div id="pageTitle">
        <h1>joinForm.jsp - 회원가입 페이지</h1>
      </div>

      <!-- pageContents -->
      <div id="pageContents">
        <form action="join" method="post" id="memberForm">
          <table>
            <thead>
              <tr>
                <th colspan="2">회원가입</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>아이디</td>
                <td>
                  <input type="text" name="mid" value="${joinMember.mid }" />
                  <div id="idMsg"></div>
                </td>
              </tr>
              <tr>
                <td>비밀번호</td>
                <td>
                  <input type="text" name="mpw" value="${joinMember.mpw }" />
                  <div></div>
                </td>
              </tr>
              <tr>
                <td>이름</td>
                <td>
                  <input
                    type="text"
                    name="mname"
                    value="${joinMember.mname }"
                  />
                </td>
              </tr>
              <tr>
                <td>생년월일</td>
                <td>
                  <input
                    type="date"
                    name="mbirth"
                    value="${joinMember.mbirth }"
                  />
                </td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <th colspan="2"><input type="submit" value="회원가입" /></th>
              </tr>
            </tfoot>
          </table>
        </form>
      </div>
    </div>
    <script type="text/javascript" src="resources/js/memberForm.js"></script>
  </body>
</html>












