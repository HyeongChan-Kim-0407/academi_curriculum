<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="resources/css/main.css" />
<!-- GET http://localhost:8080/controller/style.css net::ERR_ABORTED 404 (Not Found) -->
<style>
#memberInfo {
	border-collapse: collapse;
	text-align: center;
	margin: 0 auto;
}

#memberInfo > thead td{
	padding: 0 10;
}

#memberInfo > thead th{
	padding: 5 10;
	padding-top: 15;
	border-bottom: 3px solid black;
}

#memberInfo > tbody td{
	padding: 5 10;
}
</style>
</head>
<body>
	<!-- 메뉴, 설명, 컨텐츠를 감싸는 컨테이너로 div 생성 -->
	<div class="container">
		<div id="pageMenu">
			<!-- 프로젝트이름, 메뉴 -->
			<div>
				<a href="/">Member</a>
			</div>
			<ul>
				<c:if test="${sessionScope.loginId == null }">
					<li><a href="login">로그인</a></li>
					<!-- http://localhost:8080/controller/login -->
					<li><a href="join">회원가입</a></li>
					<!-- http://localhost:8080/controller/join -->
				</c:if>
				<c:if test="${sessionScope.loginId != null }">
					<li><a href="memberInfo">회원정보</a></li>
					<li><a href="logout">로그아웃</a></li>
				</c:if>
			</ul>
		</div>

		<div id="pageTitle">
			<!-- 페이지 파일이름, 페이지 설명 -->
			<h1>memberInfo.jsp 회원정보 페이지</h1>
		</div>

		<div id="pageContents">
			<h2 style="text-align: center; margin-top: 50px">회원목록</h2>
			<!-- 페이지 컨텐츠 -->
			<table id="memberInfo">
				<thead>
					<tr>
						<td colspan="5">전체회원정보</td>
					</tr>
					<tr id="infoMenu">
						<th>아이디</th>
						<th>비밀번호</th>
						<th>이름</th>
						<th>생년월일</th>
						<th></th>
					</tr>
				</thead>
				</tbody>
				<c:forEach items="${mList }" var="member">
					<tr>
						<td>${member.mid }</td>
						<td>${member.mpw }</td>
						<td>${member.mname }</td>
						<td>${member.mbirth }</td>
						<td> <a href="deleteMember?delMid=${member.mid }">삭제</a> </td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
