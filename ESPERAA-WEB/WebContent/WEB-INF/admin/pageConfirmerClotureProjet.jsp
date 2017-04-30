<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

	<c:import url="commun/bar_nav.jsp"/>
	
	<div class="alert alert-danger col-md-offset-4 col-md-4" role="alert">
		<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		<span class="sr-only">Error:</span>
		<h4>Etes vous sur de vouloir cloturer le projet ? </h4>
		<i>Tous les financeurs du projet seront remboursés. </i> <br/>
		<i>Une notification sera envoyée aux financeurs et au porteur. </i> <br/>
		<i>L'action est irreversible. </i>
		<form action="CloturerProjet" method="post">
			<label>Entrez "cloturer" pour confirmer la suppression.</label> <br/>
			<input type=text required name="verificationCloturer" size="10"/>
			<input type="hidden" name="idProjet" value="${projet.idProjet}">
			<input type="submit" name="act" value="Valider" class="btn btn-primary btn-danger" />
		</form>			
	</div>
</body>
</html>