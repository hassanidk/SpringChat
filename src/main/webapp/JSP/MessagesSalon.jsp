<%-- 
    Document   : Messages
    Created on : 4 oct. 2016, 19:53:31
    Author     : thibom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setHeader("refresh", "5");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PanelAdmin - Message Salon</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Cache-control" content="public">       
    </head>
    <body>
        ${message}
        <c:forEach items = "${messages}" var="item" >
            <div>
                ${item.getPseudo()} : ${item.getMessage()}  
            </div>                       
	</c:forEach>
     
    </body>
</html>
