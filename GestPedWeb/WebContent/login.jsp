<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gestor de Pedidos WEB</title>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
        <div class="panel panel-success">
            <div class="panel-heading" align="center">
                <h4><b><font color="black" style="font-family: fantasy;">Gestor de Pedidos WEB</font> </b></h4>
            </div>
            <div class="panel-body"align="center">
                  
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
    
                    <div class="panel panel-success" style="max-width: 35%;" align="left">
                        
                        <div class="panel-heading form-group">
                            <b><font color="black">
                                Login Form</font> </b>
                        </div>
                        <div class="panel-body" >

                        <form action="ServletLogin" method="post">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Usuario</label> 
                                <input type="text" class="form-control" name="txtNomUsu" id="txtNomUsu"
                                    placeholder="Ingrese usuario" required="required">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label> <input
                                    type="password" class="form-control" name="txtPassWd" id="txtPassWd"
                                    placeholder="Ingrese password" required="required">
                            </div>
                            <button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" ><b>Login</b></button>
                            <br>
                            <button type="reset" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-default btn-lg btn-block" ><b>Clean</b></button>
                            
                            <div class="form-control-static">
                            	<a href="">Registrarme</a>
                            </div>
                        </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-footer" align="center"><font style="color: #111">Copyright @2014, All Rights Reserved. </font></div>
        </div>
    </div>

</body>
</html>