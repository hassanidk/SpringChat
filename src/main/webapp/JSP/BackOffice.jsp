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
		<title> Home Back Office </title>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                 <link href="<c:url value="CSS/backoffice.css" />" type="text/css" rel="stylesheet">
	</head> 
	<body>
            <h1 id="titreBackoffice"> BackOffice du Super Chat !</h1>
            <div class="sectionBackoffice">
                <h2 class="titreDiv">Ajouter un Utilisateur</h2>
	            <form:form method="POST" action="back-office/users" >					
                        <input type="text"  id="pseudoUtilisateur" name="user" placeholder="Pseudo de l'utilisateur ..">
                        <input type="submit" value="Ajouter" id="btnAjoutUtilisateur"> 
                    </form:form>
            </div>
            <div class="sectionBackoffice">
                <h2 class="titreDiv"> Liste des message par salon</h2>
                <p> Pour avoir la liste des messages d'un salon, veuillez cliquer sur celui-ci </p>
                    <c:forEach var="nomSalon" items="${listeSalon}" >
                            Salon : <a href="back-office/salon/${nomSalon}"> ${nomSalon}</a>			
                    </c:forEach>
            </div>
            <div class="sectionBackoffice">
                <h2 class="titreDiv">Contenu d'un message en particulier</h2>
                <form:form method="GET" action="back-office/salon" >					
                    <input type="text"  id="nomSalon" name="nomsalon" placeholder="Nom du salon ..">
                    <input type="text"  id="idMessage" name="idmessage" placeholder="Numéro du message ..">
                    <input type="submit" value="Accéder au Message" id="btnContenuMessage"> 
                </form:form>
            </div>
	</body>
</html>
