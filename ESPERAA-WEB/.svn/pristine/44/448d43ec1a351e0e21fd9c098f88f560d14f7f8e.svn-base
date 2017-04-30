<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset class="searchForm">
	<legend>Rechercher un projet</legend>  
	<form action="Rechercher" method="post" role="form">
	        <div class="checkbox">
		        <input id="sTitre" type="checkbox" name="recherche" value="Titre" onclick="toggle('titreForm');" /><label for="sTitre">Par titre</label> <br />
		        <input id="sCategorie" type="checkbox" name="recherche" value="Categorie" onclick="toggle('categorieForm');"/><label for="sCategorie">Par catégorie</label> <br />
		        <input id="sNom" type="checkbox" name="recherche" value="Nom" onclick="toggle('nomForm');"/><label for="sNom">Par nom du porteur</label> <br />
	        </div>
	        
	        <div class="form-group" id="titreForm">
				<label for="titre">Titre :</label>
				<input list="titres" name="titreProjet" class="form-control">
					<datalist id="titres">
				    	<c:forEach items="${autoCompletionProjet}" var="pro">
							<option value="${pro}"></option>
						</c:forEach>
				  	</datalist>
		  	</div>
		
			<div class="form-group" id="categorieForm">
				<label>Catégorie :</label>
				<select name="categorie" size="1" class="form-control">
					<option value="0">Aucune</OPTION>
					<c:forEach items="${listeCategories}" var="cat">
						<option value="${cat}">${cat}</option>
					</c:forEach>
				</select>
			</div>
				
			<div class="form-group" id="nomForm">
				<label for="nom">Nom du porteur :</label>
				<input list="porteurs" name="nomPorteur" class="form-control">
					<datalist id="porteurs">
				    	<c:forEach items="${listePorteurs}" var="por">
							<option value="${por}"></option>
						</c:forEach>
				  	</datalist>
		  	</div>
			<input type="submit" name="btnSearch" class="btn btn-info col-md-offset-4"/>
	</form>
</fieldset>
<script type="text/javascript">
	function Hide (addr) { document.getElementById(addr).style.visibility = "hidden";	}
	function Show (addr) { document.getElementById(addr).style.visibility = "visible";	}
	
	function toggle(anId)
	{
		if (document.getElementById(anId).style.visibility == "hidden")	{	Show(anId);	}
		else															{	Hide(anId);	}
	}
	
	window.onload = function () { 
		Hide("titreForm");
		Hide("categorieForm");
		Hide("nomForm");
	};
</script>