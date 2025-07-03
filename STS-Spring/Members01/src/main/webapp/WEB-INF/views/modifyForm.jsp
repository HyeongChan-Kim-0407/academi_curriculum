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
          <li><a href="myInfo">내정보</a></li>
          <li><a href="logout">로그아웃</a></li>
        </ul>
      </div>

      <!-- pageTitle -->
      <div id="pageTitle">
        <h1>modifyForm.jsp - 정보 수정 페이지</h1>
      </div>

      <!-- pageContents -->
      <div id="pageContents">
        <form action="modify" method="post" id="memberForm">
          <table>
            <thead>
              <tr>
                <th colspan="2">회원정보 수정</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>아이디</td>
                <td style="font-weight: bold; font-size: 25px;">
                  ${member.mid}
                </td>
              </tr>
              <tr>
                <td>비밀번호</td>
                <td>
                  <input type="text" name="mpw" value="${member.mpw }" />
                </td>
              </tr>
              <tr>
                <td>이름</td>
                <td>
                  <input
                    type="text"
                    name="mname"
                    value="${member.mname }"
                  />
                </td>
              </tr>
              <tr>
                <td>생년월일</td>
                <td>
                  <input
                    type="date"
                    name="mbirth"
                    value="${member.mbirth }"
                  />
                </td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <th colspan="2">
                	<input style="width:50%; max-width:200px;" type="submit" value="수정하기" />
                	<button type="button" style="font-size: 25px;width:50%;max-width:200px; padding:10px">메인으로</button>
                </th>
              </tr>
            </tfoot>
          </table>
        </form>
      </div>
    </div>
    <script type="text/javascript" src="resources/js/memberForm.js"></script>
  </body>
</html>












