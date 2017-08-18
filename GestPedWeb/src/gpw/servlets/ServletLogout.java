package gpw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class ServletLogout extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ServletLogout.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String id = (request.getParameter("logout") != null ? request.getParameter("logout") : "");
	        if(id.equals(session.getId())) {
	            session.invalidate();
	            response.sendRedirect(request.getContextPath() + "/login.jsp");
	        } else {
	        	
	        }
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletLogout > processRequest: " + e.getMessage(), e);
			response.getWriter().write("error");
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
