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
        <h1>boardList.jsp 게시판 페이지 <i class="fa-solid fa-image"></i></h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <h2>게시판</h2>
        <!-- model을 통한 데이터 출력 
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
                   http://localhost:8080/boardWrite 
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
                   http://localhost:8080/boardWrite
                </c:if>
              </td>
            </tr>
          </tfoot>
        </table> -->

        <!-- ajax를 통한 데이터 출력 -->
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
                  <!-- http://localhost:8080/boardWrite -->
                </c:if>
                <button type="button" onclick="location.href='/boardWrite'">
                  글작성
                </button>
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
          <tbody id="ajaxTbody"></tbody>
          <tfoot>
            <tr>
              <td colspan="5">
                <select name="" id="searchType">
                  <option value="BTITLE">제목</option>
                  <option value="BCONTNETS">내용</option>
                  <option value="BWRITER">작성자</option>
                </select>
                <input
                  id="searchText"
                  type="text"
                  placeholder="검색어를 입력해주십시오"
                />
                <button onclick="search()">검색</button>
                <c:if test="${sessionScope.loginId != null}">
                  <!-- http://localhost:8080/boardWrite -->
                </c:if>
                <button type="button" onclick="location.href='/boardWrite'">
                  글작성
                </button>
              </td>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
    <!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
      /* 서버에 글 목록 데이터 조회 요청 */
      let offsetNumber = 0;
      let fetchNumber = 5;
      function getBoardList() {
        // 1. 데이터 조회 요청(ajax)
        $.ajax({
          url: "/getBoardList",
          type: "get",
          data: { offset: offsetNumber, fetch: fetchNumber },
          dataType: "json",
          success: function (response) {
            console.log("글목록 조회");
            console.log(response); // [{board}, {board}, ...]
            printBoardList(response);
            offsetNumber += fetchNumber;
          },
        });
      }

      function printBoardList(boardList) {
        // response 출력
        console.log("printBoardList() 호출");
        const tbodyEl = document.getElementById("ajaxTbody");
        // tbodyEl.innerHTML = ""; // tbodyEl의 하위요소 초기화
        for (board of boardList) {
          console.log(board.bfilename);
          let imageIcon = ``;
          if (board.bfilename != null) {
            imageIcon = `<i class="fa-solid fa-camera"></i>`;
          }
          let modifyIcon = ``;
          if (board.bstate == "M") {
            modifyIcon = `<i class="fa-solid fa-file-pen"></i>`;
          }
          const trEl = document.createElement("tr");
          trEl.innerHTML = `<td>\${board.bno}</td>
          <td><a href="/boardView?bno=\${board.bno}" style="text-decoration: none; color: black">\${board.btitle}</a>
          \${imageIcon} \${modifyIcon}</td>
          <td>\${board.bwriter}</td>
          <td>\${board.bhits}</td>
          <td>\${board.bdate}</td>`;
          tbodyEl.appendChild(trEl);
        }
        const tableRow = document.createElement("tr");
        tableRow.id = "addRow";
        if (boardList.length >= fetchNumber) {
          tableRow.innerHTML =
            '<td colspan="5"> <button onclick="addBoardList()">더보기</button> </td>';
          tbodyEl.appendChild(tableRow);
        }
      }

      function addBoardList() {
        const removeTableRow = document.getElementById("addRow");
        removeTableRow.remove();
        getBoardList();
      }

      /* 글 목록 데이터를 화면에 출력 (tbody태그 내의 요소 생성) */
      getBoardList();

      /* 검색된 글 목록 조회 - [{board}, {board}, ...] */
      function search() {
        const selectEl = document.getElementById("searchType");
        const inputEl = document.getElementById("searchText");
        console.log(selectEl.value);
        console.log(inputEl.value);
        $.ajax({
          url: "/searchBoard",
          type: "get",
          data: { searchType: selectEl.value, searchText: inputEl.value },
          dataType: "json",
          success: function (response) {
            console.log(response);
            printBoardList(response);
          },
        });
      }
      /* printBoardList(검색된 글 목록) */
    </script>
  </body>
</html>
