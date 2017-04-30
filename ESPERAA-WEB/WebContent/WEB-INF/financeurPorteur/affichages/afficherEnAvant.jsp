<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<p class="text-center">
	<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
	<Strong>Projets en vedette</Strong>
	<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
</p>
<c:if test="${empty listeEnAvant}">
	<i>Aucun projet mis en avant</i>
</c:if>
<c:if test="${!empty listeEnAvant}">
   	<div class="row carousel-holder">
   		<div class="col-md-12">
        	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            	<ol class="carousel-indicators">
                	<c:set var="number" scope="page" value="0"/>
                   	<c:forEach var="projet" items="${listeEnAvant}">
                   		<c:if test="${number==0}">
                   			<li data-target="#carousel-example-generic" data-slide-to="${number}" class="active"></li>
                   		</c:if>
                   		<c:if test="${number!=0}">
                       		<li data-target="#carousel-example-generic" data-slide-to="${number}"></li>
                      		</c:if>
                       	<c:set var="number" value="${number + 1}" scope="page"/>
                    </c:forEach>
                </ol>
                <div class="carousel-inner">
                	<c:set var="number" value="0" scope="page"/>
                   	<c:forEach var="projet" items="${listeEnAvant}">
                      		<c:if test="${number==0}">
                        		<div class="item active">
                            		<c:url value="/Membre/AfficherProjet" var="afficherProjet"> 
						  				<c:param name="idProjet" value="${projet.idProjet}"/>
						  			</c:url>
						  			<a href="<c:out value="${afficherProjet}"/>">
                            			<c:choose>
										    <c:when test="${empty projet.image}">
										        <img class="slide-image" src="http://lookmag.look-voyages.fr/wp-content/photos/maldives.jpg" alt="">
										    </c:when>
										    <c:otherwise>
										        <img src="<c:url value="/images/${ projet.image }"/>" alt="">
										    </c:otherwise>
										</c:choose>
                            		</a>
                        		</div>
                        </c:if>
                       	<c:if test="${number!=0}">
                        		<div class="item">
                           			<c:url value="/Membre/AfficherProjet" var="afficherProjet"> 
					  					<c:param name="idProjet" value="${projet.idProjet}"/>
					  				</c:url>
					  				<a href="<c:out value="${afficherProjet}"/>">
						  				<c:choose>
										    <c:when test="${empty projet.image}">
										        <img class="slide-image" src="http://lookmag.look-voyages.fr/wp-content/photos/maldives.jpg" alt="">
										    </c:when>
										    <c:otherwise>
										        <img src="<c:url value="/images/${ projet.image }"/>" alt="">
										    </c:otherwise>
										</c:choose>
                         			</a>
                        		</div>
                       	</c:if>
                       	<c:set var="number" value="${number + 1}" scope="page"/>
                      	</c:forEach>
                   </div>
                   <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                       <span class="glyphicon glyphicon-chevron-left"></span>
                   </a>
                   <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                       <span class="glyphicon glyphicon-chevron-right"></span>
                   </a>
               </div>
           </div>
       </div>
</c:if>
