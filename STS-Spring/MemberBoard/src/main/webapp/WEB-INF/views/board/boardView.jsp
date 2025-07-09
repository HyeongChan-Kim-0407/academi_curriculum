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
        width: 600px;
        height: 100%;
        max-height: 500px;
      }
      #bdate {
        text-align: end;
        padding-right: 10px;
      }
      #button {
        height: 30px;
      }
      #replys {
        margin: 0 auto;
        width: 600px;
      }
      .reply {
        margin-bottom: 5px;
        border-bottom: 1px solid black;
        padding: 10px;
        position: relative;
      }
      .rwriter {
        font-weight: bold;
        font-size: 15px;
      }
      .rcontents {
        width: 100%;
        outline: none;
        border: 0;
        font-family: inherit;
        height: fit-content;
      }
      .rdate {
        font-size: 10px;
        color: grey;
      }
      .replyDelBtn {
        position: absolute;
        right: 10px;
        top: 20px;
        background: none;
        border: none;
        cursor: pointer;
      }
      #replyWrite {
        text-align: end;
      }
      #replyWrite > textarea {
        width: 100%;
        height: 60px;
        margin-bottom: 10px;
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
              <c:if test="${not empty board.bfilename}">
                <td colspan="4"><img src="${board.bfilename}" alt="" /></td>
              </c:if>
            </tr>
            <tr id="button">
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
        <hr />
        <div id="replys">
          <div id="replyList">
            <div class="reply">
              <div class="rwriter">테스트1</div>
              <div class="rdate">테스트1</div>
              <div class="rcontents">테스트1</div>
              <button class="replyDelBtn" onfocus="">삭제</button>
            </div>
            <div class="reply">
              <div class="rwriter">테스트2</div>
              <div class="rdate">테스트2</div>
              <div class="rcontents">테스트2</div>
              <button class="replyDelBtn">삭제</button>
            </div>
            <div class="reply">
              <div class="rwriter">테스트3</div>
              <div class="rdate">테스트3</div>
              <div class="rcontents">테스트3</div>
              <button class="replyDelBtn">삭제</button>
            </div>
          </div>
            <div id="replyWrite">
              <textarea
                name=""
                id="rcontents"
                rows="5"
                cols="10"
                placeholder="내용 입력"
              ></textarea>
              <button onclick="registReply()">댓글 등록</button>
            </div>
          <c:if test="${not empty sessionScope.loginId}">
          </c:if>
        </div>
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
      const bno = "${board.bno}";
      function getReplyList() {
        console.log("getReplyList 호출");
        $.ajax({
          url: "/getReplyList",
          type: "get",
          data: { rbno: bno },
          dataType: "json",
          success: function (response) {
            /* 댓글 출력 기능 호출 */
            printReplyList(response);
          },
        });
      }
      function printReplyList(replyList) {
        const loginId = "${sessionScope.loginId}";
        console.log("printReply 호출");
        console.log(replyList);
        const replyListEl = document.getElementById("replyList");
        replyListEl.innerHTML = ""; // 댓글 목록 공간 초기화
        console.log("replyList 초기화");
        for (reply of replyList) {
          const divEl = document.createElement("div");
          divEl.setAttribute("class", "reply");
          divEl.innerHTML = `<div class="rwriter">\${reply.rwriter}</div>
          <div class="rdate">\${reply.rdate}</div>
              <textarea class="rcontents">\${reply.rcontents}</textarea>`;
          if (reply.rwriter == loginId) {
            divEl.innerHTML += `<button class="replyDelBtn" onclick="replyDelete('\${reply.rno}')">삭제</button>`;
          }
          replyListEl.appendChild(divEl);
        }
      }

      function registReply() {
        const contentsEl = document.getElementById("rcontents");
        $.ajax({
          url: "/registReply",
          type: "get",
          data: { rbno: bno, rcontents: contentsEl.value },
          success: function (response) {
            if (response == "L") {
              alert("로그인 후 이용 가능합니다.");
              location.href = "/login";
            } else if (response == "N") {
              alert("댓글 등록에 실패했습니다.");
            } else {
              alert("댓글이 등록 되었습니다.");
              contentsEl.value = "";
              /* 댓글 목록 조회 및 출력 함수 호출 */
              getReplyList();
            }
          },
        });
      }

      function replyDelete(rno) {
        $.ajax({
          url: "/deleteReply",
          type: "get",
          data: { rno: rno },
          success: function (response) {
            if (response == "L") {
              alert("잘못된 접근입니다. 로그인 후 이용해주십시오.");
              location.href = "/login";
            } else if (response == "N") {
              alert("댓글 삭제 과정에서 오류가 발생하였습니다.");
            } else {
              alert("댓글이 삭제되었습니다.");
              getReplyList();
            }
          },
        });
      }

      getReplyList();
    </script>
  </body>
</html>
