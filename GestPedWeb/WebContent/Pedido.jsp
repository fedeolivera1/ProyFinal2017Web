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
    <link href="bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery  -->
    <script type="text/javascript" src="assets/js/jquery-3.2.1.min.js"></script>
    <!-- BootStrap -->
    <script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<!--     Incluyo el JS propio del proyecto -->
    <script type="text/javascript" src="assets/js/gestped-web.js"></script>
	<!-- DateTimePicker -->
<!--     <script type="text/javascript" src="bootstrap/js/moment.js"></script> -->
    <script type="text/javascript" src="bootstrap/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="bootstrap/js/bootstrap-datetimepicker.es.js"></script>
    <!--     Validator -->
    <script type="text/javascript" src="bootstrap/js/validator.js"></script>
    
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
	    	<a class="navbar-brand" title="YAMETL SRL" alt="YAMETL SRL">
	        	<img style="max-width: 50px; margin-top: -15px;"
	             src="assets/img/icon_md.png">
	    	</a>
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
	
<fieldset>
<legend>Pedidos</legend>
	<!-- FORM 1 -->
  	<form class="form-horizontal">
		<div class="form-group col-md-12">
		  <label class="col-md-4 control-label" for="tipoPedido">Pedido:</label>
			 <div class="col-sm-4 col-md-4">
	   			<div class="input-group">
	   				<div id="radioBtn" class="btn-group">
	   					<a class="btn btn-default btn-sm active" data-toggle="tipoPedido" data-title="N" onclick="seleccionTipoPedido('N');">Nuevo</a>
	   					<a class="btn btn-default btn-sm notActive" data-toggle="tipoPedido" data-title="E" onclick="seleccionTipoPedido('E');">Existente</a>
	   				</div>
	   				<input type="hidden" name="tipoPedido" id="tipoPedido" value="N">
	   				<input type="hidden" name="estadoPedido" id="estadoPedido">
	   			</div>
	   		</div>
	  	</div>
  	</form>
  	<!-- Modal -->
  	<div class="container">
	  <div class="modal fade" id="modalInfoProd" role="dialog">
	    <div class="modal-dialog">
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title"></h4>
	        </div>
	        <div class="modal-body">
	          <p></p>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>
	      
	    </div>
	  </div>
  	</div>
  	<!-- FORM 2 -->
  	<form class="form-horizontal" action="ServletObtPedido" method="GET" id="obtPedidoForm" data-toggle="validator">
	  	<!-- 	DIV PEDIDO EXISTENTE -->
	  	<div id="divPedExistente" class="oculto" style="display:none">
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="pedExistentes">Rango de fechas</label>
				<div class="col-md-2">
					<div class="col-md-12 input-group date" id="dtpPedDesde">
			            <input type="text" id="fecPedDesde" name="fecPedDesde" class="form-control"/>
			            <span class="input-group-addon">
			                <span class="glyphicon glyphicon-calendar"></span>
			            </span>
		        	</div>
					<div class="help-block with-errors"></div>
			  	</div>
			    <div class="col-md-2">
					<div class="col-md-12 input-group date" id="dtpPedHasta">
			            <input type="text" id="fecPedHasta" name="fecPedHasta" class="form-control"/>
			            <span class="input-group-addon">
			                <span class="glyphicon glyphicon-calendar"></span>
			            </span>
		        	</div>
					<div class="help-block with-errors"></div>
			  	</div>
			</div>
			<div class="form-group col-md-12">
				<label class="col-md-4 control-label" for="pedExistentes">Estado</label>
				 <div class="col-md-2">
					<select id="selEstadoPed" name="selEstadoPed" class="form-control">
						<option value="P">Pendiente</option>
		      			<option value="R">Revision</option>
		      			<option value="F">PreConfirmado</option>
		      			<option value="C">Confirmado</option>
		      			<option value="X">Rechazado</option>
		      			<option value="A">Anulado</option>
				    </select>
				 </div>
				 <div class="col-md-2">
				    <button type="button" id="obtenerPedido" name="obtenerPedido" class="btn btn-primary">Obtener</button>
				 </div>
			</div>
		  	<!-- Select Basic [tipoProd]-->
			<div class="form-group col-md-12">
				<label class="col-md-4 control-label" for="pedExistentes">Pedidos existentes</label>
				<div class="col-md-4">
				  <select id="selPedExist" name="selPedExist" class="form-control" onchange="seleccionPedidoExist();">
				  </select>
				</div>
			</div>
	  	</div>
  	</form>
  	<!-- FORM 3 -->
   	<form class="form-horizontal" action="ServletPedido" method="POST" id="pedidoForm" data-toggle="validator">
			<!-- Select Basic [tipoProd]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="tipoProd">Tipo Producto</label>
			  <div class="col-md-2">
			    <select id="selTipoProd" name="selTipoProd" class="form-control" onchange="cargarCbxProd();">
			    </select>
			  </div>
			</div>
			<div id="datosItems">
				<!-- Select Basic [Producto]-->
				<div class="form-group col-md-12">
				  <label class="col-md-4 control-label" for="Producto">Producto</label>
				  <div class="col-md-4">
				    <select id="selProd" name="selProd" class="form-control" onchange="cargarDatosProd(this);">
				    </select>
				  </div>
				  <div class="col-md-1">
				    <button type="button" class="btn btn-default btn-md" id="infoProd">Info</button>
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
						<input id="pedCant" name="pedCant" type="number" min="1" step="1" max="255" pattern="[0-9]{1,}" placeholder="0" class="form-control input-md">
						<div class="help-block with-errors"></div>
					</div>
				</div>
			</div>
			
			<!-- Buttons [Agregar item] -->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="registrar"></label>
			  <div class="col-md-4">
			  	<button type="button" id="agregarItem" name="agregarItem" class="btn">Agregar</button>
			  	<button type="reset" id="resetItem" name="resetItem" class="btn btn-danger">Limpiar</button>
			  </div>
			</div>
			
			<div class="container">
  				<h2>Items:</h2>
  				<div class="table-responsive"> 
					<table class="table table-bordred table-striped" id="tablaPedido">
					 	<thead>
						    <tr>
						      <th>Id</th>
						      <th>Producto</th>
						      <th>Precio Unit</th>
						      <th>Cantidad</th>
						      <th>SubTotal</th>
						      <th>Borrar</th>
						    </tr>
					  	</thead>
						<tbody id="cargaTabla">
						</tbody>
					</table>
  				</div>
			</div>
			
			<hr>
			<!-- Input text y Datetimepicker [Fecha prog - Hora prog]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="FechaProg">Fecha Hora estimada:</label>
			  <div class="col-md-5">
				<div class="col-md-5 input-group date" id="dtpPedProg">
		            <input type="text" id="pedProg" name="pedProg" class="form-control" readonly/>
		            <span class="input-group-addon">
		                <span class="glyphicon glyphicon-calendar"></span>
		            </span>
				</div>
	            <div id="divPedProg" class="oculto"><label><font color="gray" size="1">opcional</font></label></div>
			  </div>  
			</div>
			
			<!-- Buttons [Generar-Modificar pedido]-->
			<div class="form-group col-md-12">
			  <label class="col-md-4 control-label" for="registrar"></label>
			  <div class="col-md-6">
			  	<button type="button" id="generarPedido" name="generarPedido" class="btn btn-info" onclick="envioPed();">Generar</button>
			  	<button type="button" id="actualizarPedido" name="actualizarPedido" class="btn btn-default" onclick="actualizarPed('U');" disabled>Actualizar</button>
			  	<button type="button" id="preconfPedido" name="preconfPedido" class="btn btn-default" onclick="actualizarPed('F');" disabled>PreConfirmar</button>
			  	<button type="button" id="rechazarPedido" name="rechazarPedido" class="btn btn-default" onclick="actualizarPed('X');" disabled>Rechazar</button>
			  	<button type="button" id="anularPedido" name="anularPedido" class="btn btn-default" onclick="actualizarPed('A');" disabled>Anular</button>
			  </div>
			</div>
			
		</form>
	</fieldset>
