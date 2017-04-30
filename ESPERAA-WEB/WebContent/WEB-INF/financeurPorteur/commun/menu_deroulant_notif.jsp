<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="../css/jquery.mCustomScrollbar.css" />
<script src="../js/jquery.mCustomScrollbar.concat.min.js"></script>

<c:url value="ViderNotifications" var="vider" />
<c:choose>
  	<c:when test="${not empty notifications}">	
		<li class="dropdown messages-menu">
			<c:set var="nbNotif" scope="page" value="${fn:length(notifications)}"/>
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-envelope"></i> <span class="badge badge-notify">${nbNotif}</span> Notifications<b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li class="header">Vous avez ${nbNotif} notifications</li>
				<li>
					<div id="notifs" data-mcs-theme="dark">
						<ul class="menu lightGreyBack">
							<c:forEach items="${notifications}" var="notification">
					        	<li>
									<div class="col-md-3 widther notification">
						    	    	<c:out value="${notification.notification}"/>
						        	</div>
					        	</li>
					       	</c:forEach>
						</ul>
					</div>
				</li>
				<li class="footer">
					<a href="<c:out value="${vider}" />">Vider</a>	
				</li>
		 	</ul>
		</li>
	</c:when> 
	<c:otherwise>
		<li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="glyphicon glyphicon-envelope"></i><span class="badge badge-notify">${nbNotif}</span> Notifications<b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li>
					<div class="col-md-3 widther">
		    	    	<i>Aucune notification</i>
		        	</div>
	        	</li>
		 	</ul>
		</li>
	</c:otherwise>
</c:choose>  
<script>
    (function($){
        $(window).load(function(){
            $("#notifs").mCustomScrollbar();
        });
    })(jQuery);
</script>