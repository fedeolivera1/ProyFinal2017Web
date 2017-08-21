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
    <link href="bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="assets/css/gestped-web.css" rel="stylesheet">

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
	<!-- DateTimePicker -->
    <script type="text/javascript" src="bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap-datetimepicker.es.js"></script>
	<!--     Validator -->
    <script type="text/javascript" src="bootstrap/js/validator.js"></script>
	
 </head>
 <body>
 	<!-- NAVBAR -->
 	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand">Gestor de Pedidos WEB</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class="active"><a href="#">Mis Datos</a></li>
	      <li><a href="pedido.jsp">Pedidos</a></li>
<%
		if(session.getAttribute("usuario") != null) {
			String sessionId = session.getId();
%>
			<li><a>Usuario:&nbsp;${sessionScope.usuario}</a></li>
	      	<li><a href="ServletLogout?logout=<%=sessionId%>"><font color="red">Logout</font></a></li>
<%
      	} 
%>
	    </ul>
	  </div>
	</nav>
	<!-- ALERTS -->
  	<div class="page-alerts">
	    <div id="alert_placeholder"></div>
	</div>
	
	<form class="form-horizontal" action="ServletPersona" method="POST" id="ingrPersForm" data-toggle="validator">
		<fieldset>
		
		<!-- Form Name -->
		<legend>Registro</legend>
		
		<!-- Text input [Usuario Email]-->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="emailReg">Usuario Email</label>  
		  <div class="col-md-4">
		  <input id="emailReg" name="emailReg" type="email" placeholder="Usuario Email" class="form-control input-md" required>
		  <div class="help-block with-errors"></div>
		  </div>
		</div>
		
		<!-- Password input [Password]-->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="passwdReg1">Password</label>
		  <div class="col-md-4">
		    <input id="passwdReg1" name="passwdReg1" type="password" placeholder="Password" class="form-control input-md" required>
		    <div id="divPw1" class="oculto"><label><font color="gray" size="1">Ingrese contraseña solamente si quiere cambiar</font></label></div>
		    <div class="help-block with-errors"></div>
		  </div>
		</div>
		
		<!-- Password input [Confirme Password]-->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="passwdReg2">Confirme Password</label>
		  <div class="col-md-4">
		    <input id="passwdReg2" name="passwdReg2" type="password" placeholder="Confirme Password" class="form-control input-md" 
		    		data-match="#passwdReg1" data-match-error="Oops! Las passwords no coinciden">
		    <div id="divPw2" class="oculto"><label><font color="gray" size="1">Confirme contraseña solamente si quiere cambiar</font></label></div>
    		<div class="help-block with-errors"></div>
		  </div>
		</div>
		
		<!-- Select Basic [Pf - Pj]-->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="tipoCli">Tipo Cliente</label>
		  <div class="col-md-2">
		    <select id="tipoPers" name="tipoPers" class="form-control">
		      <option value="F" selected>Cliente Persona</option>
		      <option value="J">Cliente Empresa</option>
		    </select>
		  </div>
		</div>

		<!-- 	DIV PERSONA FISICA -->
		<div id="divPf" class="oculto">
		
			<!-- Select Basic [TipoDoc] -->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="TipoDoc">Tipo Documento</label>
			  <div class="col-md-2" id="div-sel-loc">
			    <select id="selTipoDoc" name="selTipoDoc" class="form-control" title="Tipo Documento...">
			    </select>
			  </div>
			</div>
			<!-- Text input [Documento]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="nombre">Documento</label>  
			  <div class="col-md-4">
			  <input id="documento" name="documento" type="text" pattern="[0-9]{1,}" placeholder="Documento" class="form-control input-md" required>
			  <div class="help-block with-errors"></div>
			  </div>
			</div>
			<!-- Text input [Primer Nombre]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="nombre">Primer Nombre</label>  
			  <div class="col-md-4">
			  <input id="nombrePf1" name="nombrePf1" type="text" placeholder="Primer Nombre" class="form-control input-md" required>
			  <div class="help-block with-errors"></div>
			  </div>
			</div>
			<!-- Text input [Segundo Nombre]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="nombre">Segundo Nombre</label>  
			  <div class="col-md-4">
			  <input id="nombrePf2" name="nombrePf2" type="text" placeholder="Segundo Nombre" class="form-control input-md">
			  </div>
			</div>
			
			<!-- Text input [Primer Apellido]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="apellido">Primer Apellido</label>  
			  <div class="col-md-4">
			  <input id="apellidoPf1" name="apellidoPf1" type="text" placeholder="Primer Apellido" class="form-control input-md" required>
			  <div class="help-block with-errors"></div>
			  </div>
			</div>
			<!-- Text input [Segundo Apellido]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="apellido">Segundo Apellido</label>  
			  <div class="col-md-4">
			  <input id="apellidoPf2" name="apellidoPf2" type="text" placeholder="Segundo Apellido" class="form-control input-md">
			  </div>
			</div>
			<!-- Input text y Datetimepicker [Fecha Nac]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="FNac">Fecha Nacimiento</label>
			  <div class="col-md-4">
				<div class="col-md-5 input-group date" id="dtpFnac">
		            <input type="text" id="fNac" name="fNac" class="form-control" required/>
		            <span class="input-group-addon">
		                <span class="glyphicon glyphicon-calendar"></span>
		            </span>
	        	</div>
				<div class="help-block with-errors"></div>
			  </div>  
			</div>
			<!-- Text input [Sexo]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="sexo">Sexo</label>  
			  <div class="col-md-2">
			    <select id="sexo" name="sexo" class="form-control">
			      <option value="M" selected>Masculino</option>
			      <option value="F">Femenino</option>
			    </select>
			  </div>
			</div>
		
		</div>
		<!-- 	END DIV PF -->
	
		<!-- 	DIV PERSONA JURIDICA -->
		<div id="divPj" class="oculto">
		
			<!-- Text input [Rut]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="rut">Rut</label>  
			  <div class="col-md-4">
			  <input id="rut" name="rut" type="text" pattern="[0-9]{1,}" placeholder="Rut" class="form-control input-md" >
			  <div class="help-block with-errors"></div>
			  </div>
			</div>
			<!-- Text input [Nombre]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="nombre">Nombre</label>  
			  <div class="col-md-4">
			  <input id="nombrePj" name="nombrePj" type="text" placeholder="Nombre" class="form-control input-md" >
			  </div>
			</div>
			<!-- Text input [Razon Social]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="nombre">Razon Social</label>  
			  <div class="col-md-4">
			  <input id="razonSoc" name="razonSoc" type="text" placeholder="Razon Social" class="form-control input-md">
			  </div>
			</div>
			<!-- Text input [Bps]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="nombre">Bps</label>  
			  <div class="col-md-4">
			  <input id="bps" name="bps" type="text" placeholder="Bps" class="form-control input-md">
			  </div>
			</div>
			<!-- Text input [Bse]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="nombre">Bse</label>  
			  <div class="col-md-4">
			  <input id="bse" name="bse" type="text" placeholder="Bse" class="form-control input-md">
			  </div>
			</div>
			<!-- Check input [prov]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="nombre">Empresa proveedora</label>  
			  <div class="col-md-4">
				  <div class="btn-group" data-toggle="buttons">
				  	<label id="lblChkProv" class="btn btn-default">
						<input type="checkbox" id="esProv" name="esProv" autocomplete="off">
						<span class="glyphicon glyphicon-ok"></span>
					</label>
				  </div>
		  		</div>
			</div>
			
		</div>
		<!-- 	END DIV PJ -->

		<!-- Text input [Direccion]-->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="Direccion">Direccion</label>  
		  	<div class="col-md-4">
			  	<input id="direccion" name="direccion" type="text" placeholder="Direccion" class="form-control input-md" required>
		    	<div class="help-block with-errors"></div>
		  	</div>
		</div>
		
		<!-- Text input [Puerta] y [Solar]-->
		<div class="form-group col-md-12">
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
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="Manzana">Manzana</label>  
		  <div class="col-md-1">
		  	<input id="manzana" name="manzana" type="text" placeholder="Manzana" class="form-control input-md">
		  </div>
		  <label class="col-md-1 control-label" for="Km">Km</label>  
		  <div class="col-md-1">
		  	<input id="km" name="km" type="text" placeholder="Km" class="form-control input-md">
		  </div>
		</div>
		
		<!-- Text input [Complemento]-->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="Complemento">Complemento</label>  
		  <div class="col-md-4">
		  <input id="comp" name="comp" type="text" placeholder="Complemento" class="form-control input-md">
		  </div>
		</div>
		
		<!-- Text input [Telefono]-->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="Telefono">Telefono</label>  
		  <div class="col-md-4">
		  	<input id="telefono" name="telefono" type="text" placeholder="Telefono" class="form-control input-md">
		  </div>
		</div>
		
		<!-- Text input [Celular]-->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="Celular">Celular</label>  
		  <div class="col-md-4">
		  <input id="celular" name="celular" type="text" placeholder="Celular" class="form-control input-md">
		  </div>
		</div>
		
		<!-- Select Basic [Departamento]-->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="Departamento">Departamento</label>
		  <div class="col-md-4" id="div-sel-dep">
		    <select id="selDep" name="selDep" class="form-control" title="Seleccione departamento..." onchange="cargarCbxLoc();">
		    </select>
		  </div>
		</div>
		
		<!-- Select Basic [Localidad] -->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="Localidad">Localidad</label>
		  <div class="col-md-4" id="div-sel-loc">
		    <select id="selLoc" name="selLoc" class="form-control" title="Seleccione localidad..." required>
		    </select>
		    <div class="help-block with-errors"></div>
		  </div>
		</div>
		
		<!-- Buttons -->
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="registrar"></label>
		  <div class="col-md-4">
		    <button type="submit" id="registrar" name="registrar" class="btn btn-primary">Registrar</button>
		    <button type="reset" id="limpiar" class="btn btn-danger">Limpiar</button>
		  </div>
		</div>
	
	</fieldset>
	</form>
