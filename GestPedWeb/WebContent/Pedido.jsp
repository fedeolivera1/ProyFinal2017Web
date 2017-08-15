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
	<!-- NAV BAR -->
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand">Gestor de Pedidos WEB</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li><a href="RegistroPersona.jsp">Mis Datos</a></li>
	      <li class="active"><a href="#" class="active">Pedidos</a></li>
	    </ul>
	  </div>
	</nav>
	<!-- ALERTS -->
	<div class="page-alerts">
	    <div id="alert_placeholder"></div>
	</div>
	<br>
	
	PEDIDOS !!!
</body>
</html>