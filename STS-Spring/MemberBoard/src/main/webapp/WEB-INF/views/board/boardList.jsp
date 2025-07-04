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
        /* height: 100%; */
        max-height: 500px;
      }
    </style>
    <c:if test="${boardMsg != null}">
      <script>
        alert("${boardMsg}");
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
        <h1>boardList.jsp 게시판 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <h2>게시판</h2>
        <table border="1">
          <thead>
            <tr>
              <td colspan="5">
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
            <tr>
              <th>글번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>조회수</th>
              <th>작성일</th>
            </tr>
          </thead>
          <tbody>
            <c:if test="${not empty boardList}">
              <c:forEach var="board" items="${boardList}">
                <tr>
                  <td>${board.bno}</td>
                  <td>
                    <a
                      href="/boardView?bno=${board.bno}"
                      style="text-decoration: none; color: black"
                      >${board.btitle}</a
                    >
                  </td>
                  <td>${board.bwriter}</td>
                  <td>${board.bhits}</td>
                  <td>${board.bdate}</td>
                </tr>
              </c:forEach>
            </c:if>
            <c:if test="${empty boardList}">
              <tr>
                <th colspan="5">등록된 글이 없습니다.</th>
              </tr>
            </c:if>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="5">
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
