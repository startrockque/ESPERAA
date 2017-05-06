<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>	

<div class="thumbnail">
    <c:choose>
	    <c:when test="${empty cheval.image}">
	        <img src="http://www.larepublique77.fr/files/2014/12/Unknown-1.jpg" alt="">
	    </c:when>
	    <c:otherwise>
	        <img src="<c:url value="/images/${ cheval.image }"/>" alt="">
	    </c:otherwise>
	</c:choose>
    <div class="caption-full">
		<h4 class="text-center"><c:out value="${cheval.nomCheval}" /></h4>
		<h4 class="pull-right">Objectif : <c:out value="${cheval.montantDemande}" /></h4>
    	<h4>Catégorie : <c:out value="${cheval.categorie}"/></h4>
		<div class="detailsCheval">
			<p><strong>Déscription du cheval : </strong></p>
		    <p class="col-md-offset-1"><c:out value="${cheval.description}"/></p>
	        
	        <p><strong>But de l'argent :</strong></p>
		    <p class="col-md-offset-1"><c:out value="${cheval.butArgent}"/></p>
   	        <p><strong>Tags :</strong></p>
		  	<c:forEach var="tag" items="${cheval.tagList}">
				<c:url value="/Membre/Rechercher" var="rechercherParTag"> 
					<c:param name="tagCheval" value="${tag}"/>
					<c:param name="action" value="simpleTag"/>
				</c:url>
				<a href="<c:out value="${rechercherParTag}" />"> #<c:out value="${tag}" /> </a> 				
			</c:forEach>
		</div>
	  </div>
	    <div class="ratings">
  			<p class="pull-right">
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
				<c:when test="${cheval.montantInvesti*100/cheval.montantDemande > 100}">
					<p>Fonds : 	<fmt:formatNumber type="number" pattern="##.##" 
					value="${cheval.montantInvesti}" /> E</p>
					<div class="progress">
						<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${cheval.montantInvesti}" 
							aria-valuemin="0" aria-valuemax="${cheval.montantDemande}" style="width:${100}%">
					  	 		<fmt:formatNumber type="number" pattern="##.##" value="${cheval.montantInvesti*100/cheval.montantDemande}" />%
					  	</div>
	 				</div>	
				</c:when>						  				  	
			</c:choose>
   		</div>
</div> 