<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Page d'accueil</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<c:import url="commun/bar_nav.jsp"/>
	
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<c:import url="formulaires/formRechercherProjets.jsp"/>
				<c:import url="affichages/listesPredefiniesProjets.jsp"/>
			</div>
			<div class="col-md-9">
				<c:import url="affichages/afficherEnAvant.jsp"/>
				<div class="row">
					<p class="text-center">Liste des projets</p>
					<c:import url="affichages/pagination.jsp"/>	
					<c:import url="affichages/afficherTousProjets.jsp"/>
				</div>
			</div>
		</div>
	</div>	
</body>
	<script src="../js/jquery.js"></script>
    <script src="../css/bootstrap/js/bootstrap.min.js"></script>
    <script src="../css/bootstrap/js/bootstrap.min-def.js"></script>
</html>