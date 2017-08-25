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
	private static final String TIPO_REQ_OBJ = "O";
	private static final String TIPO_REQ_LIST = "L";
	
	/**
	 * metodo que obtiene todos los pedidos de la persona logueada por fechas y estado
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processRequestPOST_list(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		try {
			logger.debug("Llega a processRequestPOST_list en ServletObtPedido");
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
			if(listaPedidosExistentes != null && !listaPedidosExistentes.isEmpty()) {
				final Gson gson = new Gson();
				final Type tipoListaPed = new TypeToken<List<Pedido>>(){}.getType();
				final String listaPedJson = gson.toJson(listaPedidosExistentes, tipoListaPed);
				logger.debug("Lista de pedidos JSON: " + listaPedJson);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(listaPedJson);
			}
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
	
	/**
	 * metodo que recibe un string con los datos del pedido (idPersona;fechaHora), los splitea, parsea y 
	 * obtiene el pedido en base para devolverlo como JSON
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processRequestPOST_obj(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		logger.debug("Llega a processRequestPOST_obj en ServletObtPedido");
		try {
			String dataPedidoStr = request.getParameter("dataPedido");
			if(dataPedidoStr != null && !dataPedidoStr.equalsIgnoreCase("null")) {
				String[] datosPedidoSpl = dataPedidoStr.split(";");
				if(datosPedidoSpl != null && datosPedidoSpl.length == 2) {
					Long idPersona = Long.valueOf(datosPedidoSpl[0]);
					String fechaHoraStr = datosPedidoSpl[1];
					Fecha fechaHora = new Fecha(fechaHoraStr, Fecha.AMDHMS);
					GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
					Pedido pedido = gpwStLoc.obtenerPedidoPorId(idPersona, fechaHora);
					if(pedido != null) {
						final Gson gson = new Gson();
						final Type tipoPedido = new TypeToken<Pedido>(){}.getType();
						final String pedidoJson = gson.toJson(pedido, tipoPedido);
						logger.debug("Pedido JSON: " + pedidoJson);
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(pedidoJson);
					} else {
						response.setStatus(500);
						response.getWriter().write("El pedido no se ha encontrado, consulte ayuda.");
					}
				} else {
					throw new Exception("Hubo un problema del lado del servidor al procesar los datos para el pedido.");
				}
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
			response.getWriter().write("Implementacion desconocida para ServletObtPedido");
		}
    }
    
}
