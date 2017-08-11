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
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../assets/js/jquery-3.2.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../bootstrap/js/bootstrap.min.js"></script>
  </head>
  <body>
    <h1>Gestor de Pedidos WEB</h1>

<form class="form-horizontal">
<fieldset>

<!-- Form Name -->
<legend>Registro</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="mail1">Usuario Email</label>  
  <div class="col-md-4">
  <input id="mail1" name="mail1" type="email" placeholder="Usuario Email" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="contra">Password</label>
  <div class="col-md-4">
    <input id="contra" name="contra" type="password" placeholder="Password" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Password input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="contra2">Confirme Password</label>
  <div class="col-md-4">
    <input id="contra2" name="contra2" type="password" placeholder="Confirme Password" class="form-control input-md" required="">
    
  </div>
</div>

<hr>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="nombre">Primer Nombre</label>  
  <div class="col-md-4">
  <input id="nombre1" name="nombre1" type="text" placeholder="Primer Nombre" class="form-control input-md" required="">
    
  </div>
</div>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="nombre">Segundo Nombre</label>  
  <div class="col-md-4">
  <input id="nombre2" name="nombre2" type="text" placeholder="Segundo Nombre" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="apellido">Primer Apellido</label>  
  <div class="col-md-4">
  <input id="apellido1" name="apellido1" type="text" placeholder="Primer Apellido" class="form-control input-md" required="">
    
  </div>
</div>
<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="apellido">Segundo Apellido</label>  
  <div class="col-md-4">
  <input id="apellido2" name="apellido2" type="text" placeholder="Segundo Apellido" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="mail1">Email</label>  
  <div class="col-md-4">
  <input id="mail2" name="mail2" type="email" placeholder="Email" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="mail3">Confirme Email</label>  
  <div class="col-md-4">
  <input id="mail3" name="mail3" type="email" placeholder="Conf. Email" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Telefono">Telefono</label>  
  <div class="col-md-4">
  <input id="Telefono" name="Telefono" type="text" placeholder="Telefono" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Celular">Celular</label>  
  <div class="col-md-4">
  <input id="Celular" name="Celular" type="text" placeholder="Celular" class="form-control input-md">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="Direccion">Direccion</label>  
  <div class="col-md-4">
  <input id="Direccion" name="Direccion" type="text" placeholder="Direccion" class="form-control input-md">
    
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="Departamento">Departamento</label>
  <div class="col-md-4">
    <select id="Departamento" name="Departamento" class="form-control">
      <option value="1">Montevideo</option>
      <option value="2">Salto</option>
    </select>
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="Localidad">Localidad</label>
  <div class="col-md-4">
    <select id="Localidad" name="Localidad" class="form-control">
      <option value="1"></option>
    </select>
  </div>
</div>


<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="registrar"></label>
  <div class="col-md-4">
    <button id="registrar" name="registrar" class="btn btn-primary">Registrar</button>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for="limpiar"></label>
  <div class="col-md-4">
    <button type="reset" class="btn btn-danger">Limpiar</button>
  </div>
</div>

</fieldset>
</form>

  </body>
</html>