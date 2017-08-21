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

	
	protected void processRequestPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer idTipoProd = (request.getParameter("idTipoProd") != null ? Integer.valueOf(request.getParameter("idTipoProd")) : -1);
			GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			List<Producto> listaProd = gpwStLoc.obtenerListaProductoPorTipo(idTipoProd);
			final Gson gson = new Gson();
			final Type tipoListaProd = new TypeToken<List<Producto>>(){}.getType();
			final String listaProdJson = gson.toJson(listaProd, tipoListaProd);
			System.out.print(listaProdJson);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//ajax mode
			response.getWriter().write(listaProdJson);
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletObtProducto > processRequestPOST: " + e.getMessage(), e);
			response.sendError(0, e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletObtProducto > processRequestPOST: " + e.getMessage(), e);
			response.sendError(0, e.getMessage());
		}
	}
	
	protected void processRequestGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer idProd = (request.getParameter("idProd") != null ? Integer.valueOf(request.getParameter("idProd")) : -1);
			GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			Producto prod = gpwStLoc.obtenerProductoPorId(idProd);
			final Gson gson = new Gson();
			final Type tipoProd = new TypeToken<Producto>(){}.getType();
			final String prodJson = gson.toJson(prod, tipoProd);
			System.out.print(prodJson);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//ajax mode
			response.getWriter().write(prodJson);
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletObtProducto > processRequestGET: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletObtProducto > processRequestGET: " + e.getMessage(), e);
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
