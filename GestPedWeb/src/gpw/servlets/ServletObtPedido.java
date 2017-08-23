package gpw.servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gpw.dominio.pedido.EstadoPedido;
import gpw.dominio.pedido.Pedido;
import gpw.dominio.usuario.UsuarioWeb;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;
import gpw.types.Fecha;

public class ServletObtPedido extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ServletPedido.class);
	
	private void processRequestGET(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		try {
			HttpSession httpSession = request.getSession();
			String usr = (String) httpSession.getAttribute("usuario");
			String estadoPedido = request.getParameter("estadoPedido");
			String fechaDesdeStr = request.getParameter("fechaDesde");
			String fechaHastaStr = request.getParameter("fechaHasta");
			Fecha fechaDesde = new Fecha(fechaDesdeStr, Fecha.DMA);
			Fecha fechaHasta = new Fecha(fechaHastaStr, Fecha.DMA);
			GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			UsuarioWeb usuario = gpwStLoc.obtenerUsuario(usr);
			List<Pedido> listaPedidosExistentes = gpwStLoc.obtenerListaPedido(EstadoPedido.getEstadoPedidoPorChar(estadoPedido.charAt(0)), usuario.getPersona().getIdPersona(), fechaDesde, fechaHasta);
			final Gson gson = new Gson();
			final Type tipoListaPed = new TypeToken<List<Pedido>>(){}.getType();
			final String listaPedJson = gson.toJson(listaPedidosExistentes, tipoListaPed);
			logger.debug("Lista de pedidos JSON: " + listaPedJson);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//ajax mode
			response.getWriter().write(listaPedJson);
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequestGET(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequestPOST(request, response);
    }
    
}
