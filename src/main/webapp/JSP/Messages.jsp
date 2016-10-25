<%-- 
    Document   : Messages
    Created on : 4 oct. 2016, 19:53:31
    Author     : thibom
--%>

<%@page import="java.util.*"%>
<%@page import="JAVA.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! 
    String nomSalon;
    boolean testSalon;
    Map<String, List<Message>> listeMessage = new HashMap<String, List<Message>>();	
%>
<% 	
    //Variable qui permet de tester si le salon existe
    testSalon = false;
    nomSalon = (String)session.getAttribute("salon");
    if(request.getMethod().equals("POST")){ //Création du message	
        Message mes = new Message((String)session.getAttribute("pseudo"), request.getParameter("newMessage"));
        for (Map.Entry<String, List<Message>> it: listeMessage.entrySet()){
            if (it.getKey().equals(nomSalon)){
                testSalon = true;
            }		  		
        }
        // Création d'une arraylist de message si le salon n'existe pas
        if (testSalon == false){
            listeMessage.put(nomSalon, new ArrayList<Message>());
        }	
        // Ajout du message dans la liste de message concerné
        listeMessage.get(nomSalon).add(mes);

    }
%>
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
        
    <% for (Map.Entry<String, List<Message>> it: listeMessage.entrySet()){
    		if (it.getKey().equals(nomSalon)){
                    int i;
                    for (i =0;i < it.getValue().size();i++){	
    		
    %>
        <div class="divMessage">
            <span class="pseudoMessage"> 
                <% out.println(it.getValue().get(i).getPseudo()); %> 
            </span>
            <span class="separationMessage">
                -
            </span>
            <span class="contenuMessage"> 
                <% out.println(it.getValue().get(i).getMessage()); %> 
            </span>
        </div>
    <% 			}
    		}
    	}
%>
    </body>
</html>
