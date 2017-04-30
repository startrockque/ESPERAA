<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Profil de <c:out value="${loginFinanceur}"/></title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">	
	<link href="../css/profil.css" rel="stylesheet" type="text/css">	
</head>
<body>
	
	<c:import url="commun/bar_nav.jsp"/>
	
	<div class="col-md-4">
		<c:import url="affichages/afficherProfil.jsp"/>
	</div>
	
	<div class="col-md-8">
		<fieldset class="searchForm">
			<legend>Ses projets</legend>
			<c:import url="affichages/afficherTousProjets.jsp"/>
		</fieldset>
	</div>
</body>
</html>