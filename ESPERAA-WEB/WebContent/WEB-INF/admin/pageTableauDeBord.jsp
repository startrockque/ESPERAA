<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Tableau de bord</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
	<link href="../css/dashboard.css" rel="stylesheet" type="text/css">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
</head>
<body class="dashboard">

	<c:import url="commun/bar_nav.jsp"/>
	
	<c:url value="/Admin/Accueil" var="accueil" />
	<c:url value="/Admin/Categories" var="categories" />
	<c:url value="/Admin/VoirMembres" var="membres" />
	
	<div class="container">
		<section class="content">
        	<div class="row">
            	<div class="col-lg-4 col-xs-6">
              		<div class="small-box bg-aqua">
                		<div class="inner">
                  			<h3><c:out value="${nbProjets}" /></h3>
                  			<p>Projets</p>
                		</div>
                		<div class="icon">
                  			<i class="ion ion-settings"></i>
                		</div>
                		<a href="<c:out value="${accueil}"/>" class="small-box-footer">Voir en détails <i class="fa fa-arrow-circle-right"></i></a>
              		</div>
            	</div>
            	
            	<div class="col-lg-4 col-xs-6">
					<div class="small-box bg-green">
                		<div class="inner">
                  			<h3><c:out value="${nbCategories}" /></h3>
                  			<p>Catégories</p>
                		</div>
	                	<div class="icon">
	                	  	<i class="ion ion-pie-graph"></i>
	                	</div>
               			<a href="<c:out value="${categories}"/>" class="small-box-footer">Voir en détails <i class="fa fa-arrow-circle-right"></i></a>
              		</div>
            	</div>
            	
            	<div class="col-lg-4 col-xs-6">
              		<div class="small-box bg-blue">
                		<div class="inner">
		                	<h3><c:out value="${nbMembres}" /></h3>
                  			<p>Membres</p>
                		</div>
                		<div class="icon">
                  			<i class="ion ion-person"></i>
                		</div>
                		<a href="<c:out value="${membres}"/>" class="small-box-footer">Voir en détails <i class="fa fa-arrow-circle-right"></i></a>
              		</div>
            	</div>
            
            	<div class="col-lg-3 col-xs-6">
            		<div class="small-box bg-red">
                		<div class="inner">
                  			<h3><c:out value="${nbAimes}" /></h3>
                  			<p>Likes</p>
                		</div>
                		<div class="icon">
                  			<i class="ion ion-thumbsup"></i>
                		</div>
              		</div>
            	</div>
            	<div class="col-lg-3 col-xs-6">
            		<div class="small-box bg-teal">
                		<div class="inner">
                  			<h3><c:out value="${nbMessages}" /></h3>
                  			<p>Messages</p>
                		</div>
                		<div class="icon">
                  			<i class="ion ion-email"></i>
                		</div>
              		</div>
            	</div>
            	<div class="col-lg-3 col-xs-6">
            		<div class="small-box bg-maroon">
                		<div class="inner">
                  			<h3><c:out value="${nbInvestissements}" /></h3>
                  			<p>Investissements</p>
                		</div>
                		<div class="icon">
                  			<i class="ion ion-social-euro"></i>
                		</div>
              		</div>
            	</div>
            	<div class="col-lg-3 col-xs-6">
            		<div class="small-box bg-yellow">
                		<div class="inner">
                  			<h3><c:out value="${nbTags}" /></h3>
                  			<p>Tags</p>
                		</div>
                		<div class="icon">
                  			<i class="ion ion-pound"></i>
                		</div>
              		</div>
            	</div>
        	</div>
		</section>
	</div>
			
</body>
	<script src="../js/jquery-projet.js"></script>
	<script src="../js/jquery.js"></script>
    <script src="../css/bootstrap/js/bootstrap.min-projet.js"></script>
    <script src="../css/bootstrap/js/bootstrap.min-def.js"></script>
    <script src="../css/dist/js/app.min.js"></script>
</html>