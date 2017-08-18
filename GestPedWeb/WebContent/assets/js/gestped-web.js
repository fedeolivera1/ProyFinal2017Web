/**
 * alertas
 */
bootstrap_alert = function() {
	alert('entra scrolltop');
	$('body, html').animate({
		scrollTop: '0px'
	}, 300);
}
bootstrap_alert.success = function(message) {
	$('#alert_placeholder').html('<div class="alert alert-success alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span>'+message+'</span></div>')
	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove(); 
		});
	}, 4000);
	$("html, body").animate({ scrollTop: 0 }, 600);
}
bootstrap_alert.info = function(message) {
	$('#alert_placeholder').html('<div class="alert alert-info alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span>'+message+'</span></div>')
	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove(); 
		});
	}, 4000);
	$("html, body").animate({ scrollTop: 0 }, 600);
}
bootstrap_alert.warning = function(message) {
	$('#alert_placeholder').html('<div class="alert alert-warning alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span>'+message+'</span></div>')
	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove(); 
		});
	}, 4000);
	$("html, body").animate({ scrollTop: 0 }, 600);
}
bootstrap_alert.danger = function(message) {
    $('#alert_placeholder').html('<div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span>'+message+'</span></div>')
    window.setTimeout(function() {
    	$(".alert").fadeTo(500, 0).slideUp(500, function() {
    		$(this).remove(); 
    	});
    }, 4000);
    $("html, body").animate({ scrollTop: 0 }, 600);
}

bootstrap_alert.close = function() {
	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove(); 
		});
	}, 0);
}

/**
 * personas
 * carga select con tipo de doc
 */
function cargarCbxTd(response) {
	$("#selTipoDoc").empty();
    $.each(response, function () {
        $("#selTipoDoc").append($("<option></option>").val(this['idTipoDoc']).html(this['nombre']));
    });
}


/**
 * personas
 * carga select con departamentos
 */
function cargarCbxDep(response) {
	$("#selDep").empty();
    $.each(response, function () {
        $("#selDep").append($("<option></option>").val(this['idDepartamento']).html(this['nombreDepartamento']));
    });
    //inmediatamente a la carga de dep, cargo las loc.
    cargarCbxLoc();
}

/**
 * personas
 * carga de select con localidades
 */
function cargarCbxLoc() {
	alert('entra a cargarCbxLoc');
	$(function() {
		var fillCbxLoc = function() {
			alert('entra a fill de localidad');
			var selected = $('#selDep').val();
			var data = "idDep=" + selected;
			 $.ajax({
	            type: "post",
	            url: "ServletObtLoc",
	            data: data,
	            success: function(response) {
	            	$('#selLoc').empty();
	            	$.each(response, function () {
	            		$("#selLoc").append($("<option></option>").val(this['idLocalidad']).html(this['nombreLocalidad']));
	            	});
	                
	            }
		    });
		}
		fillCbxLoc();
	});
}

/**
 * personas
 * metodo que cambia div para mostrar datos de pf o pj
 * maneja requeridos de controles dependiendo de seleccion
 */
function seleccionTipoPers() {
	$('#tipoPers').on('change', function() {
	    var valorCambiado = $(this).val();
	    if((valorCambiado == 'J')) {
	    	$('#divPf').find('*').hide();
	    	$('#divPf').hide();
	    	$('#divPj').find('*').show();
	    	$('#divPj').show();
	    	
	    	document.getElementById("rut").required = true;
	    	document.getElementById("nombrePj").required = true;
	    	document.getElementById("documento").required = false;
	    	document.getElementById("nombrePf1").required = false;
	    	document.getElementById("apellidoPf1").required = false;
	    	document.getElementById("fNac").required = false;
	    	$('#ingrPersForm').validator('update');
	     } else {
	    	$('#divPj').find('*').hide();
	    	$('#divPj').hide();
	    	$('#divPf').find('*').show();
			$('#divPf').show();
			
			document.getElementById("rut").required = false;
	    	document.getElementById("nombrePj").required = false;
	    	document.getElementById("documento").required = true;
	    	document.getElementById("nombrePf1").required = true;
	    	document.getElementById("apellidoPf1").required = true;
	    	document.getElementById("fNac").required = true;
	    	$('#ingrPersForm').validator('update');
	     }
	});
}

/**
 * personas
 * metodo para cargar personas por ajax. dependiendo de respuesta de servlet, maneja controles
 * cuando response es 'nodata', maneja controles para usuarios nuevos,
 * cuando respone devuelve persona, maneja controles para usuarios existentes
 */
