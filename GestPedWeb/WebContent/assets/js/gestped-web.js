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
function cargarCbxDep() {
	console.log('entra a cargarCbxDep');
	$("#selDep").empty();
	$.ajax({
        type: 'POST',
        url: 'ServletObtDep',
        contentType: 'application/json',              
        dataType: 'json',
        success: function(response) {
        	$.each(response, function () {
        		$('#selDep').append($('<option></option>').val(this['idDepartamento']).html(this['nombreDepartamento']));
        	});
//        	$('#selDep').trigger('change');
        	//inmediatamente a la carga de dep, cargo las loc.
        	cargarCbxLoc();
        }, error: function (response) {
        	bootstrap_alert.danger(response.responseText);
        }
    });
}

/**
 * personas
 * carga de select con localidades
 */
function cargarCbxLoc() {
	console.log('entra a cargarCbxLoc');
	$(function() {
		var fillCbxLoc = function() {
			var selected = $('#selDep').val();
			console.log('entra a fill de localidad con dep=' + selected);
			if(selected != null) {
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
//						$('#selLoc').trigger('change');
					}, error: function (response) {
						bootstrap_alert.danger(response.responseText);
					}
				});
			}
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
	cargarCbxDep();
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
		        	form.find('[name="fNac"]').val(ceroNum(d.getDate()) + '/' + ceroNum((d.getMonth()+1)) + '/' + d.getFullYear()).end();
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
		        console.log('previo a carga de DEP datos:' + response.persona.localidad.departamento.idDepartamento + ' ' + response.persona.localidad.departamento.nombreDepartamento);
		        form.find('[name="selDep"]').val(response.persona.localidad.departamento.idDepartamento).end();
		        $("#selDep").trigger("change");
		        console.log('previo a carga de LOC datos:' + response.persona.localidad.idLocalidad + ' ' + response.persona.localidad.nombreLocalidad);
		        cargarCbxLoc(response.persona.localidad.idLocalidad);
		        form.find('[name="selLoc"]').val(response.persona.localidad.idLocalidad).end();
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
			//
			console.log('entra a fill de producto');
			//
			var selected = $('#selTipoProd').val();
			var data = 'tipoRequest=L' + '&idTipoProd=' + selected;
			 $.ajax({
	            type: "POST",
	            url: "ServletObtProducto",
	            data: data,
	            success: function(response) {
	            	$('#selProd').empty();
	            	$.each(response, function () {
	            		$("#selProd").append($("<option></option>").val(this['idProducto']).html(this['nombre']));
	            	});
	            	$('#selProd').trigger('change');
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
	$(function() {
		var fillDatosProd = function() {
			var selected = $('#selProd').val();
			if(selected != null) {
				var data = 'tipoRequest=O' + '&idProd=' + selected;
				//
				console.log('entra a cargarDatosProd() > data: ' + data);
				//
				$.ajax({
					type: "POST",
					url: "ServletObtProducto",
					data: data,
					success: function(response) {
						$("#prodPrecio").val('');
						var form = $('#pedidoForm');
						form.find('[name="prodPrecio"]').val(response.precioVta).end();
						cargarModalInfoProd(response);
					}, error: function (response) {
						bootstrap_alert.danger(response.responseText);
					}
				});	
			}
		}
		fillDatosProd();
	});
}

function cargarModalInfoProd(response) {
	$('#modalInfoProd').find('.modal-title').html('<strong>Informacion detallada del producto:&nbsp;</strong>' + response.nombre);
	$('#modalInfoProd').find('.modal-body').html('<strong>ID:&nbsp;</strong>' + response.idProducto + '<br>' +
												'<strong>Descripcion:&nbsp;</strong>' + response.descripcion + '<br>' +
												'<strong>Presentacion:&nbsp;</strong>' + response.cantUnidad + '&nbsp;' + response.unidad.nombre + '<br>' +
												'<strong>Iva:&nbsp;</strong>' + response.aplIva + '<br>' +
												'<strong>Precio:&nbsp;</strong>' + response.precioVta);
}

function agregarItemPed() {
	var idProd = $('#selProd').val();
	var prodText = $('#selProd option:selected').text();
    var precio = $('#prodPrecio').val();
    var cant = $('#pedCant').val();
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
		if((precio === '' || precio === '0')) {
			bootstrap_alert.warning('El producto se encuentra sin stock o indisponible.');
		} else {
			bootstrap_alert.warning('Revise los datos para cargar el item al pedido.');
		}
	}
}

function deleteRow() {
	$('table').on('click','.delete',function() {
		$(this).parents('tr').remove();
	});
}

/*
 * metodo que genera el nuevo pedido
 */
function envioPed() {
	var dataPedido = '';
	var idProd;
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
		data = 'accion=N' +
				'&tipoPedido=' + $('#tipoPedido').val() +  
				'&pedido=' + dataPedido + 
				'&fechaHoraProg=' + $('#pedProg').val();
		console.log('valores a servlet: [' + data + ']');
		$.ajax({
			type: "POST",
			url: "ServletPedido",
			data: data,
			success: function(response) {
				//limpiar form
				if(response === 'success') {
					bootstrap_alert.success('El pedido ha sido registrado correctamente.');
					clearFieldsDataPedido();
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
}

function actualizarPed(accion) {
	var dataPedido = '';
	var idProd;
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
	
	var pedidoSel = $('#selPedExist').val();
	if(pedidoSel != undefined && pedidoSel !== '') {
		if(dataPedido !== '') {
			data = 'accion=' + accion +
			'&tipoPedido=' + $('#tipoPedido').val() + 
			'&keyPedido=' + pedidoSel +
			'&pedido=' + dataPedido + 
			'&fechaHoraProg=' + $('#pedProg').val();
			console.log('valores a servlet: [' + data + ']');
			$.ajax({
				type: "POST",
				url: "ServletPedido",
				data: data,
				success: function(response) {
					//limpiar form
					if(response === 'success') {
						bootstrap_alert.success('El pedido ha sido actualizado correctamente.');
						clearFieldsPedido();
					} else {
						bootstrap_alert.warning('Han surgido problemas para actualizado el pedido.');
					}
				}, error: function (response) {
					bootstrap_alert.danger(response.responseText);
				}
			});
		} else {
			bootstrap_alert.warning('El pedido debe tener items para ser actualizado.');
		}
	} else {
		bootstrap_alert.warning('Debe seleccionar un pedido para actualizar.');
	}
}

function seleccionTipoPedido(tipo) {
	if(tipo == 'E') {
		$('#divPedExistente').find('*').show();
		$('#divPedExistente').show();
		document.getElementById("fecPedDesde").required = true;
		document.getElementById("fecPedHasta").required = true;
		document.getElementById("generarPedido").disabled = true;
		$("#generarPedido").removeClass("btn-info").addClass("btn-default");
//		$('.selectpicker').selectpicker('refresh');
	} else {
		$('#divPedExistente').find('*').hide();
		$('#divPedExistente').hide();
		document.getElementById("fecPedDesde").required = false;
		document.getElementById("fecPedHasta").required = false;
		document.getElementById("generarPedido").disabled = false;
		$("#generarPedido").removeClass("btn-default").addClass("btn-info");
//		$('.selectpicker').selectpicker('refresh');
	}
	clearFieldsPedido();
}

function cargarCbxPedExistentes() {
	$(function() {
		var fillCbxPed = function() {
			clearFieldsPedido();
			console.log('se cargan los pedidos existentes...');
			var data = 'tipoRequest=L' +
						'&fechaDesde=' + $('#fecPedDesde').val() + 
						'&fechaHasta=' + $('#fecPedHasta').val() +
						'&estadoPedido=' + $('#selEstadoPed').val();
			 $.ajax({
	            type: 'POST',
	            url: 'ServletObtPedido',
	            data: data,
	            success: function(response) {
	            	$('#selPedExist').empty();
	            	$.each(response, function (i, item) {
	            		var idPersona = response[i].persona.idPersona;
	            		var fhp = new Date(response[i].fechaHora.time);
	            		var fechaHora = ceroNum(fhp.getDate()) + '/' + ceroNum((fhp.getMonth()+1)) + '/' + fhp.getFullYear() + ' ' + 
	            						ceroNum(fhp.getHours()) + ':' + ceroNum(fhp.getMinutes()) + ':' + ceroNum(fhp.getSeconds());

	            		$("#selPedExist").append($("<option></option>").val(idPersona + ';' + fechaHora).html(fechaHora));
	            	});
	            	$('#selPedExist').trigger('change');
	            }, error: function (response) {
	            	bootstrap_alert.danger(response.responseText);
	            }
		    });
		}
		fillCbxPed();
	});
}

function manejarControlesPorEstadoPed(estado) {
	//generarPedido, actualizarPedido, confirmarPedido, rechazarPedido, anularPedido
	if(estado === 'P') {//pendiente
		document.getElementById("generarPedido").disabled = true;
		$("#generarPedido").removeClass("btn-info").addClass("btn-default");
		
		document.getElementById("actualizarPedido").disabled = false;
		$("#actualizarPedido").removeClass("btn-default").addClass("btn-primary");
		
		document.getElementById("preconfPedido").disabled = true;
		$("#preconfPedido").removeClass("btn-success").addClass("btn-default");
		
		document.getElementById("rechazarPedido").disabled = true;
		$("#rechazarPedido").removeClass("btn-warning").addClass("btn-default");
		
		document.getElementById("anularPedido").disabled = false;
		$("#anularPedido").removeClass("btn-default").addClass("btn-danger");
		
//		$("#dtpPedProg").datetimepicker('disable', false);
	} else if(estado === 'R') {//revision
		document.getElementById("generarPedido").disabled = true;
		$("#generarPedido").removeClass("btn-info").addClass("btn-default");
		
		document.getElementById("actualizarPedido").disabled = true;
		$("#actualizarPedido").removeClass("btn-primary").addClass("btn-default");
		
		document.getElementById("preconfPedido").disabled = false;
		$("#preconfPedido").removeClass("btn-default").addClass("btn-success");
		
		document.getElementById("rechazarPedido").disabled = false;
		$("#rechazarPedido").removeClass("btn-default").addClass("btn-warning");
		
		document.getElementById("anularPedido").disabled = true;
		$("#anularPedido").removeClass("btn-danger").addClass("btn-default");
		
//		$("#dtpPedProg").datetimepicker('disable');
	} else {//confirmado, anulado o rechazado
		document.getElementById("generarPedido").disabled = true;
		$("#generarPedido").removeClass("btn-info").addClass("btn-default");
		
		document.getElementById("actualizarPedido").disabled = true;
		$("#actualizarPedido").removeClass("btn-primary").addClass("btn-default");
		
		document.getElementById("preconfPedido").disabled = true;
		$("#preconfPedido").removeClass("btn-success").addClass("btn-default");
		
		document.getElementById("rechazarPedido").disabled = true;
		$("#rechazarPedido").removeClass("btn-warning").addClass("btn-default");
		
		document.getElementById("anularPedido").disabled = true;
		$("#anularPedido").removeClass("btn-danger").addClass("btn-default");
		
//		$("#dtpPedProg").datetimepicker('disable');
	}
}

function seleccionPedidoExist() {
	var keyPedido = $('#selPedExist').val();
	console.log('Se procede a invocar servlet para el pedido: ' + keyPedido);
	if(!!keyPedido) {
		var data = 'tipoRequest=O' + '&dataPedido=' + keyPedido;
		$.ajax({
			type: "POST",
			url: "ServletObtPedido",
			data: data,
			success: function(response) {
				var lineasPedido = response.listaPedidoLinea;
				var estadoPed = response.estado;
				var fechaHoraEst = '';
				if(response.fechaProg !== undefined) {
					console.log('entra a dateParser: ' + response.fechaProg.time);
					fechaHoraEst += dateParser(response.fechaProg.time);
				}
				if(response.horaProg !== undefined) {
					console.log('entra a hourParser: ' + response.fechaProg.time);
					fechaHoraEst += ' ' + hourParser(response.horaProg.time);
				}
				console.log('fechaHoraEst=' + fechaHoraEst);
				$("#estadoPedido").val(estadoPed);
				manejarControlesPorEstadoPed(estadoPed);
				//se limpia la tabla
				$("#cargaTabla").empty();
				$("#pedProg").val('');
				//
				$.each(lineasPedido, function (i, item) {
					var lineaAct = lineasPedido[i];
					var claseTr = devolverClaseTablaSegunEstado(estadoPed);
					console.log('precio=' + lineaAct.producto.precioVta + ' cant=' + lineaAct.cantidad);
					var subTotalLn = (lineaAct.producto.precioVta * lineaAct.cantidad);
					var data = "<tr class=" + claseTr + ">" +
					"<td scope='row' class='row'>" + lineaAct.producto.idProducto + "</th>" +
					"<td>" + lineaAct.producto.nombre + "</td>" +
					"<td>" + lineaAct.producto.precioVta + "</td>" +
					"<td>" + lineaAct.cantidad + "</td>" +
					"<td>" + roundNumber(subTotalLn, 2) + "</td>" +
					"<th><button class='btn btn-default btn-sm delete' onclick='deleteRow()'><span class='glyphicon glyphicon-trash'></span></button></th>" +
					"</tr>";
					$("#tablaPedido tbody").append(data);
					
					if(fechaHoraEst != '') {
						$('#pedProg').val(fechaHoraEst);
					}
				});
				
			}, error: function (response) {
				bootstrap_alert.danger(response.responseText);
				clearFieldsDataPedido();
			}
		});
	} else {
		manejarControlesPorEstadoPed();//paso sin param para que anule todos los controles
	}
}


/**********************************************************************************************************************************************/
/** GENERICOS **/
/**********************************************************************************************************************************************/
function roundNumber (number, max = 2) {
  if (typeof number !== 'number' || isNaN(number)) {
	  throw new TypeError('Numero invalido: ' + number);  
  }
  
  if (typeof max !== 'number' || isNaN(max)) {
	  throw new TypeError('Maximo de digitos invalido: ' + max); 
  }
  
  let fractionalPart = number.toString().split('.')[1];
  
  if (!fractionalPart || fractionalPart.length <= 2) {
    return number;
  }
  
  return Number(number.toFixed(max));
}
/**********************************************************************************************************************************************/
function ceroNum(i) {
	if(i < 10) {
		i = '0' + i;
	}
	return i;
}
/**********************************************************************************************************************************************/
function devolverClaseTablaSegunEstado(estado) {
	var clase = 'default';
	if(estado === 'C' || estado === 'F') {//confirmado o preconf
		clase = 'success';
	} else if(estado === 'A') {//anulado
		clase = 'danger';
	} else if(estado === 'R') {
		clase = 'info';
	} else if(estado === 'X') {
		clase = 'warning';
	}
	return clase;
}
/**********************************************************************************************************************************************/
function dateParser(fecha) {
	var fechaRet = '';
	if(fecha !== undefined) {
		var d = new Date(fecha);
		fechaRet = ceroNum(d.getDate()) + '/' + ceroNum((d.getMonth()+1)) + '/' + d.getFullYear();
	}
	return fechaRet;
}
function hourParser(hora) {
	var horaRet = '';
	if(hora !== undefined) {
		var t = new Date(hora);
		horaRet = ceroNum(t.getHours()) + ':' + ceroNum(t.getMinutes()) + ':' + ceroNum(t.getSeconds());
	}
	return horaRet;
}
/**********************************************************************************************************************************************/
/** CLEAR FIELDS **/
/**********************************************************************************************************************************************/
function clearFieldsPedido() {
	console.log('se limpian los campos de pedido.');
	$('#selPedExist').empty();
	$("#cargaTabla").empty();
	$("#pedProg").val('');
}
function clearFieldsDataPedido() {
	console.log('se limpian los datos de pedido.');
	$("#cargaTabla").empty();
	$("#pedProg").val('');
}