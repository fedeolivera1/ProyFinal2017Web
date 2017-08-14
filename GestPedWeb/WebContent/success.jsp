<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
ACA VA: <br>
<%
	String nomUsu = (String) session.getAttribute("usr");
	if(nomUsu != null) {
%>
    	<h1>Hi welcome <%=nomUsu%> </h1><br>
    	<a href="login.jsp">Back to Login</a>    
<%
	} else {
%>
	<h1>No se encontro el usuario en sesion. </h1><br>
<%		
	}
%>
</body>
</html>