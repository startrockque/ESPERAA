<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="gestionCheval">
	<form action="FinancerCheval" method="post">
		<input type="hidden" name="idCheval" value="${cheval.idCheval}">
		<input type=number step=1 min=1 max=214748 name="montant"
			placeholder="Montant" id="montant" class="higher form-control" />
		<button name="act" value="Financer"
			class="btn btn-success col-md-10 col-md-offset-1 btnMarginTop">
			Participer <i class="glyphicon glyphicon-euro"></i>
		</button>
		<c:if test="${not empty erreur}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<span class="glyphicon glyphicon-exclamation-sign"
					aria-hidden="true"></span> <span class="sr-only">Error:</span>
				<c:out value="${erreur}" />
			</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<span class="glyphicon glyphicon-heart" aria-hidden="true"></span> <span
					class="sr-only">Success:</span>
				<c:out value="${message}" />
			</div>
		</c:if>
	</form>
</div>