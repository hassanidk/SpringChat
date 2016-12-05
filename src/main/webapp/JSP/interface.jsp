<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TP 2 Servlets - Interface.html</title>
        <meta charset="UTF-8">
        <link href="CSS/style.css" type="text/css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <iframe src="Controller" id="iframeInterface" name="messages"> </iframe>
		
        <form method="post" action="Controller" target="messages" id="formInterface">
            <label id="labelMessage">Entrez votre message :</label><input type="text" placeholder="Votre message ..." name="newMessage">
            <input type="submit" value="Envoyer"> 
        </form>
        <%if (request.getAttribute("panelAdmin").equals("panelAdmin")){ %>
        <a href="back-office"><%out.println(request.getAttribute("panelAdmin")); %></a>
        <%} %>
        <a id="quitterInterface" href="quitter.html"> Quitter </a>
    </body>
</html>
