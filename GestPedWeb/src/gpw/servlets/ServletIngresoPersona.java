package gpw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gpw.dominio.usuario.UsuarioWeb;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;

public class ServletIngresoPersona extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String emailReg = request.getParameter("emailReg");
	        String passwdReg1 = request.getParameter("passwdReg1");
	        String nombrePf1 = request.getParameter("nombrePf1");
	        String nombrePf2 = request.getParameter("nombrePf2");
	        String apellidoPf1 = request.getParameter("apellidoPf1");
	        String apellidoPf2 = request.getParameter("apellidoPf2");
	        String direccion = request.getParameter("direccion");
	        String puerta = request.getParameter("puerta");
	        String solar = request.getParameter("solar");
	        String manzana = request.getParameter("manzana");
	        String km = request.getParameter("km");
	        String telefono = request.getParameter("telefono");
	        String celular = request.getParameter("celular");
	        Integer localidad = Integer.valueOf(request.getParameter("localidad"));
	        
	        GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
	        UsuarioWeb usr = new UsuarioWeb();
	        usr.setNomUsu(emailReg);
	        usr.setPass(passwdReg1);
			Integer resultado = gpwStLoc.guardarUsuario(usr);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
