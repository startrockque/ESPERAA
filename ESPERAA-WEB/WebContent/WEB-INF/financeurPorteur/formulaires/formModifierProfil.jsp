<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-md-offset-4 col-md-4">
	<h1 class="text-center">Modifier mon profil</h1>
	<form class="form-signin" action="ModifierProfil" method="post" enctype="multipart/form-data">
		<input type="email" name="email" placeholder="ex@ple.fr" value="${donateur.email}" class="form-control" required/> <br />
		<input type="password" name="ancienPassword" placeholder="Ancien mot de passe" class="form-control" required/> <br />
		<input type="password" name="password" placeholder="Nouveau mot de passe" class="form-control"/> <br />
		<input type="password" name="confirmation_password" placeholder="Confirmer nouveau mot de passe" class="form-control"/> <br />
		<input type="text" name="nom" placeholder="Nom" value="${donateur.nom}" class="form-control" required/> <br />
		<div class="input-group">
        	<span class="input-group-btn">
            	<span class="btn btn-primary btn-file">
                	 Photo de profil&hellip; <input type="file" id="imageMembre" name="imageMembre" accept="image/*" class="btn btn-primary btn-primary"/>
                 </span>
             </span>
             <input type="text" class="form-control" id="valdfil" readonly>
         </div>
		<input type="submit" value="Modifier" name="action" class="btn btn-primary col-md-10 col-md-offset-1"/>
	</form>
	<c:if test="${not empty erreur}">
		<div class="alert alert-danger col-md-12" role="alert">
	 	   <p class="text-center"><c:out value="${erreur}"/></p>
	    </div>
    </c:if>
</div>