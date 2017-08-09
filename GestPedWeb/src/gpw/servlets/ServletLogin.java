package gpw.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletLogin extends HttpServlet { 
	
	private static final long serialVersionUID = 1L;

	public ServletLogin() {
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomUsu = request.getParameter("txtNomUsu");
        String passWd = request.getParameter("txtPassWd");
        
        if(nomUsu.equalsIgnoreCase("fede") && passWd.equalsIgnoreCase("fede")) {
              RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
              request.setAttribute("nomUsu", nomUsu);
              rd.forward(request, response);
        } else {//if name&pass not match then it display error page//
            response.sendRedirect("error.jsp");
        }
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
