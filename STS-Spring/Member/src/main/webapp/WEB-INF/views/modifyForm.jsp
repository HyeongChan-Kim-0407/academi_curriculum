<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>ModifyMember</title>
    <link rel="stylesheet" href="resources/css/main.css" />
    <link rel="stylesheet" href="resources/css/form.css"/>
    <style>
      td > div {
        color: red;
      }
      td > div {
        color: red;
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
          <li><a href="memberList">회원목록</a></li>
          <li><a href="memberInfo">회원정보</a></li>
          <li><a href="logout">로그아웃</a></li>
        </ul>
      </div>

      <div id="pageTitle">
        <!-- 페이지 파일이름, 페이지 설명 -->
        <h1>modifyForm.jsp 메인 페이지</h1>
      </div>

      <div id="pageContents">
        <form id="memberForm" action="modify" method="post">
          <table>
            <thead>
              <tr>
                <th colspan="2">회원정보 수정</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>아이디</td>
                <td>${modifyInfo.mid }</td>
              </tr>
              <tr>
                <td>비밀번호</td>
                <td>
                  <input type=text name="mpw" value="${modifyInfo.mpw }" />
                  <div id="checkmpw"></div>
                </td>
              </tr>
              <tr>
                <td>이름</td>
                <td>
                  <input
                    type="text"
                    name="mname"
                    value="${modifyInfo.mname }"
                  />
                </td>
              </tr>
              <tr>
                <td>생년월일</td>
                <td>
                  <input
                    type="date"
                    name="mbirth"
                    value="${modifyInfo.mbirth }"
                  />
                </td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <th colspan="2">
                  <input type="submit" value="회원정보 수정" />
                </th>
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
