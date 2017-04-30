<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset class="profilInfos">
	<legend>Mon profil</legend>
	<div>
		<div class="col-md-8 col-md-offset-2">
			<div class="box box-widget widget-user">
				<div class="widget-user-header bg-teal">
			    	<h3 class="widget-user-username"><c:out value="${financeur.login}"/></h3>
			    	<h5 class="widget-user-desc"><c:out value="${financeur.nom}"/></h5>
			    </div>
			    <div class="widget-user-image" id="imgContainer">
			    	<c:choose>
					    <c:when test="${empty financeur.image}">
					        <img class="img-circle" id="imgHigher" src="http://img.over-blog-kiwi.com/1/40/02/09/20150328/ob_0f8efc_frgreq.gif" alt="">
					    </c:when>
					    <c:otherwise>
					        <img class="img-circle" id="imgHigher" src="<c:url value="/images/${ financeur.image }"/>" alt="">
					    </c:otherwise>
					</c:choose>
			    </div>
			    <div class="box-footer">
			    	<div class="row">
			        	<div class="col-sm-6 border-right">
			            	<div class="description-block">
			                	<h5 class="description-header"><c:out value="${financeur.nbAime}" /></h5>
			                    <span class="description-text">LIKES</span>
			                </div>
			            </div>
			            <div class="col-sm-6">
			            	<div class="description-block">
			                	<h5 class="description-header"><c:out value="${nbProjets}" /></h5>
			                    <span class="description-text">PROJETS</span>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
		</div>
	</div>
	<div class="col-md-12 text-center">
		<c:url value="/Membre/ModifierProfil" var="modifierProfil"/>
		<a href="<c:out value="${modifierProfil}"/>" class="new-account">Modifier mon profil</a>
	</div>
	
	<fieldset class="col-md-10 col-md-offset-1">
		<p><strong>Mon portefeuille : </strong><c:out value="${financeur.montantAInvestir}"/></p>
		<hr>
		<form action="AlimenterPortefeuille" method="post" role="form" class="col-md-12">
			<label for="montant">Montant ра crediter  : </label><input type=number step=1 min=1 max=2147483647 name="montant" id="montant" class="form-control"/><br />
			<input type="submit" name="act" value="Valider" class="btn btn-primary col-md-10 col-md-offset-1"/>	
		</form>
	</fieldset>
		
	<c:if test="${not empty investissements}">
		<fieldset class="col-md-10 col-md-offset-1">
			<h5>Liste de mes investissements : </h5>
			<hr>
			<ul>
				<c:forEach var="inv" items="${investissements}"> 
					<li>
						<c:url value="/Membre/AfficherProjet" var="afficherProjet"> 
						<c:param name="idProjet" value="${inv.idProjet}"/>
						</c:url>
						Projet : <a href="<c:out value="${afficherProjet}" />"> <c:out value="${inv.titreProjet}"/></a> 
						<br />
						Somme investie : <c:out value="${inv.sommeInvestie}"/> E
						<br />
						Compensation : <c:out value="${inv.compensation}"/>
					</li>			
				</c:forEach>
			</ul>
		</fieldset>
	</c:if>
</fieldset>