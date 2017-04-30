<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${projet.porteur.login != sessionScope.financeur.login and projet.enCours}">		
	<div class="gestionProjet">
		<form action="FinancerProjet" method="post">
			<input type="hidden" name="idProjet" value="${projet.idProjet}">
			<input type=number step=1 min=1 max=214748 name="montant" placeholder="Montant" id="montant" class="higher form-control"/>
			<button name="act" value="Financer" class="btn btn-success col-md-10 col-md-offset-1 btnMarginTop">Participer <i class="glyphicon glyphicon-euro"></i></button>
		<c:if test="${not empty erreur}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			  	<span class="sr-only">Error:</span>
			  	<c:out value="${erreur}" />
			</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
			  	<span class="sr-only">Success:</span>
			  	<c:out value="${message}" />
			</div>
		</c:if>
		</form>			
	</div>
</c:if>

<c:if test="${projet.porteur.login == sessionScope.financeur.login}">	
	<div class="gestionProjet">
		<c:if test="${projet.porteur.login == sessionScope.financeur.login}">
			<form action="ModifProjet" method="get">
				<input type="hidden" name="idProjet" value="${projet.idProjet}">
				<button name="act" value="Modifier" class="btn btn-promary btn-warning col-md-12"><i class="glyphicon glyphicon-wrench"></i> Modifier</button>
			</form>
		</c:if>
		<br/>
		<form action="AbandonnerProjet" method="get">
			<input type="hidden" name="idProjet" value="${projet.idProjet}">
			<button name="act" value="Abandonner" class="btn btn-primary btn-danger col-md-12"><i class="glyphicon glyphicon-alert"></i> Abandonner </button>
		</form>
	</div>
</c:if>


<c:url value="/Membre/AfficherProfil" var="afficherProfil"> 
	<c:param name="loginFinanceur" value="${projet.porteur.login}"/>
</c:url>

<div class="box box-widget widget-user">
	<div class="widget-user-header bg-teal">
    	<h5 class="widget-user-username"><a id="whiteLink" href="<c:out value="${afficherProfil}" />"><c:out value="${projet.porteur.login}" /></a></h5>
    </div>
    <div class="widget-user-image">
    	<c:choose>
		    <c:when test="${empty financeur.image}">
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