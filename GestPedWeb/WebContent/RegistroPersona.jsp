<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Gestor de Pedidos WEB</title>

    <!-- Bootstrap -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="assets/js/jquery-3.2.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<!--     Incluyo el JS propio del proyecto -->
    <script type="text/javascript" src="assets/js/gestped-web.js"></script>
	<!--     Incluyo js de criptografia -->
    <script type="text/javascript" src="assets/js/core.js"></script>  
	<script type="text/javascript" src="assets/js/md5.js"></script>  
 </head>
 <body>
 	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">Gestor de Pedidos WEB</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="#">Mis Datos</a></li>
	      <li><a href="Pedido.jsp">Pedidos</a></li>
	    </ul>
	  </div>
	</nav>

  	<div class="page-alerts">
	    <div id="alert_placeholder"></div>
	</div>

	<form class="form-horizontal" action="ServletIngresoPersona" id="ingrPersForm">
	<fieldset>
	
	<!-- Form Name -->
	<legend>Registro</legend>
	
	<!-- Text input [Usuario Email]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="emailReg">Usuario Email</label>  
	  <div class="col-md-4">
	  <input id="emailReg" name="emailReg" type="email" placeholder="Usuario Email" class="form-control input-md" required="">
	    
	  </div>
	</div>
	
	<!-- Password input [Password]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="passwdReg1">Password</label>
	  <div class="col-md-4">
	    <input id="passwdReg1" name="passwdReg1" type="password" placeholder="Password" class="form-control input-md" required="">
	    
	  </div>
	</div>
	
	<!-- Password input [Confirme Password]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="passwdReg2">Confirme Password</label>
	  <div class="col-md-4">
	    <input id="passwdReg2" name="passwdReg2" type="password" placeholder="Confirme Password" class="form-control input-md" required="">
	    
	  </div>
	</div>
	
	<hr>
	
	<!-- Select Basic [Pf - Pj]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="tipoCli">Tipo Cliente</label>
	  <div class="col-md-2">
	    <select id="tipoPers" name="tipoPers" class="form-control" onchange="deplegarTipoPers(this);">
	      <option value="1" selected>Persona</option>
	      <option value="2">Empresa</option>
	    </select>
	  </div>
	</div>
	
	<!-- Text input [Primer Nombre]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="nombre">Primer Nombre</label>  
	  <div class="col-md-4">
	  <input id="nombrePf1" name="nombrePf1" type="text" placeholder="Primer Nombre" class="form-control input-md" required="">
	    
	  </div>
	</div>
	<!-- Text input [Segundo Nombre]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="nombre">Segundo Nombre</label>  
	  <div class="col-md-4">
	  <input id="nombrePf2" name="nombrePf2" type="text" placeholder="Segundo Nombre" class="form-control input-md">
	    
	  </div>
	</div>
	
	<!-- Text input [Primer Apellido]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="apellido">Primer Apellido</label>  
	  <div class="col-md-4">
	  <input id="apellidoPf1" name="apellidoPf1" type="text" placeholder="Primer Apellido" class="form-control input-md" required="">
	    
	  </div>
	</div>
	<!-- Text input [Segundo Apellido]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="apellido">Segundo Apellido</label>  
	  <div class="col-md-4">
	  <input id="apellidoPf2" name="apellidoPf2" type="text" placeholder="Segundo Apellido" class="form-control input-md" required="">
	    
	  </div>
	</div>
	
	<!-- Text input [Email]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="mail1">Email</label>  
	  <div class="col-md-4">
	  <input id="mail2" name="mail2" type="email" placeholder="Email" class="form-control input-md" required="">
	    
	  </div>
	</div>
	
	<!-- Text input [Direccion]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="Direccion">Direccion</label>  
	  <div class="col-md-4">
	  <input id="direccion" name="direccion" type="text" placeholder="Direccion" class="form-control input-md">
	    
	  </div>
	</div>
	
	<!-- Text input [Puerta] y [Solar]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="Puerta">Puerta</label>  
	  <div class="col-md-1">
	  <input id="puerta" name="puerta" type="text" placeholder="Puerta" class="form-control input-md">
	    
	  </div>
	  <label class="col-md-1 control-label" for="Solar">Solar</label>  
	  <div class="col-md-1">
	  <input id="solar" name="solar" type="text" placeholder="Solar" class="form-control input-md">
	    
	  </div>
	</div>
	
	<!-- Text input [Manzana] y [Km]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="Manzana">Manzana</label>  
	  <div class="col-md-1">
	  <input id="manzana" name="manzana" type="text" placeholder="Manzana" class="form-control input-md">
	    
	  </div>
	  <label class="col-md-1 control-label" for="Km">Km</label>  
	  <div class="col-md-1">
	  <input id="km" name="km" type="text" placeholder="Km" class="form-control input-md">
	    
	  </div>
	</div>
	
	<!-- Text input [Telefono]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="Telefono">Telefono</label>  
	  <div class="col-md-4">
	  <input id="Telefono" name="Telefono" type="text" placeholder="Telefono" class="form-control input-md" required="">
	    
	  </div>
	</div>
	
	<!-- Text input [Celular]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="Celular">Celular</label>  
	  <div class="col-md-4">
	  <input id="Celular" name="Celular" type="text" placeholder="Celular" class="form-control input-md">
	    
	  </div>
	</div>
	
	<!-- Select Basic [Departamento]-->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="Departamento">Departamento</label>
	  <div class="col-md-4" id="div-sel-dep">
	    <select id="selDep" name="selDep" class="form-control" title="Seleccione departamento...">
	    </select>
	  </div>
	</div>
	
	<!-- Select Basic [Localidad] -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="Localidad">Localidad</label>
	  <div class="col-md-4" id="div-sel-loc">
	    <select id="selLoc" name="selLoc" class="form-control" title="Seleccione localidad...">
	    </select>
	  </div>
	</div>
	
	
	<!-- Button -->
	<div class="form-group">
	  <label class="col-md-4 control-label" for="registrar"></label>
	  <div class="col-md-4">
	    <button id="registrar" name="registrar" class="btn btn-primary">Registrar</button>
	    <button type="reset" class="btn btn-danger">Limpiar</button>
	  </div>
	</div>
	
