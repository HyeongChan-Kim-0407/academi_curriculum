<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Home</title>
    <link rel="stylesheet" href="/resources/css/main.css" />
    <!-- GET http://localhost:8080/controller/style.css net::ERR_ABORTED 404 (Not Found) -->
    <style>
      #boardTable {
        text-align: center;
        margin: 0 auto;
      }
    </style>
    <c:if test="${msg != null}">
      <script>
        alert("${msg}");
      </script>
    </c:if>
    <script
      src="https://kit.fontawesome.com/af375df1cb.js"
      crossorigin="anonymous"
    ></script>
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
        <h1>memberInfo.jsp 회원정보 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <div id="btnBox" style="text-align: center">
          <button onclick="contentView('member')">내 정보 보기</button>
          <button onclick="contentView('board')">작성 글 보기</button>
        </div>
        <div id="contentBox"></div>
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
      function contentView(contentType) {
        console.log("contentType : " + contentType);
        // 선택된 정보를 출력할 요소 선택
        const contentBoxEl = document.getElementById("contentBox");
        contentBoxEl.innerHTML = "";
        if (contentType == "member") {
          let contentEl = "";
          //회원정보 조회
          let member_Json = getInfoByType("/getMemberInfo");
          console.log(member_Json);
          contentEl = `<table style="margin: 0 auto;">
            <tr>
              <th>아이디</th>
              <td>\${member_Json.mid}</td>
            </tr>
            <tr>
              <th>비밀번호</th>
              <td>\${member_Json.mpw}</td>
            </tr>
            <tr>
              <th>이름</th>
              <td>\${member_Json.mname}</td>
            </tr>
            <tr>
              <th>가입일</th>
              <td>\${member_Json.mjoindate}</td>
            </tr>
          </table>`;
          contentBoxEl.innerHTML = contentEl;
        } else if (contentType == "board") {
          //createElement 활용
          //작성글 조회
          let boardList_Json = getInfoByType("/getMemberBoardList");
          let tableEl = document.createElement("table");
          // tableEl.setAttribute("속성명", "속성값");
          tableEl.setAttribute("id", "boardTable");
          let theadEl = document.createElement("thead");
          theadEl.innerHTML = `<tr>
                              <th>글번호</th>
                              <th>제목</th>
                              <th>작성자</th>
                              <th>조회수</th>
                              <th>작성일</th>
                            </tr>`;
          tableEl.appendChild(theadEl);
          let tbodyEl = document.createElement("tbody");
          for (board of boardList_Json) {
            let trEl = document.createElement("tr");
            trEl.innerHTML = `<td>\${board.bno}</td>
                  <td>\${board.btitle}</td>
                  <td>\${board.bwriter}</td>
                  <td>\${board.bhits}</td>
                  <td>\${board.bdate}</td>`;
            tbodyEl.appendChild(trEl);
          }
          tableEl.appendChild(tbodyEl);
          contentBoxEl.appendChild(tableEl);
        }
      }

      function getInfoByType(contentType) {
        let returnData = null;
        $.ajax({
          url: contentType,
          type: "get",
          dataType: "json",
          async: false,
          success: function (response) {
            returnData = response;
          },
        });
        return returnData;
      }
    </script>
  </body>
</html>
