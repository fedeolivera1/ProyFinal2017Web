package gpw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;


public class ServletLogin extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ServletLogin.class);

	public ServletLogin() {
	}
	
	protected void processRequestGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void processRequestPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nomUsuario = request.getParameter("txtNomUsu");
			String passWd = request.getParameter("txtPassWd");
			GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			String usr = gpwStLoc.loginUsuario(nomUsuario, passWd);
			if(usr != null) {
				HttpSession session = request.getSession();
				session.setAttribute("usuario", usr);
				session.setAttribute("id", session.getId());
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				//ajax mode
				response.getWriter().write("success");
			} else {
				//devuelvo nodata porque no se pudo chequear logueo
				response.getWriter().write("nodata");
			}
			
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletLogin > processRequestPOST: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletLogin > processRequestPOST: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequestGET(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequestPOST(request, response);
    }

}
