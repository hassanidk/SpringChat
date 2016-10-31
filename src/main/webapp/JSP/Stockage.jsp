<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap, modele.Message,controller.ControllerMessage" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	ServletContext ctx=getServletContext();  
	
	Map<String, List<Message>> listeMessages = (Map<String, List<Message>> )ctx.getAttribute("modele");
	
	 	String nomSalon;
	    boolean testSalon;
	    String pseudo = (String) request.getSession().getAttribute("pseudo");
	    testSalon = false;
	    nomSalon = (String)request.getSession().getAttribute("salon");
	    
	    	
	    Message mes = new Message(pseudo, request.getParameter("newMessage"));
        for (Map.Entry<String, List<Message>> it: listeMessages.entrySet()){
            if (it.getKey().equals(nomSalon)){
                testSalon = true;
            }		  		
        }
        // Création d'une arraylist de message si le salon n'existe pas
        if (testSalon == false){
            listeMessages.put(nomSalon, new ArrayList<Message>());
        }	
        // Ajout du message dans la liste de message concerné
        listeMessages.get(nomSalon).add(mes);
        
     // Mise à jour du cookie utilisateur
 	ControllerMessage test;
     test = new ControllerMessage();
     
     Cookie cookie = test.getCookie(request, "utilisateur");
     cookie.setValue(String.valueOf(listeMessages.get(nomSalon).size()));
     
     
     response.addCookie(cookie);
     request.setAttribute("modele", listeMessages);
	
	
	
	%>
	<jsp:forward page="Messages.jsp"/>
</body>
</html>