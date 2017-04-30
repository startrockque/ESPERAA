<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	
	
<c:url value="/Admin/AfficherProfil" var="afficherProfil"> 
	<c:param name="loginFinanceur" value="${projet.porteur.login}"/>
</c:url>

<div class="thumbnail">
    <c:choose>
	    <c:when test="${empty projet.image}">
	        <img src="http://lookmag.look-voyages.fr/wp-content/photos/maldives.jpg" alt="">
	    </c:when>
	    <c:otherwise>
	        <img src="<c:url value="/images/${ projet.image }"/>" alt="">
	    </c:otherwise>
	</c:choose>
    <div class="caption-full">
		<h4 class="text-center"><c:out value="${projet.titreProjet}" /></h4>
		<h4 class="pull-right">Objectif : <c:out value="${projet.montantDemande}" /></h4>
    	<h4>Catégorie : <c:out value="${projet.categorie}"/></h4>
		<div class="detailsProjet">
			<p><strong>Description du projet : </strong></p>
		    <p class="col-md-offset-1"><c:out value="${projet.description}"/></p>
	        
	        <p><strong>But de l'argent :</strong></p>
		    <p class="col-md-offset-1"><c:out value="${projet.butArgent}"/></p>
		  	<p><strong>Tags :</strong></p>
		  	<c:forEach var="tag" items="${projet.tagList}">
				<c:url value="/Admin/Rechercher" var="rechercherParTag"> 
					<c:param name="tagProjet" value="${tag}"/>
					<c:param name="action" value="simpleTag"/>
				</c:url>
				<a href="<c:out value="${rechercherParTag}" />"> #<c:out value="${tag}" /> </a> 				
			</c:forEach>
			<p><strong>Likes : </strong><c:out value="${fn:length(projet.aimeList)}" /> </p>	
		</div>
    </div>
	<div class="ratings">
		<p class="pull-right">
			<c:choose>
				<c:when test="${ projet.enCours }">
					Fin : <c:out value="${projet.frenchDate}"/>
				</c:when>
				<c:otherwise>
					Terminé
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
				<c:when test="${projet.montantInvesti*100/projet.montantDemande > 100}">
					<p>Fonds : 	<fmt:formatNumber type="number" pattern="##.##" 
					value="${projet.montantInvesti}" /> E</p>
					<div class="progress">
						<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${projet.montantInvesti}" 
							aria-valuemin="0" aria-valuemax="${projet.montantDemande}" style="width:${100}%">
					  	 		<fmt:formatNumber type="number" pattern="##.##" value="${projet.montantInvesti*100/projet.montantDemande}" />%
					  	</div>
	 				</div>	
				</c:when>				  	
		</c:choose>
	</div>
</div> 