<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Page d'accueil</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
	<script src="../js/jquery.js"></script>
    <script src="../css/bootstrap/js/bootstrap.min.js"></script>
    <script src="../css/bootstrap/js/bootstrap.min-def.js"></script>
    <script type="text/javascript">
		$(document).ready(function(){ 
		    $("#myTab li:eq(0) a").tab('show');
		});
	</script>
</head>
<body>

	<c:import url="commun/bar_nav.jsp"/>
	
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<c:import url="formulaires/formRechercherChevaux.jsp"/>
				<c:import url="affichages/listesPredefiniesChevaux.jsp"/>
			</div>
			<div class="col-md-9">
				<c:import url="affichages/afficherEnAvant.jsp"/>
				<div class="row">
					<p class="text-center">Liste des chevaux</p>
					<c:import url="affichages/pagination.jsp"/>	
					<c:import url="affichages/afficherTousChevaux.jsp"/>
				</div>
			</div>
		</div>
	</div>	
</body>	
</html>