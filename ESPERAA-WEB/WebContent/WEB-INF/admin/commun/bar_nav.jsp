<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/Admin/Accueil" var="accueil" />
<c:url value="/Admin/Categories" var="categories" />
<c:url value="/Admin/Dashboard" var="dashboard" />
<c:url value="/Deconnexion" var="deconnexion" />

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Cacher la barre</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="<c:out value="${accueil}" />"> <i
			class="glyphicon glyphicon-home"></i> Accueil
		</a>
	</div>
	<div class="collapse navbar-collapse">
		<ul class="nav navbar-nav">
			<li><a href="<c:out value="${categories}"/>"><i class="glyphicon glyphicon-list-alt"></i> Voir les catégories</a></li>
			<li><a href="<c:out value="${dashboard}"/>"><i class="glyphicon glyphicon-th-large"></i> Tableau de bord</a></li>
			<li class="dropdown widther"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="glyphicon glyphicon-search"></i>
					Recherche simple<b class="caret"></b></a>
				<ul class="dropdown-menu widther">
					<li>
						<form class="navbar-form col-md-3 widther" role="search"
							action="Rechercher" method="post" role="form">
							<div class="input-group">
								<input list="noms" name="nomCheval" class="form-control"
									placeholder="Nom du cheval">
								<datalist id="noms">
									<c:forEach items="${autoCompletionChevaux}" var="che">
										<option value="${che}"></option>
									</c:forEach>
								</datalist>
								<div class="input-group-btn">
									<button value="simpleTitre" name="action"
										class="higher btn btn-primary btn-primary" type="submit">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</form>
					</li>
					<li>
						<form class="navbar-form col-md-3 widther" role="search"
							action="Rechercher" method="post" role="form">
							<div class="input-group">
								<select name="categorie" size="1" class="form-control col-md-12">
									<option value="0">Aucune catégorie</OPTION>
									<c:forEach items="${listeCategories}" var="cat">
										<option value="${cat}">${cat}</option>
									</c:forEach>
								</select>
								<div class="input-group-btn">
									<button value="simpleCat" name="action"
										class="higher btn btn-primary btn-primary" type="submit">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</form>
					</li>
					<li>
						<form class="navbar-form col-md-3 widther" role="search"
							action="Rechercher" method="post">
							<div class="input-group">
								<input list="tags" type="text" name="tagCheval"
									class="form-control" placeholder="Recherche par tag">
								<datalist id="tags">
									<c:forEach items="${listeTag}" var="tag">
										<option value="${tag}"></option>
									</c:forEach>
								</datalist>
								<div class="input-group-btn">
									<button value="simpleTag" name="action"
										class="higher btn btn-primary btn-primary" type="submit">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>
							</div>
						</form>
					</li>
				</ul>
		</ul>
		<a href="<c:out value="${deconnexion}"/>"
			class="btnDeco btn btn-primary btn-danger"><i
			class="glyphicon glyphicon-off"></i> Deconnexion</a>
	</div>
</div>

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="../css/bootstrap/js/bootstrap.min.js"></script>