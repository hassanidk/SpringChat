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
	</head> 
	<body>
            <h1> Interface Back Office </h1>
           		<h2>Ajout utilisateur</h2>
	           		<form:form method="POST" action="back-office/users" >
					
						<label for="Nom utilisateur">NOm utilisateur</label>
						<input type="text"  name="user" placeholder="user Name" maxlength="80">
						<button>Confirm</button>
						
					</form:form>
				<h2> Liste des message par salon</h2>
				
					<c:forEach var="nomSalon" items="${listeSalon}" >
						<a href="back-office/salon/${nomSalon}"> ${nomSalon}</a></td>			
					</c:forEach>
				<h2>COntenu d'un message en particulier</h2>
				
	</body>
</html>
