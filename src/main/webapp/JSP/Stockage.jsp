<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="modele.GestionMessages,modele.Message,controller.ControllerMessage" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%	
		// Récupération de la Map dans le contexte applicatif
		ServletContext ctx=getServletContext();  
		GestionMessages listeMessages = (GestionMessages )ctx.getAttribute("modele");
	    String pseudo = (String) request.getSession().getAttribute("pseudo");
	    
	    String nomSalon = (String)request.getSession().getAttribute("salon");
	    
	    	
	    Message mes = new Message(pseudo, request.getParameter("newMessage"));
	    listeMessages.setNewMessage(nomSalon, mes);
	  
   
	%>
	
	<jsp:forward page="Messages.jsp"/>
</body>
</html>