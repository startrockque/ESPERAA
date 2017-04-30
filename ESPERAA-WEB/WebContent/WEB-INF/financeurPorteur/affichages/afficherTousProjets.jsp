<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty listeProjets}">
	<div class="alert alert-danger" role="alert">
	  	<form action="Accueil" method="get">
		  	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  	<span class="sr-only">Error:</span>
	  		<i>Aucun projet trouv�</i>
			<input type="submit" value="Voir tous" class="btn btn-primary btn-primary"> 
		</form>
	</div>
</c:if>
<c:if test="${!empty listeProjets}">
	<c:forEach var="projet" items="${listeProjets}">
		<c:url value="/Membre/AfficherProjet" var="afficherProjet"> 
			<c:param name="idProjet" value="${projet.idProjet}"/>
		</c:url>
		<div class="col-sm-4 col-lg-4 col-md-4">
			<div class="thumbnail">
				<a href="<c:out value="${afficherProjet}"/>">
					<c:choose>
					    <c:when test="${empty projet.image}">
					        <img height="150" width="320" src="http://lookmag.look-voyages.fr/wp-content/photos/maldives.jpg" alt="">
					    </c:when>
					    <c:otherwise>
					        <img height="150" width="320" src="<c:url value="/images/${ projet.image }"/>" alt="">
					    </c:otherwise>
					</c:choose>
	   			</a>
	    		<div class="caption">
		    		<c:set var="financeurMembre" scope="page" value="${sessionScope.financeur}"/>
	    			<c:choose>	
		    			<c:when test="${ not financeurMembre.aimeProjet(projet) and financeurMembre.login ne projet.porteur and projet.enCours}">
							<form action="AimerProjet" method="post" class="pull-right">
								<input type="hidden" name="idProjet" value="${projet.idProjet}">
								<button name="act" value="J'aime" class="btn btn-primary btn-success"><i class="glyphicon glyphicon-thumbs-up"></i> <c:out value="${fn:length(projet.aimeList)}" /></button>
							</form>
						</c:when>
						<c:when test="${ financeurMembre.aimeProjet(projet) and financeurMembre.login ne projet.porteur and projet.enCours}">
							<form action="DesAimeProjet" method="post" class="pull-right">
								<input type="hidden" name="idProjet" value="${projet.idProjet}">
								<button name="act" value="J'aime plus" class="btn btn-primary btn-danger"><i class="glyphicon glyphicon-thumbs-down"></i> <c:out value="${fn:length(projet.aimeList)}" /></button>
							</form>
						</c:when>
						<c:otherwise>
							<div class="pull-right">							
								<i class="glyphicon glyphicon-thumbs-up"></i> <c:out value="${fn:length(projet.aimeList)}" />
							</div>
						</c:otherwise>
					</c:choose>	
					<c:forEach var="tag" items="${projet.tagList}">
						<c:url value="Rechercher" var="rechercherParTag"> 
							<c:param name="tagProjet" value="${tag}"/>
							<c:param name="action" value="simpleTag"/>
						</c:url>
						<a href="<c:out value="${rechercherParTag}" />"> #<c:out value="${tag}" /> </a> 				
					</c:forEach>		
					<h4><a href="<c:out value="${afficherProjet}" />"> <c:out value="${projet.titreProjet}"/></a></h4>
					<p><c:out value="${projet.butArgent}" /></p>						
				</div>
				<div class="ratings">
	    			<p class="pull-right">
	    				<c:choose>
	    					<c:when test="${ projet.enCours }">
	    						Fin : <c:out value="${projet.frenchDate}"/>
	    					</c:when>
	    					<c:otherwise>
	    						Termin�
	    					</c:otherwise>
	    				</c:choose>
	    				
	    			</p>
					<c:choose> 
   						<c:when test="${projet.montantInvesti*100/projet.montantDemande <= 100}">
	   						<p>Fonds : 	<fmt:formatNumber type="number" pattern="##.##" 
							value="${projet.montantInvesti}" /> E</p>
   							<div class="progress">
								<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${projet.montantInvesti}" 
									aria-valuemin="0" aria-valuemax="${projet.montantDemande}" style="width:${projet.montantInvesti*100/projet.montantDemande}%">
						   	 		<fmt:formatNumber type="number" pattern="##.##" value="${projet.montantInvesti*100/projet.montantDemande}" />%
							  	</div>
						  	</div>
				  		</c:when>
   						<c:when test="${projet.montantInvesti*100/projet.montantDemande > 100 and projet.montantInvesti*100/projet.montantDemande <= 9999999}">
							<p>Fonds : 	<fmt:formatNumber type="number" pattern="##.##" 
							value="${projet.montantInvesti}" /> E</p>
							<div class="progress">
								<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${projet.montantInvesti}" 
									aria-valuemin="0" aria-valuemax="${projet.montantDemande}" style="width:${100}%">
						   	 		<fmt:formatNumber type="number" pattern="##.##" value="${projet.montantInvesti*100/projet.montantDemande}" />%
							  	</div>
						  	</div>	
					  	</c:when>						  	
						<c:otherwise>
							<p>Fonds : > 9999999 E</p>
							<div class="progress">
								<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${projet.montantInvesti}" 
									aria-valuemin="0" aria-valuemax="${projet.montantDemande}" style="width:${100}%"> > 9999999%	
							  	</div>
						  	</div>						
						</c:otherwise>					  	
				  	</c:choose>
					
	        	</div>
			</div>
		</div>
	</c:forEach>
</c:if>
