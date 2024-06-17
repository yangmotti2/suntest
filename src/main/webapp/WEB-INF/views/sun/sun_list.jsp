<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- <table border="1" align="center">
		<c:forEach var="time" items="${ list }" begin="">
			<tr>
				<td>${ time }</td>
			</tr>
		</c:forEach>
	</table> --%>
	
	<!-- 현재 들어가 있는 DB 조회 -->
	<table border="1">
		<c:forEach var="vo" items="${list}">
		<tr>
			<td>${vo.idx}</td>	
			<td>${vo.direct_radiation}</td>	
			<td>${vo.time}</td>	
		</tr>
		</c:forEach>
	</table>
	
	${no_date}
		
	<a href="#" onclick="location.href='change.do'">차오르는 모양</a>

	<a href="#" onclick="location.href='uv_change.do'">uv api</a>
	
<%-- 	${ res } 개 insert 성공! --%>
	
</body>
</html>