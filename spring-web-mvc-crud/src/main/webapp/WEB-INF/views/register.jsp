<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="d" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<d:form action="/registerForm" method="post">
	
			<d:label path="">name</d:label><br>
			<d:input path="" name=""/><br>
			
			<d:label path="">email</d:label>
		
	</d:form>
</body>
</html>