<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Profil de <c:out value="${admin.login}"/></title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">	
	<link href="../css/profil.css" rel="stylesheet" type="text/css">	
</head>
<body>
	
	<c:import url="commun/bar_nav.jsp"/>
	
	<div class="container">
		<div class="row">
			<div class="col-md-4">
			</div>
			<div class="col-md-4">
				<fieldset class="profilInfos">
					<legend>Cette personne est administrateur</legend>
					<div class="col-md-8 col-md-offset-2">
						<div class="box box-widget widget-user">
							<div class="widget-user-header bg-teal">
						    	<h3 class="widget-user-username"><c:out value="${admin.login}"/></h3>
						    	<h5 class="widget-user-desc"><c:out value="${admin.email}"/></h5>
						    </div>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>
</body>
</html>