<!-- 	<!-- Button --> -->
<!-- 	<div class="form-group"> -->
<!-- 	  <label class="col-md-4 control-label" for="limpiar"></label> -->
<!-- 	  <div class="col-md-4"> -->
<!-- 	  </div> -->
<!-- 	</div> -->
	
	</fieldset>
	</form>

</body>
  
<script>

	// document ready
	$(document).ready(function() {
	    $.ajax({
            type: "POST",
            url: "ServletObtDep",
            contentType: "application/json",              
            dataType: "json",
            success: function(response) {
                cargarCbxDep(response);
            }
	    });
	});
  
	$("#ingrPersForm").submit(function( event ) {
	    // Stop form from submitting normally
	    event.preventDefault();
	
	    // Get some values from elements on the page:
	    var $form = $(this);
	
	    // We want to customize what we post, therefore we format our data
	    var data = "emailReg="+ $('#emailReg').val() + 
				    "&passwdReg1=" + CryptoJS.MD5($('#passwdReg1').val()) +
				    "&passwdReg2=" + CryptoJS.MD5($('#passwdReg2').val()) +
				    "&nombrePf1=" + $('#nombrePf1').val() +
				    "&nombrePf2=" + $('#nombrePf2').val() +
				    "&apellidoPf1=" + $('#apellidoPf1').val() +
				    "&apellidoPf2=" + $('#apellidoPf2').val() +
				    "&direccion=" + $('#direccion').val() +
				    "&puerta=" + $('#puerta').val() +
				    "&solar=" + $('#solar').val() +
				    "&manzana=" + $('#manzana').val() +
				    "&km=" + $('#km').val() +
				    "&telefono=" + $('#telefono').val() +
				    "&celular=" + $('#celular').val() +
// 				    "&departamento=" + $('#departamento').val() +
				    "&localidad=" + $('#localidad').val();
				    
	
	    // For debugging purposes... see your console:
	    console.log(data);
	
	    // The actual from POST method
	    $.ajax({
	        type: $form.attr('method'),
	        url:  $form.attr('action'),
	        data: data,
	        success: function (data) {
	            console.log("Respuesta desde JAVA con la siguiente DATA: ");
	            console.log(data);
	            // redirecting
	            if(data === "SUCCESS") {
	              	window.location.replace('/GestPedWeb/success.jsp');
	            } else {
	              	window.location.replace('/GestPedWeb/error.jsp');
	            }
	        }
	    });
	});   

</script>
</html>