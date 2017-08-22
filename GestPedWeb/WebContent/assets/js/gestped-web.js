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
	console.log('entra a cargarCbxLoc');
	$(function() {
		var fillCbxLoc = function() {
			console.log('entra a fill de localidad');
			var selected = $('#selDep').val();
			var data = "idDep=" + selected;
			 $.ajax({
	            type: "POST",
	            url: "ServletObtLoc",
	            data: data,
	            success: function(response) {
	            	$('#selLoc').empty();
	            	$.each(response, function () {
	            		$("#selLoc").append($("<option></option>").val(this['idLocalidad']).html(this['nombreLocalidad']));
	            	});
	                
	            }, error: function (response) {
	            	bootstrap_alert.danger(response.responseText);
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
        }, error: function (response) {
        	bootstrap_alert.danger(response.responseText);
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
			console.log('entra a fill de producto');
			var selected = $('#selTipoProd').val();
			var data = "idTipoProd=" + selected;
			 $.ajax({
	            type: "POST",
	            url: "ServletObtProducto",
	            data: data,
	            success: function(response) {
	            	$('#selProd').empty();
	            	$.each(response, function () {
	            		$("#selProd").append($("<option></option>").val(this['idProducto']).html(this['nombre']));
	            	});
	            }, error: function (response) {
	            	bootstrap_alert.danger(response.responseText);
	            }
		    });
		}
		fillCbxProd();
	});
	cargarDatosProd();
}

/**
 * pedidos - prod
 * carga datos de cada producto seleccionado
 */
function cargarDatosProd() {
	var selected = $('#selProd').val();
	var data = "idProd=" + selected;
	//
	console.log('entra a cargarDatosProd() > data=' + data);
	//
	$.ajax({
        type: "GET",
        url: "ServletObtProducto",
        data: data,
        success: function(response) {
        	$("#prodPrecio").val('');
        	var form = $('#pedidoForm');
        	form.find('[name="prodPrecio"]').val(response.precioVta).end();
        }, error: function (response) {
        	bootstrap_alert.danger(response.responseText);
        }
    });	
}

function agregarItemPed() {
	var idProd = $('#selProd').val();
	var prodText = $('#selProd option:selected').text();
    var precio = $('#prodPrecio').val();
    var cant = $('#pedCant').val();
	console.log(data);
	if( (precio !== '' && precio > 0) &&
			(cant !== '' && cant > 0) ) {
		var buscarExistente = $("#selProd").val();
		console.log('agregar pedido - dato a buscar: ' + buscarExistente);
		var encontradoResultado = false;
		$("#tablaPedido tr").find('td:eq(0)').each(function () {
			var idProd = $(this).html();
			if(idProd == buscarExistente) {
				console.log('idProd actual tabla : ' + idProd + ' se repite.');
				encontradoResultado = true;
			}
		});

		if(!encontradoResultado) {
			var data = "<tr>" +
			"<td scope='row' class='row'>"+idProd+"</th>" +
			"<td>" + prodText + "</td>" +
			"<td>" + precio + "</td>" +
			"<td>" + cant + "</td>" +
			"<td>" + roundNumber((precio*cant), 2) + "</td>" +
			"<th><button class='btn btn-default btn-sm delete' onclick='deleteRow()'><span class='glyphicon glyphicon-trash'></span></button></th>" +
			"</tr>";
			$("#tablaPedido tbody").append(data);
		} else {
			bootstrap_alert.info('El item ya existe en el pedido.');
		}
	} else {
		bootstrap_alert.warning('Revise los datos para cargar el item al pedido.');
	}
}

function deleteRow() {
	$('table').on('click','.delete',function() {
		$(this).parents('tr').remove();
	});
}

function envioPed() {
//$("#generarPedido").click(function() {
	var dataPedido = '';
	//no necesito mas valores de th
//	$(".row").parent("tr").find("th").each(function() {
//		valores += $(this).html() + " ";
//	});
	var idProd;
//	var nomProd;
//	var precioUnit;
	var cant;
	$(".row").parent("tr").each(function(index) {
		$(this).children("td").each(function (index2) {
			switch (index2) {
				case 0:
					//obtengo dato de idProd
				   idProd = $(this).text();
				   break;
				case 3:
					//obtengo dato de cant
					cant = $(this).text();
					break;
			}
		});
		dataPedido += idProd + ";" + cant + "~";
	});
	
	if(dataPedido !== '') {
		data = 'pedido=' + dataPedido + '&fechaHoraProg=' + $('#pedProg').val();
		console.log('valores a servlet: [' + data + ']');
		$.ajax({
			type: "POST",
			url: "ServletPedido",
			data: data,
			success: function(response) {
				//limpiar form
				if(response === 'success') {
					bootstrap_alert.success('El pedido ha sido registrado correctamente.');
				} else {
					bootstrap_alert.warning('Han surgido problemas para registrar el pedido.');
				}
			}, error: function (response) {
				bootstrap_alert.danger(response.responseText);
			}
		});
	} else {
		bootstrap_alert.warning('El pedido debe tener items para ser registrado.');
	}
	
//});
}

function seleccionTipoPedido(tipo) {
	if(tipo == 'N') {
		alert('pedido nuevo');
	} else {
		alert('pedido existente');
	}
}


/**********************************************************************************************************************************************/
/** GENERICOS **/
/**********************************************************************************************************************************************/

function roundNumber (number, max = 2) {
  if (typeof number !== 'number' || isNaN(number)) {
	  throw new TypeError('Número inválido: ' + number);  
  }
  
  if (typeof max !== 'number' || isNaN(max)) {
	  throw new TypeError('Máximo de dígitos inválido: ' + max); 
  }
  
  let fractionalPart = number.toString().split('.')[1];
  
  if (!fractionalPart || fractionalPart.length <= 2) {
    return number;
  }
  
  return Number(number.toFixed(max));
}

