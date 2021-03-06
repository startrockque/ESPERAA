<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Connexion</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="colog">

<c:url value="/Inscription" var="inscription" />

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-6 col-md-offset-3">
			<c:if test="${!empty erreur}">			
				<div class="alert alert-danger col-md-12" role="alert">
	         	   <p class="text-center"><c:out value="${erreur}"/></p>
	            </div>
            </c:if>
            <div class="account-wall">
            	<h1 class="text-center login-title welcomeBubule">Bienvenu sur le site d'ESPERAA !</h1>
				<h2 class="text-center login-title">Je me connecte</h2>
                <img class="profile-img" src="http://www.emeraude-app.fr/img/logo-ulule.png" alt="">
                <form class="form-signin" action="Connexion" method="post">
                	<div class="form-group">
                		<label for="login">Login : </label><input name="login" id="login" type="text" size="20" placeholder="Pseudonyme" class="form-control" required autofocus/><br />
               		</div> 
					<div class="form-group">	
						<label for="pwd">Mot de passe : </label><input name="passwd" id="pwd" type="password" size="20" placeholder="Mot de passe" class="form-control" required/><br />
					</div> 
					<input type="submit" name="act" value="Connexion" class="btn btn-primary btn-success col-md-8 col-md-offset-2"/>
                </form>
				<a href="<c:out value="${inscription}"/>" class="text-center new-account"> S'inscrire </a>
            </div>
        </div>
    </div>
</div>
</body>
<script src="css/bootstrap/js/bootstrap.min.js"></script>
</html>