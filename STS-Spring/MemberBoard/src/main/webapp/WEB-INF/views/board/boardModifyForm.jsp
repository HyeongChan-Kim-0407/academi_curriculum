<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Home</title>
    <link rel="stylesheet" href="/resources/css/main.css" />
    <!-- GET http://localhost:8080/controller/style.css net::ERR_ABORTED 404 (Not Found) -->
    <style>
      #pageContents > p {
        text-align: center;
      }

      #pageContents table {
        text-align: center;
        margin: 0 auto;
      }
      /* #pageContents input[type="submit"]{

      } */
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
        <h1>boardWriteForm.jsp 글작성 페이지</h1>
      </div>

      <div id="pageContents">
        <!-- 페이지 컨텐츠 -->
        <h2>글 수정</h2>
        <form
          id="boardForm"
          action="/boardModify"
          method="post"
          enctype="multipart/form-data"
        >
          <input type="hidden" name="bno" value="${board.bno}" />
          <!-- 사용자에게 보이지 않지만 데이터 처리를 위한 글 번호 삽입 -->
          <table border="1">
            <thead></thead>
            <tbody>
              <tr>
                <td>제목</td>
                <td>
                  <input
                    type="text"
                    name="btitle"
                    placeholder="제목을 입력해주십시오"
                    value="${board.btitle}"
                  />
                </td>
              </tr>
              <tr>
                <td>첨부이미지</td>
                <td>
                  <input type="file" name="bfile" value="${board.bfilename}" />
                </td>
              </tr>
              <tr>
                <td>내용</td>
                <td>
                  <textarea
                    name="bcontents"
                    id=""
                    rows="5"
                    cols="20"
                    placeholder="내용입력"
                  >
                  ${board.bcontents}
                  </textarea>
                  <!-- rows 행, cols 열 -->
                  <!-- 파일선택 -->
                </td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <td>
                  <input type="submit" value="등록하기" />
                  <button type="button" onclick="location.href='/boardList'">
                    글목록
                  </button>
                </td>
              </tr>
            </tfoot>
          </table>
        </form>
      </div>
    </div>
  </body>
</html>
