<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en"><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
<!--     <link rel="icon" href="../../favicon.ico"> -->

    <title>Gestor de Pedidos WEB</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/css/signin.css" rel="stylesheet">

	<script type="text/javascript" src="assets/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<!--     Incluyo el JS propio del proyecto -->
    <script type="text/javascript" src="assets/js/gestped-web.js"></script>
	<!--     Incluyo js de criptografia -->
	<script type="text/javascript" src="assets/js/core.js"></script>  
	<script type="text/javascript" src="assets/js/md5.js"></script>  
	
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<!--     <script src="../../assets/js/ie-emulation-modes-warning.js"></script> -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>

  <body>
	<div class="page-alerts">
	    <div id="alert_placeholder"></div>
	</div>
    <div class="container">

		<div class="panel-heading" align="center">
			<h2><b><font color="black">Gestor de Pedidos WEB</font> </b></h2>
		</div>
		<form class="form-signin" action="ServletLogin" method="POST" id="loginForm">
			<h2 class="form-signin-heading">Login:</h2>

		      	<label for="inputEmail" class="sr-only">Direccion de Email</label>
		      	<input type="email" name="txtNomUsu" id="txtNomUsu" class="form-control" placeholder="Email" required="" autofocus="">

				<label for="inputPassword" class="sr-only">Password</label>
			    <input type="password" name="txtPassWd" id="txtPassWd" class="form-control" placeholder="Password" required="">

			    <div class="checkbox">
			    	<label><input type="checkbox" value="remember-me"> Remember me</label>
			    </div>
			    <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
			    <button class="btn btn-lg btn-default btn-block" type="reset"><b>Clean</b></button>
			    <div class="form-control-static">
					<a href="registroPersona.jsp">Registrarme</a>
				</div>
      </form>

    </div> <!-- /container -->
  
<script>
$("#loginForm").submit(function( event ) {
    event.preventDefault();

    var $form = $(this);

    var data = "txtNomUsu=" + $('#txtNomUsu').val() + "&txtPassWd=" + CryptoJS.MD5($('#txtPassWd').val());

    // The actual from POST method
    $.ajax({
        type: $form.attr('method'),
        url:  $form.attr('action'),
        data: data,
        success: function (data) {
            console.log("Hey, we got reply form java side, with following data: ");
            console.log(data);
            // data success > redirijo usuario logueado a pedido
            if(data === "success") {
              	window.location.replace('/GestPedWeb/pedido.jsp');
            } else {
            	bootstrap_alert.danger('El usuario no se ha podido autenticar.');
            }
        }
    });

});    
</script>

</body>
</html>