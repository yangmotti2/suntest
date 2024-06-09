<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<table border="1">
			<tr>
				<td>time</td>
				<td>uv_index_max</td>
				<td>uv_index_clear_sky_max</td>
			</tr>	
				
			<c:forEach var="vo" items="${list}">
				<tr>
					<td>${vo.time}</td>			
					<td>${vo.uv_index_max}</td>			
					<td>${vo.uv_index_clear_sky_max}</td>			
				</tr>
			</c:forEach>
		
		
		</table>
	</body>
</html>