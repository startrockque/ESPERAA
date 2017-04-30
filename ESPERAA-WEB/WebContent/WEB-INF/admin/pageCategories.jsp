<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gestion des catégories</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
	<link href="../css/categorie.css" rel="stylesheet" type="text/css">
	<script src="../js/jquery.js"></script>
    <script src="../css/bootstrap/js/bootstrap.min.js"></script>
    <script src="../css/bootstrap/js/bootstrap.min-def.js"></script>
</head>
<body>
	<c:import url="commun/bar_nav.jsp"/>
	
	<div class="col-md-3">
		<c:import url="formulaires/formAjoutCategorie.jsp"></c:import>
	</div>
	<div class="col-md-9">
		<c:import url="affichages/afficherToutesCategories.jsp"></c:import>
	</div>
</body>
</html>