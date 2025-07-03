<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>joinForm</title>
    <link rel="stylesheet" href="resources/css/main.css" />
    <link rel="stylesheet" href="resources/css/form.css" />
    <style type="text/css">
      /* 테이블을 대상으로 */
      #pageContents > form > table {
        width: 100%; /* 폭 100% */
        max-width: 600px; /* 최대 폭 600px */
        margin: 0 auto; /* 중앙으로 */
      }

      /* 테이블 tbody의 td를 대상으로 */
      #pageContents > form > table > tbody td {
        padding: 10px 3px;
        font-size: 20px;
      }
      /* 테이블의 th를 대상으로 */
      #pageContents > form > table th {
        font-size: 30px;
      }
      /* form 안에 위치한 <input type="submit"> 을 대상으로 */
      #pageContents > form input[type="submit"] {
        padding: 10px;
        width: 100%;
        font-weight: bold;
        font-size: 25px;
      }
      /* form 안에 위치한 <input type="text"> 을 대상으로 */
      #pageContents > form input[type="text"] {
        border: 0; /* 경계선을 0으로 */
        border-bottom: 1px solid black; /* 아래쪽 경계선 */
        outline: none; /* 태그 선택시 나오는 외곽선 */
        width: 100%;
        font-size: 25px;
      }
      /* form 안에 위치한 <input type="date"> 을 대상으로 */
      #pageContents > form input[type="date"] {
        border: 0;
        border-bottom: 1px solid black;
        width: 100%;
        font-size: inherit; /* inherit: 상위 요소에 적용된 값으로 */
      }
      td > div {
        color: red;
      }
      td > div {
        color: red;
      }
    </style>
  </head>
  <body>
    ㅋ
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
        <h1>joinForm.jsp 메인 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <form id="memberForm" action="join" method="post">
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
                  <div id="checkmid"></div>
                </td>
              </tr>
              <tr>
                <td>비밀번호</td>
                <td>
                  <input type="text" name="mpw" value="${joinMember.mpw }" />
                  <div id="checkmpw"></div>
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

    <script
      type="text/javascript"
      src="resources/JavaScript/memberForm.js"
    ></script>
  </body>
</html>
