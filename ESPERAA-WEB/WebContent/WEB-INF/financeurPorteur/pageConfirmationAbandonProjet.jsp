<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Projet abandonné</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<c:import url="commun/bar_nav.jsp"/>

	<div class="alert alert-success col-md-offset-4 col-md-4" role="alert">
		<h4>Projet abandonné</h4>
		<i>Votre projet a bien été abandonné.</i><br/>
		<c:url value="/Membre/Accueil" var="accueil"/>
		<a href="<c:out value="${accueil}"/>" class="new-account"> Retour à l'accueil </a>
	</div>
	
</body>
</html>