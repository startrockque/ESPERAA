<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<p class="lead">Tranches</p>
      	<div class="list-group">	
	<c:set var="cpt" value="0" scope="page"/>
	<c:forEach var="tranche" items="${projet.trancheList}">
		<c:if test="${cpt%2==0}">
			<p class = "list-group-item greyBack">
				Prix : <c:out value="${tranche.montantTranche}" /> <br/>	
				Compensation : <c:out value="${tranche.compensation}" />
			</p>
		</c:if>
		<c:if test="${cpt%2==1}">
			<p class = "list-group-item blueBack">
				Prix : <c:out value="${tranche.montantTranche}" /> <br/>	
				Compensation : <c:out value="${tranche.compensation}" />
			</p>
		</c:if>
		<c:set var="cpt" value="${cpt + 1}" scope="page"/>
	</c:forEach>
</div>