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

import gpw.dominio.producto.TipoProd;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;

public class ServletObtTipoProd extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ServletObtTipoProd.class);

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			List<TipoProd> listaTp = gpwStLoc.obtenerListaTipoProd();
			final Gson gson = new Gson();
			final Type tipoListaTp = new TypeToken<List<TipoProd>>(){}.getType();
			final String listaTpJson = gson.toJson(listaTp, tipoListaTp);
			System.out.print(listaTpJson);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
//			//ajax mode
			response.getWriter().write(listaTpJson);
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletObtTipoProd > processRequest: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletObtTipoProd > processRequest: " + e.getMessage(), e);
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
