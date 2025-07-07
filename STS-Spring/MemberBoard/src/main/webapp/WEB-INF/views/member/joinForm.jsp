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
    <c:if test="${joinMember != null}">
      <script>
        alert("회원가입에 실패했습니다. \n다시 가입해주십시오");
      </script>
    </c:if>
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
        <h1>joinForm.jsp 회원가입 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <!-- 회원가입 양식 -->
        <!-- <h2 style="text-align: center">테스트 ${param.mid}</h2>z -->
        <form
          onsubmit="return joinFormCheck(this)"
          id="memberjoin"
          action="/join"
          method="post"
        >
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
                  <input
                    id="mid"
                    name="mid"
                    type="text"
                    onkeyup="resetMidCheck()"
                    placeholder="아이디 입력"
                    value="${joinMember.mid }"
                  />
                  <button type="button" onclick="idCheck()">중복확인</button>
                  <!-- form태그 안의 button은 type 미 정의시 기본값 submit -->
                  <div id="idCheckMsg"></div>
                </td>
              </tr>
              <tr>
                <td>비밀번호</td>
                <td>
                  <input
                    name="mpw"
                    type="text"
                    onkeyup="resetMpwCheck()"
                    placeholder="비밀번호 입력"
                    value="${joinMember.mpw }"
                  />
                  <div id="pwCheckMsg"></div>
                </td>
              </tr>
              <tr>
                <td>이름</td>
                <td>
                  <input
                    name="mname"
                    type="text"
                    onkeyup="resetMnameCheck()"
                    placeholder="이름 입력"
                    value="${joinMember.mname }"
                  />
                  <div id="nameCheckMsg"></div>
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
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script type="text/javascript">
      /* 아이디 중복 확인 요청 */
      const inputIdEl = document.getElementById("mid");
      const idMsgEl = document.getElementById("idCheckMsg");
      const pwMsgEl = document.getElementById("pwCheckMsg");
      const nameMsgEl = document.getElementById("nameCheckMsg");

      // 중복 확인 결과 저장용 변수
      let isDuplicated = true;

      let textVal = "메시지";
      function idCheck() {
        const idVal = inputIdEl.value.trim(); // 문자의 앞 뒤 공백을 제거 ex) "  AB CD  " >> "AB CD"
        if (idVal.length <= 0) {
          console.log("ID 미입력");
          idMsgEl.innerHTML = "아이디를 입력해주십시오";
          // innerHTML : 태그를 붙여서 메시지 입력시 태그 속성 적용
          // innerText : 태그를 붙여도 태그 속성 적용 x 그대로 텍스트로 출력됨
          idMsgEl.style.color = "blue";
          inputIdEl.value = "";
          return;
        }
        // jquery의 ajax 사용하여 아이디 중복확인 요청
        $.ajax({
          url: "/midCheck", //요청주소
          type: "get", //요청방식
          data: { userid: inputIdEl.value }, //요청시 보내는 파라미터 { key:value }
          // dataType: "" << 응답을 받아올 때 사용
          success: function (response) {
            if (response == "y") {
              // 사용가능
              idMsgEl.innerHTML = "사용가능한 아이디입니다";
              idMsgEl.style.color = "green";
              isDuplicated = false;
            } else {
              // 사용불가
              idMsgEl.innerHTML = "사용중인 아이디입니다";
              idMsgEl.style.color = "red";
              isDuplicated = true;
            }
          },
          // error: function(){} << 생략 가능
        });
      }

      function joinFormCheck(formEl) {
        /* input 태그들 선택 */
        const input_id = formEl.mid; // 아이디 input
        const input_pw = formEl.mpw; // 비밀번호 input
        const input_name = formEl.mname; // 이름 input
        let isChecked = true; // submit 가능여부

        if (input_id.value.trim() <= 0) {
          idMsgEl.innerHTML = "아이디를 입력해주십시오";
          idMsgEl.style.color = "blue";
          isChecked = false;
        } else if (isDuplicated) {
          idMsgEl.innerHTML = "";
          // 아이디 중복확인이 필요한 경우
          idMsgEl.innerHTML = "중복확인을 진행해주십시오";
          idMsgEl.style.color = "blue";
          isChecked = false;
        }

        if (input_pw.value.trim() <= 0) {
          pwMsgEl.innerHTML = "비밀번호를 입력해주십시오";
          pwMsgEl.style.color = "blue";
          isChecked = false;
        } else {
          pwMsgEl.innerHTML = "";
        }

        if (input_name.value.trim() <= 0) {
          nameMsgEl.innerHTML = "이름을 입력해주십시오";
          nameMsgEl.style.color = "blue";
          isChecked = false;
        } else {
          nameMsgEl.innerHTML = "";
        }

        return isChecked;
      }

      function resetMidCheck() {
        idMsgEl.innerHTML = ""; // 중복확인 메시지 초기화
        isDuplicated = true; // 중복확인 기록 초기화
      }

      function resetMpwCheck() {
        pwMsgEl.innerHTML = "";
      }

      function resetMnameCheck() {
        nameMsgEl.innerHTML = "";
      }
    </script>
  </body>
</html>
