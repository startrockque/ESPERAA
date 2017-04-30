<!DOCTYPE html>
<html>
<head> 
<script type="text/javascript" src="../js/canvasjs.min.js"></script> 
<script type="text/javascript">
window.onload = function () {
	var chart = new CanvasJS.Chart("chartContainer", { 
		title:{
			text: "Catégories existantes"
		},
		exportFileName: "Graphique catégories",
		exportEnabled: true,
                animationEnabled: true,
		legend:{
			verticalAlign: "bottom",
			horizontalAlign: "center"
		},
		data: [
		{       
			type: "pie",
			showInLegend: false,
// 			toolTipContent: "<a href='Rechercher?categorie=categorie10&action=simpleCat'> {legendText}</a>: <strong>{y} projets</strong>",
			toolTipContent: "{legendText}: <strong>{y} projets</strong>",
			indexLabel: "{label}",
			dataPoints: []
		}
	]
	});

	populate();
	
	function populate () {
		var attribut = '${listeCategoriesDto}';
		var categories = JSON.parse(attribut);
		for(var i=0;i<categories.length;i++){
	        var obj = categories[i];
	        var titre = obj.titreCategorie;
	        var nbProjets = obj.nbProjets;
           	chart.options.data[0].dataPoints.push({  y: nbProjets, legendText: titre, label: titre});
	    }
		chart.render();

	};
}
</script>
</head>  
<body>  
	<fieldset>
		<div id="chartContainer" style="height: 80vh; width: 100%;"></div>
	</fieldset>  
</body>
</html>