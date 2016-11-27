<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="controller.ControllerMessage,modele.GestionMessages,modele.Message,java.io.*,java.util.*, javax.servlet.*" %>

      <%
		
      ServletContext ctx=getServletContext();  
		GestionMessages listeAllMessage = (GestionMessages )ctx.getAttribute("modele");
      	String salon = (String)session.getAttribute("salon");
      //	GestionMessages listeAllMessage = (GestionMessages)request.getAttribute("modele");
      	List<Message> listeMessage = listeAllMessage.getMessages(salon);
      	if (listeMessage!=null){
			for (Message m : listeMessage){
      %>
       
  <%--      
   		<c:forEach items="${modele}" var="entry">
   		<c:if test="${entry.getMessages(salon)}">
   		<c:forEach items="${entry.value}" var="item" varStatus="loop">
   	--%>	
        <div class="divMessage">
  
            <span class="pseudoMessage"> 
            <!-- $ {item.pseudo} --> 
            <%out.println(m.getPseudo()); %>
            </span>
            <span class="separationMessage">
                -
            </span>
            <span class="contenuMessage"> 
            <!--  $ {item.message} -->  
            <%out.println(m.getMessage()); %>
            </span>
        </div>
		<%} 
      	}
	     
	     // Déclaration d'un objet de type controller pour récupérer la méthode getCookie()
	 	 ControllerMessage temp;
	     temp = new ControllerMessage();
	  	 // Récupération et mise à jour du cookie utilisateur
	     Cookie cookie = temp.getCookie(request, "utilisateur");
	     cookie.setValue(String.valueOf(listeAllMessage.getNbMessages(salon)));
	     
	     // Ajout du cookie à la réponse 
	     response.addCookie(cookie);
	     // Ajout du modele à la requete
	     request.setAttribute("modele", listeAllMessage);
		%>
