<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Home</title>
    <link rel="stylesheet" href="/resources/css/main.css" />
    <link rel="stylesheet" href="resources/css/form.css" />
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
        <h1>loginForm.jsp 로그인 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <!-- 회원가입 양식 -->
        <form
          onsubmit="return loginFormCheck(this)"
          id="memberlogin"
          action="/login"
          method="post"
        >
          <table>
            <thead>
              <tr>
                <th>로그인</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <input
                    name="mid"
                    type="text"
                    placeholder="아이디 입력"
                    value="${joinMember.mid }"
                  />
                  <div id="idCheckMsg" name="idCheckMsg"></div>
                </td>
              </tr>
              <tr>
                <td>
                  <input
                    name="mpw"
                    type="text"
                    placeholder="비밀번호 입력"
                    value="${joinMember.mpw }"
                  />
                  <div id="pwCheckMsg" name="pwCheckMsg"></div>
                </td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <th><input type="submit" value="로그인" /></th>
              </tr>
            </tfoot>
          </table>
        </form>
      </div>
    </div>
    <script type="text/javascript">
      function loginFormCheck(loginEl) {
        const idMsg = document.getElementById("idCheckMsg");
        const pwMsg = document.getElementById("pwCheckMsg");
        const input_id = loginEl.mid;
        const input_pw = loginEl.mpw;

        let canSubmit = true;

        if (input_id.value.trim() <= 0) {
          console.log("ID 미입력");
          idMsg.innerHTML = "아이디를 입력해주십시오";
          idMsg.style.color = "blue";
          canSubmit = false;
        }
        if (input_pw.value.trim() <= 0) {
          console.log("PW 미입력");
          pwMsg.innerHTML = "비밀번호를 입력해주십시오";
          pwMsg.style.color = "blue";
          canSubmit = false;
        }

        return canSubmit;
      }
    </script>
  </body>
</html>
