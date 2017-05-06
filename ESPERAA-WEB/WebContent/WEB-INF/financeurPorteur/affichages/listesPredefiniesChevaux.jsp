<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fieldset class="searchForm">
	<legend>Listes Prédéfinies</legend>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#home">Tags</a></li>
		<li><a data-toggle="tab" href="#menu1">Catégories</a></li>
		<li><a data-toggle="tab" href="#menu2">Listes</a></li>
	</ul>

	<div class="tab-content">
		<div id="home" class="tab-pane fade in active">
			<!-- 		TAG -->
			<c:forEach var="tag" items="${tagsPop}">
				<c:url value="/Membre/Rechercher" var="rechercherParTag">
					<c:param name="tagCheval" value="${tag}" />
					<c:param name="action" value="simpleTag" />
				</c:url>
				<a href="<c:out value="${rechercherParTag}" />"> #<c:out
						value="${tag}" />
				</a>
				<br />
			</c:forEach>
		</div>
		<div id="menu1" class="tab-pane fade">
			<!-- 		CATEGORIES -->
			<c:forEach var="categoriePop" items="${categoriesPop}">
				<c:url value="/Membre/Rechercher" var="rechercherParCategorie">
					<c:param name="categorie" value="${categoriePop}" />
					<c:param name="action" value="simpleCat" />
				</c:url>
				<a href="<c:out value="${rechercherParCategorie}" />"> <c:out
						value="${categoriePop}" />
				</a>
				<br />
			</c:forEach>
		</div>
		<div id="menu2" class="tab-pane fade">
			<c:url value="/Membre/Rechercher" var="rechercherPlusFinances">
				<c:param name="action" value="chevauxPlusFinances" />
			</c:url>
			<a href="<c:out value="${rechercherPlusFinances}" />"> Chevaux
				les plus financés </a><br />

			<c:url value="/Membre/Rechercher" var="rechercherPresqueFinances">
				<c:param name="action" value="chevauxPresqueFinances" />
			</c:url>

			<a href="<c:out value="${rechercherPresqueFinances}" />"> Chevaux
				presque finis de financer </a><br />

			<c:url value="/Membre/Rechercher" var="rechercherRecents">
				<c:param name="action" value="chevauxRecents" />
			</c:url>
			<a href="<c:out value="${rechercherRecents}" />"> Chevaux récents
			</a><br />
		</div>
	</div>
</fieldset>