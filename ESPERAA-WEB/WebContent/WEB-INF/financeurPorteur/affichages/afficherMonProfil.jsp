<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset class="profilInfos">
	<legend>Mon profil</legend>
	<div>
		<div class="col-md-8 col-md-offset-2">
			<div class="box box-widget widget-user">
				<div class="widget-user-header bg-teal">
			    	<h3 class="widget-user-username"><c:out value="${donateur.login}"/></h3>
			    	<h5 class="widget-user-desc"><c:out value="${donateur.nom}"/></h5>
			    </div>
			    <div class="widget-user-image" id="imgContainer">
			    	<c:choose>
					    <c:when test="${empty donateur.image}">
					        <img class="img-circle" id="imgHigher" src="http://img.over-blog-kiwi.com/1/40/02/09/20150328/ob_0f8efc_frgreq.gif" alt="">
					    </c:when>
					    <c:otherwise>
					        <img class="img-circle" id="imgHigher" src="<c:url value="/images/${ donateur.image }"/>" alt="">
					    </c:otherwise>
					</c:choose>
			    </div>
			</div>
		</div>
	</div>
	<div class="col-md-12 text-center">
		<c:url value="/Membre/ModifierProfil" var="modifierProfil"/>
		<a href="<c:out value="${modifierProfil}"/>" class="new-account">Modifier mon profil</a>
	</div>
	
	<c:if test="${not empty investissements}">
		<fieldset class="col-md-10 col-md-offset-1">
			<h5>Liste de mes participations : </h5>
			<hr>
			<ul>
				<c:forEach var="inv" items="${investissements}"> 
					<li>
						<c:url value="/Membre/AfficherCheval" var="afficherCheval"> 
						<c:param name="idCheval" value="${inv.idCheval}"/>
						</c:url>
						Cheval : <a href="<c:out value="${afficherCheval}" />"> <c:out value="${inv.nomCheval}"/></a> 
						<br />
						Somme donnée : <c:out value="${inv.sommeInvestie}"/> E
						<br />
						Compensation : <c:out value="${inv.compensation}"/>
					</li>			
				</c:forEach>
			</ul>
		</fieldset>
	</c:if>
</fieldset>