<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<p class="text-center">
	<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
	<Strong>Chevaux en vedette</Strong>
	<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
</p>
<c:if test="${empty listeEnAvant}">
	<i>Aucun cheval mis en avant</i>
</c:if>
<c:if test="${!empty listeEnAvant}">
   	<div class="row carousel-holder">
   		<div class="col-md-12">
        	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            	<ol class="carousel-indicators">
                	<c:set var="number" scope="page" value="0"/>
                   	<c:forEach var="cheval" items="${listeEnAvant}">
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
                   	<c:forEach var="cheval" items="${listeEnAvant}">
                      		<c:if test="${number==0}">
                        		<div class="item active">
                            		<c:url value="/Membre/AfficherCheval" var="afficherCheval"> 
						  				<c:param name="idCheval" value="${cheval.idCheval}"/>
						  			</c:url>
						  			<a href="<c:out value="${afficherCheval}"/>">
                            			<c:choose>
										    <c:when test="${empty cheval.image}">
										        <img class="slide-image" src="http://www.larepublique77.fr/files/2014/12/Unknown-1.jpg" alt="">
										    </c:when>
										    <c:otherwise>
										        <img src="<c:url value="/images/${ cheval.image }"/>" alt="">
										    </c:otherwise>
										</c:choose>
                            		</a>
                        		</div>
                        </c:if>
                       	<c:if test="${number!=0}">
                        		<div class="item">
                           			<c:url value="/Membre/AfficherCheval" var="afficherCheval"> 
					  					<c:param name="idCheval" value="${cheval.idCheval}"/>
					  				</c:url>
					  				<a href="<c:out value="${afficherCheval}"/>">
						  				<c:choose>
										    <c:when test="${empty cheval.image}">
										        <img class="slide-image" src="http://www.larepublique77.fr/files/2014/12/Unknown-1.jpg" alt="">
										    </c:when>
										    <c:otherwise>
										        <img src="<c:url value="/images/${ cheval.image }"/>" alt="">
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
