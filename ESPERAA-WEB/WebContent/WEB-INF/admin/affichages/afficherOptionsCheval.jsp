<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="gestionProjetAdmin">
	<form action="SupprimerCheval" method="get">
		<input type="hidden" name="idCheval" value="${cheval.idCheval}">
		<button name="act" value="Supprimer ce cheval" class="btn btn-promary btn-danger col-md-12"><i class="glyphicon glyphicon-alert"></i> Supprimer ce cheval<i class="glyphicon glyphicon-alert"></i></button>
	</form>
	<br />
	<c:if test="${not cheval.enAvant}">
		<form action="MettreEnAvant" method="post">
			<input type="hidden" name="idCheval" value="${cheval.idCheval}">
			<button name="mea" value="Mettre en avant" class="btn btn-primary btn-success col-md-12"><i class="glyphicon glyphicon-star-empty"></i>Mettre en avant<i class="glyphicon glyphicon-star-empty"></i></button>
		</form>
	</c:if>
	<c:if test="${cheval.enAvant}">
		<form action="MettreEnAvant" method="post">
			<input type="hidden" name="idCheval" value="${cheval.idCheval}">
			<button name="mea" value="Mettre en arriere" class="btn btn-primary btn-danger pull-right col-md-12"><i class="glyphicon glyphicon-star"></i>Mettre en arrière<i class="glyphicon glyphicon-star"></i></button>
		</form>
	</c:if>
</div>