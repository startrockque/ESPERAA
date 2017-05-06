<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><c:out value="${cheval.nom}" /></title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
    <link href="../css/projet.css" rel="stylesheet">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>

	<c:import url="commun/bar_nav.jsp"/>
	
	<div class="container">

        <div class="row">
		
			<div class="col-md-3">
				<c:import url="affichages/afficherTranches.jsp"/>
			</div>
			
			<div class="col-md-7">
				<c:import url="affichages/afficherCheval.jsp"/>
				
				<c:import url="affichages/afficherMessageCheval.jsp"/>
			</div>
			
			<div class="col-md-2">
				<c:import url="affichages/afficherOptionsCheval.jsp"/>
			</div>
		</div>
	</div>
</body>
	<script src="../js/jquery-projet.js"></script>
	<script src="../js/jquery.js"></script>
    <script src="../css/bootstrap/js/bootstrap.min-projet.js"></script>
    <script src="../css/bootstrap/js/bootstrap.min-def.js"></script>
    <script src="../css/dist/js/app.min.js"></script>
</html>