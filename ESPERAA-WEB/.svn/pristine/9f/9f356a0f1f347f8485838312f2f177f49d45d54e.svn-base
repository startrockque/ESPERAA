<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="gestionProjetAdmin">
	<form action="CloturerProjet" method="get">
		<input type="hidden" name="idProjet" value="${projet.idProjet}">
		<button name="act" value="Cloturer ce projet" class="btn btn-promary btn-danger col-md-12"><i class="glyphicon glyphicon-alert"></i> Cloturer ce projet <i class="glyphicon glyphicon-alert"></i></button>
	</form>
	<br />
	<c:if test="${not projet.enAvant and projet.enCours}">
		<form action="MettreEnAvant" method="post">
			<input type="hidden" name="idProjet" value="${projet.idProjet}">
			<button name="mea" value="Mettre en avant" class="btn btn-primary btn-success pull-right col-md-12"><i class="glyphicon glyphicon-star-empty"></i>Mettre en avant<i class="glyphicon glyphicon-star-empty"></i></button>
		</form>
	</c:if>
	<c:if test="${projet.enAvant and projet.enCours}">
		<form action="MettreEnAvant" method="post">
			<input type="hidden" name="idProjet" value="${projet.idProjet}">
			<button name="mea" value="Mettre en arriere" class="btn btn-primary btn-danger pull-right col-md-12"><i class="glyphicon glyphicon-star"></i>Mettre en arrière<i class="glyphicon glyphicon-star"></i></button>
		</form>
	</c:if>
</div>

<c:url value="/Admin/AfficherProfil" var="afficherProfil"> 
	<c:param name="loginFinanceur" value="${projet.porteur.login}"/>
</c:url>

<div class="box box-widget widget-user">
	<div class="widget-user-header bg-teal">
    	<h5 class="widget-user-username"><a id="whiteLink" href="<c:out value="${afficherProfil}" />"><c:out value="${projet.porteur.login}" /></a></h5>
    </div>
    <div class="widget-user-image">
    	<c:choose>
		    <c:when test="${empty projet.porteur.image}">
		        <img class="img-circle" id="imgHigher" src="http://img.over-blog-kiwi.com/1/40/02/09/20150328/ob_0f8efc_frgreq.gif" alt="">
		    </c:when>
		    <c:otherwise>
		        <img class="img-circle" id="imgHigher" src="<c:url value="/images/${ projet.porteur.image }"/>" alt="">
		    </c:otherwise>
		</c:choose>
    </div>
    <div class="box-footer">
    	<div class="row">
        	<div class="col-sm-6 border-right">
            	<div class="description-block">
                	<h5 class="description-header"><c:out value="${projet.porteur.nbAime}" /></h5>
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