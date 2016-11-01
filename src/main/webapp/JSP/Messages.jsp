<%-- 
    Document   : Messages
    Created on : 4 oct. 2016, 19:53:31
    Author     : thibom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List,java.util.ArrayList,java.util.Map,java.util.HashMap, modele.Message,controller.ControllerMessage" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TP 2 Servlets - Messages.jsp</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Cache-control" content="public">
        <link href="<c:url value="CSS/messages.css" />" type="text/css" rel="stylesheet">
        <link href="../CSS/messages.css" type="text/css" rel="stylesheet">
        <!-- Va permettre de rafraichir la page toutes les 5 secondes -->
   
             
    </head>
    <body>
        <h1>Qui de nouveau dans le Chat ?</h1>
        <h2> Salon <% out.println(session.getAttribute("salon")); %></h2>
      		<jsp:include page="Affichage.jsp"/>
    </body>
</html>
