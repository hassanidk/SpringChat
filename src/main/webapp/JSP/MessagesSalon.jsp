<%-- 
    Document   : Messages
    Created on : 4 oct. 2016, 19:53:31
    Author     : thibom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!-- Page JSP qui permet d'avoir les différent résultats de requetes -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PanelAdmin - Message Salon</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Cache-control" content="public">       
    </head>
    <body>
   
    <!--  Permet d'afficher la liste des salons disponibles dans l'application -->
        <c:if test ="${not empty listeSalon }">
        <c:forEach items = "${listeSalon}" var="salon" >
	            <div>
	                ${salon}
	            </div>                       
			</c:forEach>
        
        </c:if>
    <!-- Permet d'afficher la liste de message d'un salon -->
        <c:if test ="${not empty messages}">
        <div>Nombre de message : ${nbmessages}</div>
	        <c:forEach items = "${messages}" var="item" >
	            <div>
	                ${item.getPseudo()} : ${item.getMessage()}  
	            </div>                       
			</c:forEach>
		</c:if>
	<!-- Permet d'afficher un message en particulier -->
		<c:if test="${not empty message}">
			${message} 
		</c:if>
	<!-- Permet d'afficher la liste des message à partir d'un certain id -->
		<c:if test ="${not empty messages_id}">
			<c:forEach items = "${messages_id}" var="item" >
	            <div>
	                ${item.getPseudo()} : ${item.getMessage()}  
	            </div>                       
			</c:forEach>
		</c:if>
     
    </body>
</html>
