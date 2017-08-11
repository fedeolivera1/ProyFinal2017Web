package gpw.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gpw.dominio.usuario.UsuarioWeb;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejb.SincronizadorStatelessLocal;
import gpw.exceptions.PersistenciaException;


public class ServletLogin extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;

	public ServletLogin() {
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomUsuario = request.getParameter("txtNomUsu");
        String passWd = request.getParameter("txtPassWd");
        
        try {
			GpWebStatelessLocal gpwStLoc = InitialContext.doLookup("java:app/GestPedWebEJB/GpWebStateless!gpw.ejb.GpWebStatelessLocal");
			UsuarioWeb usr = gpwStLoc.obtenerUsuario(nomUsuario, passWd);
			if(usr != null) {
				request.setAttribute("usr", usr.getNomUsu());
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("SUCCESS");
				
//              RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
//              rd.forward(request, response);
	        } else {//if name&pass not match then it display error page//
	            response.sendRedirect("error.jsp");
	        }
				
		} catch (NamingException | PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }

//	@EJB(lookup = "ejb:/AdditionEJB//Addition!com.logic.AdditionRemote")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
