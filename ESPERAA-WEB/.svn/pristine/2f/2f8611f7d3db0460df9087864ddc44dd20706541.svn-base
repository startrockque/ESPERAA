<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset>
	<legend>Créer une catégorie :</legend>
	<c:if test="${erreur != null}">
		<div class="alert alert-danger" role="alert"><c:out value="${erreur}" /></div>
	</c:if>
	<form action="Categories" method="post">
		<label for="titre">Titre de la catégorie : </label><input name="titreCategorie" id="titre" type="text" size="30" class="form-control"/>
		<br/>
		<button name="submit" value="submit" class="btn btn-primary btn-primary col-md-offset-4 col-md-4"><i class="glyphicon glyphicon-plus"></i> Créer</button>
	</form>
</fieldset>