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
}

function cargarCbxLoc() {
	$(function(){
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
//function deplegarTipoPers(tipo) {
$(document).ready(function() {
	$('#tipoPers').on('change', function() {
	    var valorCambiado = $(this).val();
	    if((valorCambiado == 'J')) {
	    	$('#divPf').css('display','none');
	    	$('#divPj').css('display','block');
	     } else {
	    	 $('#divPf').css('display','block');
	    	 $('#divPj').css('display','none');
	     }
	});
});
//}