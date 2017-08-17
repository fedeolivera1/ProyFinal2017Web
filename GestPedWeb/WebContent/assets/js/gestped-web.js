/**
 * alertas
 */
bootstrap_alert = function() {
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
}
bootstrap_alert.info = function(message) {
	$('#alert_placeholder').html('<div class="alert alert-info alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span>'+message+'</span></div>')
	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove(); 
		});
	}, 4000);
}
bootstrap_alert.warning = function(message) {
	$('#alert_placeholder').html('<div class="alert alert-warning alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span>'+message+'</span></div>')
	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove(); 
		});
	}, 4000);
}
bootstrap_alert.danger = function(message) {
    $('#alert_placeholder').html('<div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span>'+message+'</span></div>')
    window.setTimeout(function() {
    	$(".alert").fadeTo(500, 0).slideUp(500, function() {
    		$(this).remove(); 
    	});
    }, 4000);
}

bootstrap_alert.close = function() {
	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove(); 
		});
	}, 0);
}

/**
 * 
 */
function cargarCbxTd(response) {
	$("#selTipoDoc").empty();
    $.each(response, function () {
        $("#selTipoDoc").append($("<option></option>").val(this['idTipoDoc']).html(this['nombre']));
    });
}


/**
 * departamentos y localidades
 */
function cargarCbxDep(response) {
	$("#selDep").empty();
    $.each(response, function () {
        $("#selDep").append($("<option></option>").val(this['idDepartamento']).html(this['nombreDepartamento']));
    });
    //le disparo el change para que levante las loc
    $("#selDep").trigger("change");
}

function cargarCbxLoc() {
	$(function() {
		var fillCbxLoc = function() {
			var selected = $('#selDep').val();
			var data = "idDep=" + selected;
			 $.ajax({
	            type: "post",
	            url: "ServletObtLoc",
//	            contentType: "application/json",              
//	            dataType: "json",
	            data: data,
	            success: function(response) {
	            	$('#selLoc').empty();
	            	$.each(response, function () {
	            		$("#selLoc").append($("<option></option>").val(this['idLocalidad']).html(this['nombreLocalidad']));
	            	});
	                
	            }
		    });
			 
		}
		$('#selDep').change(fillCbxLoc);
		fillCbxLoc();
	});
}

/**
 * metodo que cambia div para mostrar datos de pf o pj
 */
$(document).ready(function() {
	
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
});

function cargarDatosPers() {
	$.ajax({
        url: 'ServletPersona',
        method: 'GET',
        success: function(response) {
	    	if(response === 'nodata') {
	    		//usuario NO EXISTENTE
	    		//manejo controles por usuario no existente
	    		document.getElementById("passwdReg1").required = true;
		    	document.getElementById("passwdReg2").required = true;
		    	$('#divPw1').hide();
		    	$('#divPw2').hide();
		    	document.getElementById("emailReg").readOnly = false;
		    	//
		    	bootstrap_alert.info('Ingrese sus datos para registrarse...');
		    	$("#tipoPers").trigger("change");
	    	} else {
	    		//usuario EXISTENTE
	    		//manejo controles por usuario existente
	    		document.getElementById("passwdReg1").required = false;
		    	document.getElementById("passwdReg2").required = false;
		    	$('#divPw1').show();
		    	$('#divPw2').show();
		    	document.getElementById("emailReg").readOnly = true;
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
		        form.find('[name="selLoc"]').val(response.persona.localidad.idLocalidad).end();
	    	}
        }, error: function(qXHR, textStatus, errorThrown) {
        	bootstrap_alert.danger(errorThrown);
        }
	});
}