function cargarDatosPers() {
	$.ajax({
        url: 'ServletPersona',
        method: 'GET',
        success: function(response) {
	    	if(response === 'nodata') {
	    		//manejo controles por usuario NO EXISTENTE
	    		document.getElementById("passwdReg1").required = true;
		    	document.getElementById("passwdReg2").required = true;
		    	$('#divPw1').hide();
		    	$('#divPw2').hide();
		    	document.getElementById("emailReg").readOnly = false;
		    	//
		    	bootstrap_alert.info('Ingrese sus datos para registrarse...');
		    	$("#tipoPers").trigger("change");
		    	document.getElementById('limpiar').disabled = false;
	    	} else {
	    		//manejo controles por usuario EXISTENTE
	    		document.getElementById("passwdReg1").required = false;
		    	document.getElementById("passwdReg2").required = false;
		    	$('#divPw1').show();
		    	$('#divPw2').show();
		    	document.getElementById("emailReg").readOnly = true;
		    	document.getElementById('limpiar').disabled = true;
		    	//
	    		var tipoPers = response.persona.tipoPers;
	    		var form = $('#ingrPersForm');
		        form.find('[name="emailReg"]').val(response.nomUsu).end();
		        form.find('[name="tipoPers"]').val(tipoPers).end();
		        $("#tipoPers").trigger("change");
		        if(tipoPers == "F") {
		        	form.find('[name="selTipoDoc"]').val(response.persona.tipoDoc.idTipoDoc).end();
		        	form.find('[name="documento"]').val(response.persona.documento).end();
		        	form.find('[name="nombrePf1"]').val(response.persona.nombre1).end();
		        	form.find('[name="nombrePf2"]').val(response.persona.nombre2).end();
		        	form.find('[name="apellidoPf1"]').val(response.persona.apellido1).end();
		        	form.find('[name="apellidoPf2"]').val(response.persona.apellido2).end();
		        	var d = new Date(response.persona.fechaNac.time);
		        	form.find('[name="fNac"]').val(d.getDate() + '/' + (d.getMonth()+1) + '/' + d.getFullYear()).end();
		        	form.find('[name="sexo"]').val(response.persona.sexo).end();
		        } else if(tipoPers == "J") {
		        	form.find('[name="rut"]').val(response.persona.rut).end();
		        	form.find('[name="nombrePj"]').val(response.persona.nombre).end();
		        	form.find('[name="razonSoc"]').val(response.persona.razonSocial).end();
		        	form.find('[name="bps"]').val(response.persona.bps).end();
		        	form.find('[name="bse"]').val(response.persona.bse).end();
		        	//manejo check para activar o desact segun prov en pj
		        	if(response.persona.esProv) {
	        			$("#lblChkProv").addClass('active');
		        	} else {
		        		$("#lblChkProv").removeClass('active');   
		        	}
		        }
		        form.find('[name="direccion"]').val(response.persona.direccion).end();
		        form.find('[name="puerta"]').val(response.persona.puerta).end();
		        form.find('[name="solar"]').val(response.persona.solar).end();
		        form.find('[name="manzana"]').val(response.persona.manzana).end();
		        form.find('[name="km"]').val(response.persona.km).end();
		        form.find('[name="comp"]').val(response.persona.complemento).end();
		        form.find('[name="telefono"]').val(response.persona.telefono).end();
		        form.find('[name="celular"]').val(response.persona.celular).end();
		        form.find('[name="selDep"]').val(response.persona.localidad.departamento.idDepartamento).end();
		        $("#selDep").trigger("change");
		        alert('loc:' + response.persona.localidad.idLocalidad + '-' + response.persona.localidad.nombreLocalidad)
		        form.find('[name="selLoc"]').val(response.persona.localidad.idLocalidad).end();
		        alert('finaliza de cargar localidad del response');
	    	}
        }, error: function(qXHR, textStatus, errorThrown) {
        	bootstrap_alert.danger(errorThrown);
        }
	});
}

/**
 * pedidos - prod
 * carga select con tipos de producto
 */
function cargarCbxTipoProd(response) {
	$("#selTipoProd").empty();
    $.each(response, function () {
        $("#selTipoProd").append($("<option></option>").val(this['idTipoProd']).html(this['descripcion']));
    });
    //inmediatamente a la carga de tipo prod, cargo los prod asociados.
    cargarCbxProd();
}

/**
 * pedidos - prod
 * carga select con productos
 */
function cargarCbxProd() {
	$(function() {
		var fillCbxProd = function() {
			alert('entra a fill de producto');
			var selected = $('#selTipoProd').val();
			var data = "idTipoProd=" + selected;
			 $.ajax({
	            type: "post",
	            url: "ServletObtProducto",
	            data: data,
	            success: function(response) {
	            	$('#selProd').empty();
	            	$.each(response, function () {
	            		$("#selProd").append($("<option></option>").val(this['idProducto']).html(this['nombre']));
	            	});
	                
	            }
		    });
		}
		fillCbxProd();
	});
}

