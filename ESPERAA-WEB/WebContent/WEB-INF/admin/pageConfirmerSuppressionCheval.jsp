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
		<h4>Etes vous sur de vouloir retirer ce cheval du site ? </h4>
		<i>L'action est irreversible. </i>
		<form action="SupprimerCheval" method="post">
			<label>Entrez "confirmer" pour confirmer le retrait.</label> <br/>
			<input type=text required name="verificationSupprimer" size="10"/>
			<input type="hidden" name="idProjet" value="${cheval.idCheval}">
			<input type="submit" name="act" value="Valider" class="btn btn-primary btn-danger" />
		</form>			
	</div>
</body>
</html>