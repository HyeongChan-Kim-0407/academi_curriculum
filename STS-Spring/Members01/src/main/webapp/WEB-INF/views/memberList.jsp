<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
<!-- 공통 스타일 CSS -->
<link rel="stylesheet" href="resources/css/main.css" />
<!-- 회원 정보 table CSS -->
<link rel="stylesheet" href="resources/css/memberInfo.css" />

</head>
<body>
	<div class="container">
		<!-- pageMenu -->
		<div id="pageMenu">
			<div>
				<a href="/">MEMBERS</a>
			</div>
			<ul>
				<!-- 로그인이 되어 있지 않은 경우 -->
				<c:if test="${sessionScope.loginMid == null }">
					<li><a href="/join">회원가입</a></li>
					<li><a href="login">로그인</a></li>
				</c:if>
				<!-- 로그인이 되어 있는 경우 -->
				<c:if test="${sessionScope.loginMid != null }">
					<li><a href="myInfo">내정보</a></li>
					<li><a href="logout">로그아웃</a></li>
				</c:if>
			</ul>
		</div>

		<!-- pageTitle -->
		<div id="pageTitle">
			<h1>회원목록 페이지</h1>
		</div>

		<!-- pageContents -->
		<div id="pageContents">
			<h2 style="text-align: center; ">회원목록</h2>
			<table id="memberInfo">
				<thead>
					<tr>
						<th>아이디</th>
						<th>비밀번호</th>
						<th>이름</th>
						<th>생년월일</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${mList }" var="member"  >
					<tr>
						<td>${member.mid }</td>
						<td>${member.mpw }</td>
						<td>${member.mname }</td>
						<td>${member.mbirth }</td>
						<td> <a href="deleteMember?delmid=${member.mid }">삭제</a></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
