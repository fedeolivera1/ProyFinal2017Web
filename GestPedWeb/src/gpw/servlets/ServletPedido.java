package gpw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import gpw.dominio.pedido.EstadoPedido;
import gpw.dominio.pedido.Pedido;
import gpw.dominio.pedido.PedidoLinea;
import gpw.dominio.producto.Producto;
import gpw.dominio.util.Converters;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;
import gpw.types.Fecha;

public class ServletPedido extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ServletPedido.class);
	private final static String CHAR_EMPTY = "";
	private final static String CHAR_SPLIT_PEDIDO = "|";
	private final static String CHAR_SPLIT_LINEA = "~";

	protected void processRequestGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void processRequestPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String pedidoStr = request.getParameter("pedido");
			String fechaHoraStr = request.getParameter("fechaHora");
			logger.info("Llega pedido servlet para procesar: " + pedidoStr);
			if(pedidoStr != null) {
				GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
				String[] pedidosSpl = pedidoStr.split(CHAR_SPLIT_PEDIDO);
				Pedido pedido = new Pedido();
				pedido.setFechaHora(new Fecha(Fecha.AMDHMS));
				pedido.setEstado(EstadoPedido.P);
				pedido.setFechaProg(null);
				pedido.setHoraProg(null);
				
				for (int i=0 ; i<pedidosSpl.length ; i++) {
					String[] pedidoLinea = pedidosSpl[i].split(CHAR_SPLIT_LINEA);
					if(pedidoLinea != null && !pedidoLinea.equals(CHAR_EMPTY)) {
						PedidoLinea pl = new PedidoLinea(pedido);
						Producto prod = gpwStLoc.obtenerProductoPorId(Integer.valueOf(pedidoLinea[0]));
						Integer cant = Integer.valueOf(pedidoLinea[1]);
						pl.setPrecioUnit(prod.getPrecioVta());
						pl.setCantidad(cant);
						pedido.getListaPedidoLinea().add(pl);
					} else {
						
					}
				}
			}
			
//		} catch (PersistenciaException e) {
//			logger.fatal("Excepcion en ServletPersona > processRequestPOST: " + e.getMessage(), e);
//			response.setStatus(500);
//			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletPersona > processRequestPOST: " + e.getMessage(), e);
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
