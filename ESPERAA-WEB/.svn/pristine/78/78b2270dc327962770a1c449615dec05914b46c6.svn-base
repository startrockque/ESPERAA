<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title>Inscription</title>
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<link href="css/inputFile.css" rel="stylesheet" type="text/css">
	<script src="css/bootstrap/js/bootstrap.min.js"></script>
	<script src="js/jquery.js"></script>
	
	<script type="text/javascript">
		$(document).on('change', '.btn-file :file', function() {
		  var input = $(this),
		      numFiles = input.get(0).files ? input.get(0).files.length : 1,
		      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		  input.trigger('fileselect', [numFiles, label]);
		});

		$(document).ready( function() {
		    $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
		        
		        var input = $(this).parents('.input-group').find(':text'),
		            log = numFiles > 1 ? numFiles + ' files selected' : label;
		        
		        if( input.length ) {
		            input.val(log);
		        } else {
		            if( log ) alert(log);
		        }
		        
		    });
		});
	</script>
</head>
<body class="colog">

<c:url value="/Connexion" var="connexion" />
	
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-6 col-md-offset-3">
            <c:if test="${!empty erreur}">			
				<div class="alert alert-danger col-md-12" role="alert">
	         	   <p class="text-center"><c:out value="${erreurMap.password}"/></p>
	         	   <p class="text-center"><c:out value="${erreurMap.password}"/></p>
	            </div>
            </c:if>
            <div class="account-wall">
				<h1 class="text-center login-title">Je m'inscris</h1>
                <form class="form-signin" action="Inscription" method="post"  enctype="multipart/form-data">
               		<input type="text" id="nom" name="nom" placeholder="Nom" value="${nom}" class="form-control" required autofocus/> <br />
					<input type="email" id="email" name="email" placeholder="ex@mple.fr" value="${email}" class="form-control" required/> <br />
					<input type="password" id="mot de passe" name="password" placeholder="Mot de passe" class="form-control" required/> <br />
					<input type="password" id="confirmation du mot de passe" name="confirmation_password" placeholder="Confirmer mot de passe" class="form-control" required/> <br />
					<input type="text" id="login" name="login" placeholder="Pseudonyme" value="${login}" class="form-control" required/> <br />
                    <div class="input-group">
		                <span class="input-group-btn">
		                    <span class="btn btn-primary btn-file">
		                        Photo de profil&hellip; <input type="file" id="imageMembre" name="imageMembre" accept="image/*" class="btn btn-primary btn-primary"/>
		                    </span>
		                </span>
		                <input type="text" class="form-control" id="valdfil" readonly>
		            </div>
					<input type="submit" value="Inscription" class="btn btn-primary btn-success col-md-8 col-md-offset-2" />
                </form>
				<a href="<c:out value="${connexion}"/>" class="text-center new-account"> Se connecter </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>