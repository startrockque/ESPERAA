<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset class="profilInfos">
	<legend>Son profil</legend>
	<div class="col-md-8 col-md-offset-2">
		<div class="box box-widget widget-user">
			<div class="widget-user-header bg-teal">
		    	<h3 class="widget-user-username"><c:out value="${donateur.login}"/></h3>
		    	<h5 class="widget-user-desc"><c:out value="${donateur.email}"/></h5>
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
</fieldset>