<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fieldset class="searchForm">
	<legend>Rechercher un cheval</legend>  
	<form action="Rechercher" method="post" role="form">
	        <div class="checkbox">
		        <input id="sNom" type="checkbox" name="recherche" value="Nom" onclick="toggle('nomForm');" /><label for="sNom">Par nom</label> <br />
		        <input id="sCategorie" type="checkbox" name="recherche" value="Categorie" onclick="toggle('categorieForm');"/><label for="sCategorie">Par catégorie</label> <br />
	        </div>
	        
	        <div class="form-group" id="nomForm">
				<label for="nom">Nom :</label>
				<input list="noms" name="nomCheval" class="form-control">
					<datalist id="noms">
				    	<c:forEach items="${autoCompletionCheval}" var="che">
							<option value="${che}"></option>
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
		Hide("nomForm");
		Hide("categorieForm");
	};
</script>