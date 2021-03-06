<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modifier mon profil</title>
	<link href="../css/style.css" rel="stylesheet" type="text/css">
	<link href="../css/profil.css" rel="stylesheet" type="text/css">
	<link href="../css/inputFile.css" rel="stylesheet" type="text/css">
	<script src="../css/bootstrap/js/bootstrap.min.js"></script>
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
	<div id="body">
		<c:import url="formulaires/formModifierProfil.jsp"/>
	</div>
</body>
</html>