<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty listeChevaux}">
	<div class="alert alert-danger" role="alert">
	  	<form action="Accueil" method="get">
		  	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  	<span class="sr-only">Error:</span>
	  		<i>Aucun cheval trouvé</i>
			<input type="submit" value="Voir tous" class="btn btn-primary btn-primary"> 
		</form>
	</div>
</c:if>
<c:if test="${!empty listeChevaux}">
	<c:forEach var="cheval" items="${listeChevaux}">
		<c:url value="/Membre/AfficherCheval" var="afficherCheval"> 
			<c:param name="idCheval" value="${cheval.idCheval}"/>
		</c:url>
		<div class="col-sm-4 col-lg-4 col-md-4">
			<div class="thumbnail">
				<a href="<c:out value="${afficherCheval}"/>">
					<c:choose>
					    <c:when test="${empty cheval.image}">
					        <img height="150" width="320" src="http://www.larepublique77.fr/files/2014/12/Unknown-1.jpg" alt="">
					    </c:when>
					    <c:otherwise>
					        <img height="150" width="320" src="<c:url value="/images/${ cheval.image }"/>" alt="">
					    </c:otherwise>
					</c:choose>
	   			</a>
	    		<div class="caption">
					<c:forEach var="tag" items="${cheval.tagList}">
						<c:url value="Rechercher" var="rechercherParTag"> 
							<c:param name="tagCheval" value="${tag}"/>
							<c:param name="action" value="simpleTag"/>
						</c:url>
						<a href="<c:out value="${rechercherParTag}" />"> #<c:out value="${tag}" /> </a> 				
					</c:forEach>		
					<h4><a href="<c:out value="${afficherCheval}" />"> <c:out value="${cheval.nomCheval}"/></a></h4>
					<p><c:out value="${cheval.butArgent}" /></p>						
				</div>
				<div class="ratings">
					<c:choose> 
   						<c:when test="${cheval.montantInvesti*100/cheval.montantDemande <= 100}">
	   						<p>Fonds : 	<fmt:formatNumber type="number" pattern="##.##" 
							value="${cheval.montantInvesti}" /> E</p>
   							<div class="progress">
								<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${cheval.montantInvesti}" 
									aria-valuemin="0" aria-valuemax="${cheval.montantDemande}" style="width:${cheval.montantInvesti*100/cheval.montantDemande}%">
						   	 		<fmt:formatNumber type="number" pattern="##.##" value="${cheval.montantInvesti*100/cheval.montantDemande}" />%
							  	</div>
						  	</div>
				  		</c:when>
   						<c:when test="${cheval.montantInvesti*100/cheval.montantDemande > 100 and cheval.montantInvesti*100/cheval.montantDemande <= 9999999}">
							<p>Fonds : 	<fmt:formatNumber type="number" pattern="##.##" 
							value="${cheval.montantInvesti}" /> E</p>
							<div class="progress">
								<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${cheval.montantInvesti}" 
									aria-valuemin="0" aria-valuemax="${cheval.montantDemande}" style="width:${100}%">
						   	 		<fmt:formatNumber type="number" pattern="##.##" value="${cheval.montantInvesti*100/cheval.montantDemande}" />%
							  	</div>
						  	</div>	
					  	</c:when>						  	
						<c:otherwise>
							<p>Fonds : > 9999999 E</p>
							<div class="progress">
								<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${cheval.montantInvesti}" 
									aria-valuemin="0" aria-valuemax="${cheval.montantDemande}" style="width:${100}%"> > 9999999%	
							  	</div>
						  	</div>						
						</c:otherwise>					  	
				  	</c:choose>
					
	        	</div>
			</div>
		</div>
	</c:forEach>
</c:if>
