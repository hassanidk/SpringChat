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
		<title> Interface Gestion Utilisateur </title>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                 <link href="<c:url value="#" />" type="text/css" rel="stylesheet">
	</head> 
	<body>
	 	<div class="sectionBackoffice">
			<h2 class="titreDiv">Ajouter un Utilisateur</h2>
				<form:form method="POST" action="users" >					
					<input type="text"  id="pseudoUtilisateur" name="user" placeholder="Pseudo de l'utilisateur ..">
					<input type="submit" value="Ajouter" id="btnAjoutUtilisateur"> 
				</form:form>
		</div>
		Modifier pseudo : Requete  { put users/id}
		<div class="sectionBackoffice">
			<h2 class="titreDiv">Liste utilisateurs</h2>
				<c:forEach items = "${listeUsers}" var="item" varStatus="loop" >
            <div>
                <a href="users/${loop.index}">${item}  </a>
            </div>                       
	</c:forEach>
		</div>
	
	
	</body>
</html>