<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="well">
    <div class="text-right">
        <form action=EnvoyerMessage method="post">
       		<input type="hidden" name="idProjet" value="${projet.idProjet}" /> 
			<textarea name="contenuMessage" rows="3" placeholder="Entrez votre message ..." class="form-control lightMarginBottom" required></textarea>
			<button name="act" value="Envoyer" class="btn btn-success"><i class="glyphicon glyphicon-send"></i> Envoyer</button>	
		</form>
    </div>
    <hr>
	<c:forEach var="conversation" items="${projet.conversationList}">
		<c:set var="compteurMessage" scope="page" value="${0}"/>
		<div class="box box-primary">
        	<div class="box-header with-border">
        		<h4>
	        		<c:if test="${conversation.emetteur == sessionScope.admin.login }">
						Vous
					</c:if>
					<c:if test="${conversation.emetteur != sessionScope.admin.login }">
						<c:url value="/Admin/AfficherProfil" var="afficherProfil"> 
							<c:param name="loginFinanceur" value="${conversation.emetteur}"/>
						</c:url>
						<a href="<c:out value="${afficherProfil}" />"> <c:out value="${conversation.emetteur}" /> </a>
					</c:if>
				</h4>
            	<div class="box-tools pull-right">
		        	<form action="SupprimerMessage" method="post" id="formSupprimer">
						<input type="hidden" name="idConversation" value="${conversation.idConversation}" />	
						<input type="hidden" name="idMessage" value="${conversation.messageList[0].idMessage}" />
						<input type="hidden" name="idProjet" value="${projet.idProjet}" />	
						<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
						<button name="act" value="Supprimer" class="btn btn-box-tool" ><i class="fa fa-times"></i></button>
					</form>
            	</div>
          	</div>
         	<div class="box-body">
				<c:forEach var="message" items="${conversation.messageList}">
					<div class="row">
	   					<div class="col-md-12">
	       					<c:if test="${compteurMessage == 0}">
   								<div class="box box-success">
   							</c:if>
   							<c:if test="${compteurMessage != 0}">
	   							<div class="box box-danger reponseBox">
	   						</c:if>	
				        		<div class="box-header with-border">
				        			<c:if test="${message.emetteur == sessionScope.admin.login }">
				        			<div class="widget-user-image">
								    	<c:choose>
										    <c:when test="${empty financeur.image}">
										        <img class="img-circle image-message" src="http://img.over-blog-kiwi.com/1/40/02/09/20150328/ob_0f8efc_frgreq.gif" alt="">
										    </c:when>
										    <c:otherwise>
										        <img class="img-circle image-message" src="<c:url value="/images/${ financeur.image }"/>" alt="">
										    </c:otherwise>
										</c:choose>
										<strong>Vous</strong>, à : <c:out value="${message.dateHeureMessage}"/>
									</div>
									</c:if>
									<c:if test="${message.emetteur != sessionScope.admin.login }">
										<c:url value="/Admin/AfficherProfil" var="afficherProfil"> 
											<c:param name="loginFinanceur" value="${message.emetteur}"/>
										</c:url>
										<div class="widget-user-image">
								    	<c:choose>
										    <c:when test="${empty financeur.image}">
										        <img class="img-circle image-message" src="http://img.over-blog-kiwi.com/1/40/02/09/20150328/ob_0f8efc_frgreq.gif" alt="">
										    </c:when>
										    <c:otherwise>
										        <img class="img-circle image-message" src="<c:url value="/images/${ financeur.image }"/>" alt="">
										    </c:otherwise>
										</c:choose>
										<a href="<c:out value="${afficherProfil}" />"> <c:out value="${message.emetteur}" /> </a>, à : <c:out value="${message.dateHeureMessage}"/>
										</div>
									</c:if>
				    	        	<div class="box-tools pull-right">
					    	    		<form action="SupprimerMessage" method="post" id="formSupprimer">
											<input type="hidden" name="idConversation" value="${conversation.idConversation}" />	
											<input type="hidden" name="idMessage" value="${message.idMessage}" />
											<input type="hidden" name="idProjet" value="${projet.idProjet}" />	
											<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
											<button name="act" value="Supprimer" class="btn btn-box-tool" ><i class="fa fa-times"></i></button>
										</form>
				            		</div>
				          		</div>
					    		<div class="box-body">
							    	<pre><c:out value="${message.message}" /></pre>
								</div>	
								<c:set var="compteurMessage" scope="page" value="${compteurMessage + 1}"/>
							</div>
						</div>
					</div>
	    		</c:forEach>
	    		<br/>
	    		<form action="RepondreMessage" method="post">
					<label for="rep">Répondre à ce message  : </label><textarea name="contenuReponse" id="rep" placeholder="Entrez votre message ..." class="form-control lightMarginBottom" required></textarea>
					<input type="hidden" name="idConversation" value="${conversation.idConversation}" />	
					<input type="hidden" name="idProjet" value="${projet.idProjet}" />
					<button name="act" value="Repondre" class="btn btn-primary btn-success"><i class="glyphicon glyphicon-share-alt"></i> Répondre</button>	
				</form>
    		</div>
    	</div>
   		<c:set var="compteurMessage" scope="page" value="${0}"/>
	</c:forEach>
</div>