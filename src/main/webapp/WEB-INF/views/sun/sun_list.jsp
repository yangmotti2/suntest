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

	<table border="1" align="center">
		<c:forEach var="time" items="${ list }" begin="">
			<tr>
				<td>${ time }</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>