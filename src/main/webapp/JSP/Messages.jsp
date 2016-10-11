<%-- 
    Document   : Messages
    Created on : 4 oct. 2016, 19:53:31
    Author     : thibom
--%>

<%@page import="java.util.*"%>
<%@page import="JAVA.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! List<Message> messages = new ArrayList<Message>(); %>
<% if(request.getMethod().equals("POST")){ //On va créer et ajouter le nouveau message à la suite des autres
    Message mes = new Message((String)session.getAttribute("pseudo"), request.getParameter("newMessage"));
    messages.add(mes);
%>
<%}%>
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
        <h1>Super Chat !</h1>
        
        <%
    if(request.getMethod().equals("GET")){%>
        <h1> GET </h1>
        <!-- Rafraichir la page -->
    <%}else if (request.getMethod().equals("POST")){%>
        <h1> POST </h1>
        <!-- Rafraichir la page -->
<%}
%>

<% 
    for(int i=0; i < messages.size(); i++){
        out.println(messages.get(i).getPseudo()+" - "+messages.get(i).getMessage());
    }
%>
    </body>
</html>
