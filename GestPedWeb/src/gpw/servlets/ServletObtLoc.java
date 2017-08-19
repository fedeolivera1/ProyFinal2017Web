package gpw.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gpw.dominio.persona.Localidad;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;

public class ServletObtLoc extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ServletObtLoc.class);
	

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer idDep = (request.getParameter("idDep") != null ? Integer.valueOf(request.getParameter("idDep")) : -1);
			GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			List<Localidad> listaLoc = gpwStLoc.obtenerListaLocPorDep(idDep);
			final Gson gson = new Gson();
			final Type tipoListaLoc = new TypeToken<List<Localidad>>(){}.getType();
			final String listaLocJson = gson.toJson(listaLoc, tipoListaLoc);
			System.out.print(listaLocJson);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//ajax mode
			response.getWriter().write(listaLocJson);
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletObtLoc > processRequest: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletObtLoc > processRequest: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
