<%-- 
    Document   : Messages
    Created on : 4 oct. 2016, 19:53:31
    Author     : thibom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TP 2 Servlets - Messages.jsp</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="../CSS/messages.css" type="text/css" rel="stylesheet">
        <!-- Va permettre de rafraichir la page toutes les 5 secondes -->
         <meta http-equiv="Refresh" content="5">
        
    </head>
    <body>
        <h1>Qui de nouveau dans le Chat ?</h1>
        <h2> Salon <% out.println(session.getAttribute("salon")); %></h2>
         
   		<c:forEach items="${modele}" var="entry">
   		<c:if test="${entry.key eq salon}">
   		<c:forEach items="${entry.value}" var="item" varStatus="loop">
   		
        <div class="divMessage">
            <span class="pseudoMessage"> 
             ${item.pseudo}
            </span>
            <span class="separationMessage">
                -
            </span>
            <span class="contenuMessage"> 
            ${item.message}   
            </span>
        </div>
        </c:forEach>
        </c:if>
</c:forEach>
    </body>
</html>
