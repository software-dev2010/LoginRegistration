<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div>
		<a href="${friendsAtr}">Friends</a> |  
		<a href="LogoutServlet">Logout</a> 
		<hr>   
		<h1 style="color: blue">Hi ${userAtr.name}!!! This is your profile page!!!</h1>
	</div>
	
	<div id="container">
		<h3>Update Profile</h3>
		
		<form action="LoginRegisterServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />
			
			<input type="hidden" name="userId" value="${userAtr.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Username:</label></td>
						<td><input type="text" name="username" 
								   value="${userAtr.username}" /></td>
					</tr>

					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="name" 
								   value="${userAtr.name}" /></td>
					</tr>
					
					<tr>
						<td><label>Email address:</label></td>
						<td><input type="text" name="email" 
								   value="${userAtr.email}" /></td>
					</tr>
					
					<tr>
						<td><label>Country:</label></td>
						<td><input type="text" name="country" 
								   value="${userAtr.country}" /></td>
					</tr>

					<tr>
						<td><label>Town:</label></td>
						<td><input type="text" name="town" 
								   value="${userAtr.town}" /></td>
					</tr>		
					
					<tr>
						<td><label>Age:</label></td>
						<td><input type="text" name="age" 
								   value="${userAtr.age}" /></td>
					</tr>				

					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="password" 
								   value="${userAtr.password}" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<c:url var="deleteLink" value="StudentControllerServlet">
				<c:param name="command" value="DELETE" /> 
				<c:param name="studentId" value="${userAtr.id}" />		
			</c:url>
			
			<a href="${deleteLink}"
				onclick="if (!(confirm('Are you sure you want to delete your account?'))) return false">Delete Account</a>
		</p>
	</div>
</body>
</html>