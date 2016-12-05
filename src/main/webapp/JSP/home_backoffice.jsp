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
                 <link href="<c:url value="#" />" type="text/css" rel="stylesheet">
	</head> 
	<body>
            <h1 id="titreBackoffice"> BackOffice du Super Chat !</h1>
           
            
            <div class="sectionBackoffice">
            	 <a href="back-office/users" class="titreDiv">Gestion Utilisateur</a>
            </div>
            <div class="sectionBackoffice">
            	 <a href="back-office/salon" class="titreDiv">Gestion Salon & Messages</a>
            </div>
            
	</body>
</html>
