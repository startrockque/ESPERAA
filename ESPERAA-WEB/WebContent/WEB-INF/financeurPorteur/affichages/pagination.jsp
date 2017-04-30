<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${not (nbPage == 1)}">
	<c:url value="Accueil" var="pagePrecedente"> 
		<c:param name="numeroPage" value="${numeroPage-1}"/>
	</c:url>
	<c:url value="Accueil" var="pageSuivante"> 
		<c:param name="numeroPage" value="${numeroPage+1}"/>
	</c:url>
	<div class="col-md-6 col-md-offset-3" id="pagination">
		<ul class="pagination">
			<c:if test="${numeroPage > 1}">
				<li><a href="<c:out value="${pagePrecedente}" />">&laquo;</a></li>
			</c:if>
			<c:forEach var="i" begin="1" end="${nbPage}" >
				<c:url value="Accueil" var="page"> 
					<c:param name="numeroPage" value="${i}"/>
				</c:url>
				<c:if test="${numeroPage == i}">
					<li><a href="<c:out value="${page}" />"><b><c:out value="${i}"/></b></a></li>
				</c:if>
				<c:if test="${not (numeroPage == i)}">
					<li><a href="<c:out value="${page}" />"><c:out value="${i}"/></a></li>
				</c:if>
			</c:forEach>
			<c:if test="${numeroPage < nbPage}">
				<li><a href="<c:out value="${pageSuivante}" />">&raquo;</a></li>
			</c:if>
		</ul>
	</div>
</c:if>