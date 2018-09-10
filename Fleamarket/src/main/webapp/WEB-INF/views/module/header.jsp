<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>
헤더 부분
	<div id="headmenu">
		<ul>
			<li><a href="/fleamarket/board/customerCenterList.do" id="notice">NOTICE</a></li>
			<c:if test="${member != null }">
				<li>[${member.member_name }]</li>
			</c:if>
			<c:choose>
				<c:when test="${member.member_name == null }">
					<li class="detailmenu"><a href="/fleamarket/memmanage/login">LOGIN</a></li>
					<li class="detailmenu"><a href="/fleamarket/memmanage/joinform">JOIN</a></li>
				</c:when>
				<c:when test="${member.member_name != null }">
					<li class="detailmenu"><a href="/fleamarket/memmanage/logout">LOGOUT</a></li>
				</c:when>
			</c:choose>
		</ul>
	</div>

</body>
</html>