<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
	<head>
		<title> Interface Gestion Salon </title>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                 <link href="<c:url value="#" />" type="text/css" rel="stylesheet">
	</head> 
	<body>
	${erreur}
		<div class="sectionBackoffice">
			<h2 class="titreDiv"> Liste des message par salon</h2>
				<p> Pour avoir la liste des messages d'un salon ainsi que son nombre de message, veuillez cliquer sur celui-ci </p>
			    <c:forEach var="nomSalon" items="${listeSalon}" >
					<a href="salon/${nomSalon}"> ${nomSalon}</a>			
				</c:forEach>
		</div>
		
		<div class="sectionBackoffice">
                <h2 class="titreDiv">Contenu d'un message en particulier</h2>
                <form:form method="GET" action="salon*" >					
                    <input type="text"  id="nomSalon" name="nomsalon" placeholder="Nom du salon ..">
                    <input type="text"  id="idMessage" name="idmessage" placeholder="Numéro du message ..">
                    <input type="submit" value="Accéder au message" id="btnContenuMessage"> 
                </form:form>
            </div>
            <div class="sectionBackoffice">
                <h2 class="titreDiv">Liste de message après un id</h2>
                <form:form method="GET" action="salon$" >					
                    <input type="text"  id="nomSalon" name="nomsalon" placeholder="Nom du salon ..">
                    <input type="text"  id="idMessage" name="idmessage" placeholder="Numéro du message ..">
                    <input type="submit" value="Accéder à la liste de messages" id="btnContenuMessage"> 
                </form:form>
            </div>
            
            <div class="sectionBackoffice">
                <h2 class="titreDiv">Ajout d'un message dans un salon</h2>
                <form:form method="POST" action="salon" >					
                    <input type="text"  id="nomSalon" name="nomSalon" placeholder="Nom du salon ..">
                    <input type="text"  id="idMessage" name="message" placeholder="Contenu du message">
                    <input type="submit" value="Ajouter message" id="btnContenuMessage"> 
                </form:form>
            </div>
	
	</body>
</html>