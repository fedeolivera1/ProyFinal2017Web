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
	
	private static final String TIPO_REQ_OBJ = "O";
	private static final String TIPO_REQ_LIST = "L";

	
	/**
	 * obtiene lista de productos a partir del tipo
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequestPOST_list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer idTipoProd = ((request.getParameter("idTipoProd") != null && !request.getParameter("idTipoProd").equals("null")) ? 
					Integer.valueOf(request.getParameter("idTipoProd")) : -1);
			if(idTipoProd != null) {
				GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
				List<Producto> listaProd = gpwStLoc.obtenerListaProductoPorTipo(idTipoProd);
				final Gson gson = new Gson();
				final Type tipoListaProd = new TypeToken<List<Producto>>(){}.getType();
				final String listaProdJson = gson.toJson(listaProd, tipoListaProd);
				logger.debug("Lista de productos JSON: " + listaProdJson);
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				//ajax mode
				response.getWriter().write(listaProdJson);
			}
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletObtProducto > processRequestPOST: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletObtProducto > processRequestPOST: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}
	
	/**
	 * obtiene objeto producto a partir del id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequestPOST_obj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String tipoReq = request.getParameter("tipoRequest");
		if(TIPO_REQ_OBJ.equals(tipoReq)) {
			processRequestPOST_obj(request, response);
		} else if(TIPO_REQ_LIST.equals(tipoReq)) {
			processRequestPOST_list(request, response);
		} else {
			response.setStatus(500);
			response.getWriter().write("Implementacion desconocida para ServletObtProducto");
		}
    }
}
