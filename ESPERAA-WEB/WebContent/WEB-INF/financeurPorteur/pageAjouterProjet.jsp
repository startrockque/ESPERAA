<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Créer un projet</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
	<link href="../css/formProjet.css" rel="stylesheet" type="text/css">
	<link href="../css/inputFile.css" rel="stylesheet" type="text/css">
	<script src="../js/jquery.js"></script>
	
	<script type="text/javascript">
		$(document).on('change', '.btn-file :file', function() {
		  var input = $(this),
		      numFiles = input.get(0).files ? input.get(0).files.length : 1,
		      label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		  input.trigger('fileselect', [numFiles, label]);
		});

		$(document).ready( function() {
		    $('.btn-file :file').on('fileselect', function(event, numFiles, label) {
		        
		        var input = $(this).parents('.input-group').find(':text'),
		            log = numFiles > 1 ? numFiles + ' files selected' : label;
		        
		        if( input.length ) {
		            input.val(log);
		        } else {
		            if( log ) alert(log);
		        }
		        
		    });
		});
	</script>
</head>
<body>

	<c:import url="commun/bar_nav.jsp"/>
	
	<c:import url="formulaires/formAjouterProjet.jsp"/>
	
</body>
</html>