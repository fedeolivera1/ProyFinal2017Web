package gpw.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import gpw.dominio.pedido.EstadoPedido;
import gpw.dominio.pedido.Pedido;
import gpw.dominio.pedido.PedidoLinea;
import gpw.dominio.producto.Producto;
import gpw.dominio.usuario.UsuarioWeb;
import gpw.dominio.util.Converters;
import gpw.dominio.util.Origen;
import gpw.dominio.util.Sinc;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;
import gpw.types.Fecha;

public class ServletPedido extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ServletPedido.class);
	private final static String CHAR_EMPTY = "";
	private final static String CHAR_SPLIT_PEDIDO = "~";
	private final static String CHAR_SPLIT_LINEA = ";";

	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequestGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	/**
	 * request que recibe el metodo POST, el cual manda el NUEVO pedido a ingresarse a la base de datos
	 * en caso de no haber problemas.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequestPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer resultado = null;
		try {
			String pedidoStr = request.getParameter("pedido");
			String fechaHoraProgStr = request.getParameter("fechaHoraProg");
			logger.info("Llega pedido servlet para procesar: " + pedidoStr);
			if(pedidoStr != null && !pedidoStr.isEmpty()) {
				GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
				HttpSession httpSession = request.getSession();
				//obtengo usuario de sesion para conocer la persona
				String usr = (String) httpSession.getAttribute("usuario");
				Pedido pedido = new Pedido();
				//levanto obj usuarioweb de persistencia a partir del usuario de sesion
				UsuarioWeb usuario = gpwStLoc.obtenerUsuario(usr);
				pedido.setPersona(usuario.getPersona());
				pedido.setFechaHora(new Fecha(Fecha.AMDHMS));
				pedido.setEstado(EstadoPedido.P);
				String[] pedidosSpl = pedidoStr.split(CHAR_SPLIT_PEDIDO);//Hago split para separar las lineas
				if(fechaHoraProgStr != null && !fechaHoraProgStr.equals(CHAR_EMPTY)) {
					Fecha fechaProg = new Fecha(fechaHoraProgStr, Fecha.DMA);
					Fecha horaProg = new Fecha(fechaHoraProgStr, Fecha.HM);
					pedido.setFechaProg(fechaProg);
					pedido.setHoraProg(horaProg);
				}

				Double total = new Double(0);
				for (int i=0; i<pedidosSpl.length; i++) {
					String[] pedidoLinea = pedidosSpl[i].split(CHAR_SPLIT_LINEA);//Se hace un split para separar los valores de cada linea
					if(pedidoLinea != null && !pedidoLinea.equals(CHAR_EMPTY)) {
						PedidoLinea pl = new PedidoLinea(pedido);
						Producto prod = gpwStLoc.obtenerProductoPorId(Integer.valueOf(pedidoLinea[0]));
						Integer cant = Integer.valueOf(pedidoLinea[1]);
						pl.setProducto(prod);
						pl.setPrecioUnit(prod.getPrecioVta());
						pl.setCantidad(cant);
						pedido.getListaPedidoLinea().add(pl);
						total += (prod.getPrecioVta()*cant);
					} else {
						throw new Exception("Ha surgido un error al ingresar el pedido. Verifique lineas y/o cabezal.");
					}
				}
				total = Converters.redondearDosDec(total);
				pedido.setTotal(total);
				pedido.setOrigen(Origen.W);
				pedido.setSinc(Sinc.N);
				pedido.setUltAct(new Fecha(Fecha.AMDHMS));
				resultado = gpwStLoc.guardarPedido(pedido);
				if(resultado > 0) {
					response.setContentType("text/plain");
					response.setCharacterEncoding("UTF-8");
					//ajax mode
					response.getWriter().write("success");
				} else {
					response.getWriter().write("warning");
				}
			} else {
				response.getWriter().write("warning");
			}
			
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletPedido > processRequestPOST: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletPedido > processRequestPOST: " + e.getMessage(), e);
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
