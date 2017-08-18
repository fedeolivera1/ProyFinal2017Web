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
import gpw.dominio.producto.Producto;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;

public class ServletObtProducto extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ServletObtProducto.class);

	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer idTipoProd = (request.getParameter("idTipoProd") != null ? Integer.valueOf(request.getParameter("idTipoProd")) : -1);
			GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			List<Producto> listaProd = gpwStLoc.obtenerListaProductoPorTipo(idTipoProd);
			final Gson gson = new Gson();
			final Type tipoListaLoc = new TypeToken<List<Producto>>(){}.getType();
			final String listaProdJson = gson.toJson(listaProd, tipoListaLoc);
			System.out.print(listaProdJson);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//ajax mode
			response.getWriter().write(listaProdJson);
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletObtLoc > processRequest: " + e.getMessage(), e);
			response.getWriter().write("error");
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletObtLoc > processRequest: " + e.getMessage(), e);
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