</body>
  
<script type="text/javascript">
	$('#dtpFnac').datetimepicker({
		format: 'dd/mm/yyyy',
        minView: 2,
        maxView: 4,
        autoclose: true,
        language: 'es'
	});

	// document ready
	$(document).ready(function() {
	    $.ajax({
            type: "POST",
            url: "ServletObtTipoDoc",
            contentType: "application/json",              
            dataType: "json",
            success: function(response) {
                cargarCbxTd(response);
            }, error: function (response) {
            	bootstrap_alert.danger(response.responseText);
            }
	    });
	    $.ajax({
            type: "POST",
            url: "ServletObtDep",
            contentType: "application/json",              
            dataType: "json",
            success: function(response) {
				cargarCbxDep(response);
            }, error: function (response) {
            	bootstrap_alert.danger(response.responseText);
            }
	    });
	    seleccionTipoPers();
		cargarDatosPers();
	});

	//submit prevent default
	$('#ingrPersForm').validator().on('submit', function (e) {
		if (e.isDefaultPrevented()) {
  			// manejo form inválido (opc)
		} else {
			e.preventDefault();
			var $form = $(this);
			var tipoPers = $('#tipoPers').val();
			var pwd = $('#passwdReg1').val();
			var dataUsr = "emailReg="+ $('#emailReg').val();
			if(pwd === '') {
				dataUsr += "&passwdReg1=";
			} else {
			   	dataUsr += "&passwdReg1=" + CryptoJS.MD5(pwd);
			}
		   	dataUsr += "&tipoPers=" + tipoPers;
			var dataPf = "";
			var dataPj = "";
			//pers
			var dataPers = "&direccion=" + $('#direccion').val() +
			   "&puerta=" + $('#puerta').val() +
			   "&solar=" + $('#solar').val() +
			   "&manzana=" + $('#manzana').val() +
			   "&km=" + $('#km').val() +
			   "&comp=" + $('#comp').val() +
			   "&telefono=" + $('#telefono').val() +
			   "&celular=" + $('#celular').val() +
			   "&localidad=" + $('#selLoc').val();
			   if(tipoPers == 'F') { //pf
				    dataPf = "&tipoDoc=" + $('#selTipoDoc').val() +
					"&idPersona=" + $('#documento').val() +
					"&nombrePf1=" + $('#nombrePf1').val() +
				    "&nombrePf2=" + $('#nombrePf2').val() +
				    "&apellidoPf1=" + $('#apellidoPf1').val() +
				    "&apellidoPf2=" + $('#apellidoPf2').val() +
				    "&fNac=" + $('#fNac').val() +
				    "&sexo=" + $('#sexo').val();
			   } else { //pj
					dataPj = "&idPersona=" + $('#rut').val() +
					"&nombrePj=" + $('#nombrePj').val() +
					"&razonSoc=" + $('#razonSoc').val() +
					"&bps=" + $('#bps').val() +
					"&bse=" + $('#bse').val() +
					"&esProv=" + $('#esProv').is(':checked');
			   }
			var data = dataUsr + dataPf + dataPj + dataPers;
		    // For debugging purposes... see your console:
		    console.log(data);
		
		    $.ajax({
		        type: $form.attr('method'),
		        url:  $form.attr('action'),
		        data: data,
		        success: function (data) {
		            console.log("Respuesta desde JAVA con la siguiente DATA: ");
		            console.log(data);
		            // redirecting
		            if(data === "success") {
		            	bootstrap_alert.success('Ingreso del usuario ok.');
		            } else if(data === "warning") {
		            	bootstrap_alert.warning('Han surgido problemas y no se ingresó el usuario.');
			        }
		        }, error: function (response) {
		        	bootstrap_alert.danger(response.responseText);
		        }
		    });
		  }
	})

</script>
</html>