<% 
	} else {
%>
	<div class="container">
		<div class="panel-heading" align="center">
			<h2><b><font color="black">Debe ser un Usuario registrado para acceder a este recurso.</font></b></h2><br>
			<a href="login.jsp">Volver al login</a><br>
			<a href="registroPersona.jsp">Registrarme</a><br>
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

	$('#divPedExistente').find('*').hide();
	$('#divPedExistente').hide();

	$("#infoProd").click(function(){
        $("#modalInfoProd").modal({show: true});
    });
	
	//datetimepicker con fecha y hora, intervalos de 30 min, fecha minima hoy
	$('#dtpPedProg').datetimepicker({
		format: 'dd/mm/yyyy hh:ii',
        autoclose: true,
        startDate: new Date(),
        minuteStep: 30,
        clearBtn: true,
        language: 'es'
	});

	$('#dtpPedDesde').datetimepicker({
		format: 'dd/mm/yyyy',
        minView: 2,
        maxView: 4,
        autoclose: true,
        language: 'es'
	});
	$('#dtpPedHasta').datetimepicker({
		format: 'dd/mm/yyyy',
        minView: 2,
        maxView: 4,
        autoclose: true,
        language: 'es'
	});

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

    $('#agregarItem').on('click', function (e) {
    	if ($('#pedidoForm').validator('validate').has('.has-error').length) {
  			// manejo form inválido (opc)
       	} else {
	    	e.preventDefault();
     		agregarItemPed();
        }
    });

    $('#obtenerPedido').on('click', function (e) {
    	if ($('#obtPedidoForm').validator('validate').has('.has-error').length) {
  			// manejo form inválido (opc)
       	} else {
	    	e.preventDefault();
	    	cargarCbxPedExistentes();
        }
    });

    $('#radioBtn a').on('click', function() {
        var sel = $(this).data('title');
        var tog = $(this).data('toggle');
        $('#'+tog).prop('value', sel);
        
        $('a[data-toggle="'+tog+'"]').not('[data-title="'+sel+'"]').removeClass('active').addClass('notActive');
        $('a[data-toggle="'+tog+'"][data-title="'+sel+'"]').removeClass('notActive').addClass('active');
    })
    
});

</script>
</html>