<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<a href="${profileAtr}">Profile</a> |
	<a href="${friendsAtr}">Friends</a> |  
	<a href="LogoutServlet">Logout</a> 
	<hr>   
	<h1 style="color: blue">Hi ${userAtr.name}!!! This is welcome page!!!</h1>
</body>
</html>