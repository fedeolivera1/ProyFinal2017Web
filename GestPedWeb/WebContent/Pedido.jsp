<%@page import="gpw.dominio.usuario.UsuarioWeb"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
</head>
<body>
<%
	if(session.getAttribute("usuario") != null) {
		String sessionId = session.getId();
%>
	<!-- NAV BAR -->
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand">Gestor de Pedidos WEB</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li><a href="registroPersona.jsp">Mis Datos</a></li>
	      <li class="active"><a href="#" class="active">Pedidos</a></li>
	      <li><a>Usuario:&nbsp;${sessionScope.usuario}</a></li>
	      <li><a href="ServletLogout?logout=<%=sessionId%>"><font color="red">Logout</font></a></li>
	    </ul>
	  </div>
	</nav>
	<!-- ALERTS -->
	<div class="page-alerts">
	    <div id="alert_placeholder"></div>
	</div>
	<br>
	
	<form class="form-horizontal" action="ServletPedido" method="POST" id="pedidoForm" data-toggle="validator">
		<fieldset>
			<!-- Form Name -->
			<legend>Pedidos</legend>
			
			<!-- Select Basic [tipoProd]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="tipoProd">Tipo Producto</label>
			  <div class="col-md-2">
			    <select id="selTipoProd" name="selTipoProd" class="form-control" onchange="cargarCbxProd();">
			    </select>
			  </div>
			</div>
			
			<!-- Select Basic [Producto]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="Producto">Producto</label>
			  <div class="col-md-4">
			    <select id="selProd" name="selProd" class="form-control" onchange="cargarDatosProd(this);">
			    </select>
			  </div>
			</div>
			
			<!-- Text input [Precio] y [Cantidad]-->
			<div class="form-group col-md-12">
				<label class="col-md-4 control-label" for="Precio">Precio</label>  
				<div class="col-md-1">
					<input id="prodPrecio" name="prodPrecio" type="text" placeholder="0" class="form-control input-md" readonly>
				</div>
				<label class="col-md-1 control-label" for="Cantidad">Cantidad</label>  
				<div class="col-md-1">
					<input id="pedCant" name="pedCant" type="text" placeholder="0" class="form-control input-md">
				</div>
			</div>
			
			<!-- Buttons [Agregar item] -->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="registrar"></label>
			  <div class="col-md-4">
			  	<button type="button" id="agregarItem" name="agregarItem" class="btn btn-primary" onclick="agregarItemPed();">Agregar</button>
			  </div>
			</div>
			
			<div class="container">
  				<h2>Items:</h2>
  				<div class="table-responsive"> 
					<table class="table table-bordred table-striped" id="tablaPedido">
					  <thead>
					    <tr>
					      <th>#</th>
					      <th>Producto</th>
					      <th>Precio Unit</th>
					      <th>Cantidad</th>
					      <th>SubTotal</th>
					      <th>Borrar</th>
					    </tr>
					  </thead>
					    <tbody id="cargaTabla">
  						</tbody>
					  </tbody>
					</table>
  				</div>
			</div>
			
			<!-- Buttons [Generar pedido]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="registrar"></label>
			  <div class="col-md-4">
			  	<button type="button" id="generarPedido" name="generarPedido" class="btn btn-primary" onclick="envioPed();">Generar</button>
			  	<button type="button" id="actualizarPedido" name="actualizarPedido" class="btn btn-primary" onclick="actualizarPedido();">Actualizar</button>
			  </div>
			</div>
			
		</fieldset>
	</form>
<% 
	} else {
%>
	<div class="container">
		<div class="panel-heading" align="center">
			<h2><b><font color="black">Usuario no autorizado a acceder al recurso.</font></b></h2><br>
			<a href="login.jsp">Volver al login</a>
			<hr>
		</div>
	</div>
<% 
	}
%>
</body>

<script type="text/javascript">

//document ready
$(document).ready(function() {
    $.ajax({
        type: "POST",
        url: "ServletObtTipoProd",
        contentType: "application/json",              
        dataType: "json",
        success: function(response) {
        	cargarCbxTipoProd(response);
        }, error: function (response) {
        	bootstrap_alert.danger(response.responseText);
        }
    });

	
});

</script>
</html>