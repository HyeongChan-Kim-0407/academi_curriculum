<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Home</title>
    <link rel="stylesheet" href="/resources/css/main.css" />
    <!-- GET http://localhost:8080/controller/style.css net::ERR_ABORTED 404 (Not Found) -->
    <style>
      #pageContents > h2 {
        text-align: center;
      }
      #pageContents > table {
        text-align: center;
        margin: 0 auto;
        width: 100%;
        height: 100%;
        max-height: 500px;
      }
      #bdate {
        text-align: end;
        padding-right: 10px;
      }
    </style>
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
        <h1>boardList.jsp 게시판 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <h2>게시판</h2>
        <table border="1">
          <thead>
            <tr>
              <th>${board.bno}</th>
              <th colspan="2">${board.btitle}</th>
              <th>${board.bhits}</th>
            </tr>
            <tr>
              <th>${board.bwriter}</th>
              <th colspan="4" id="bdate">${board.bdate}</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="4">${board.bcontents}</td>
            </tr>
            <tr>
              <td colspan="4"><img src="${board.bfilename}" alt="" /></td>
            </tr>
            <tr>
              <c:if test="${board.bwriter != sessionScope.loginId}">
                <td colspan="4">
                  <button
                    type="button"
                    onclick="location.href='/boardList'"
                    style="background-color: lightblue; color: white"
                  >
                    게시판
                  </button>
                </td>
              </c:if>
              <c:if test="${sessionScope.loginId == board.bwriter}">
                <td colspan="2">
                  <button
                    type="button"
                    onclick="location.href='/boardList'"
                    style="background-color: lightblue; color: white"
                  >
                    게시판
                  </button>
                </td>
                <td>
                  <button
                    type="button"
                    onclick="location.href='/boardModify?bno=${board.bno}'"
                    style="background-color: lightgreen; color: white"
                  >
                    글수정
                  </button>
                </td>
                <td>
                  <button
                    type="button"
                    onclick="location.href='/boardDelete?bno=${board.bno}'"
                    style="background-color: lightgreen; color: white"
                  >
                    글삭제
                  </button>
                </td>
              </c:if>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="4">
                <select name="" id="">
                  <option>제목</option>
                  <option>내용</option>
                  <option>작성자</option>
                </select>
                <input type="text" placeholder="검색어를 입력해주십시오" />
                <button>검색</button>
                <c:if test="${sessionScope.loginId != null}">
                  <button type="button" onclick="location.href='/boardWrite'">
                    글작성
                  </button>
                  <!-- http://localhost:8080/boardWrite -->
                </c:if>
              </td>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  </body>
</html>
