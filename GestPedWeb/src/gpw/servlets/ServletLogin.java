package gpw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gpw.dominio.usuario.UsuarioWeb;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;


public class ServletLogin extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;

	public ServletLogin() {
	}
	
	protected void processRequestGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void processRequestPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String nomUsuario = request.getParameter("txtNomUsu");
			String passWd = request.getParameter("txtPassWd");
			GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			UsuarioWeb usr = gpwStLoc.obtenerUsuario(nomUsuario, passWd);
			if(usr != null) {
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usr.getNomUsu());
				session.setAttribute("usr", usr);
				session.setAttribute("id", session.getId());
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				//ajax mode
				response.getWriter().write("success");
			} else {//if name&pass not match then it display error page//
				response.getWriter().write("error");
			}
			
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequestGET(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequestPOST(request, response);
    }

}
