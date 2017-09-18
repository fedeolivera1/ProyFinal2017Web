package gpw.servlets;

import java.io.IOException;
import java.util.ArrayList;

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
	
	private String tipoPedido = "";
	private Integer resultado = null;
	private final static String TIPO_PEDIDO_NUEVO = "N";
	private final static String TIPO_PEDIDO_EXISTENTE = "E";
	private final static String CHAR_EMPTY = "";
	private final static String CHAR_SPLIT_PEDIDO = "~";
	private final static String CHAR_SPLIT_LINEA = ";";
	private final static String ACCION_PRECONF = "F";
	private final static String ACCION_RECHAZAR = "X";
	private final static String ACCION_ANULAR = "A";
	private final static String ACCION_ACTUALIZAR = "U";
	

	/**
	 * request que recibe el metodo POST, el cual manda el NUEVO/EXISTENTE pedido a ingresarse a la base de datos
	 * en caso de no haber problemas.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequestPOST_nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				pedido.setEstado(EstadoPedido.P);// pedido PENDIENTE
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
					response.getWriter().write("success");
				} else {
					response.getWriter().write("warning");
				}
			} else {
				response.getWriter().write("warning");
			}
			
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletPedido > processRequestPOST_nuevo: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletPedido > processRequestPOST_nuevo: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}
	
	private void processRequestPOST_existente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String keyPedido = request.getParameter("keyPedido");
			String accion = request.getParameter("accion"); 
			
			String[] keyPedidoSpl = keyPedido.split(";");
			Long idPersona = Long.valueOf(keyPedidoSpl[0]);
			String fechaHoraStr = keyPedidoSpl[1];
			Fecha fechaHora = new Fecha(fechaHoraStr, Fecha.AMDHMS);
			GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			Pedido pedido = gpwStLoc.obtenerPedidoPorId(idPersona, fechaHora);
			if(pedido != null) {
			
				/*caso modifica pedido PENDIENTE (solo modifica el pedido, no modifica su estado, vuelve a sinc = 'N')*/
				if( EstadoPedido.P.equals(pedido.getEstado()) && ACCION_ACTUALIZAR.equalsIgnoreCase(accion) ) {
					
					String pedidoStr = request.getParameter("pedido");
					String fechaHoraProgStr = request.getParameter("fechaHoraProg");
					if(pedidoStr != null && !pedidoStr.isEmpty()) {
						String[] pedidosSpl = pedidoStr.split(CHAR_SPLIT_PEDIDO);//Hago split para separar las lineas
						ArrayList<PedidoLinea> listaLineasNuevas = new ArrayList<>();
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
								listaLineasNuevas.add(pl);
								total += (prod.getPrecioVta()*cant);
							} else {
								throw new Exception("Ha surgido un error al ingresar el pedido. Verifique lineas y/o cabezal.");
							}
						}
						total = Converters.redondearDosDec(total);
						pedido.setTotal(total);
						pedido.setSinc(Sinc.N);
						pedido.setUltAct(new Fecha(Fecha.AMDHMS));
						resultado = gpwStLoc.modificarPedido(pedido, listaLineasNuevas);
						if(resultado > 0) {
							response.setContentType("text/plain");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write("success");
						} else {
							response.getWriter().write("warning");
						}
					} else {
						response.getWriter().write("error");
					}
				/*caso pedido en estado REVISION, para PRECONFIRMAR o RECHAZAR*/
				} else if( EstadoPedido.R.equals(pedido.getEstado()) && 
						(ACCION_PRECONF.equalsIgnoreCase(accion) || ACCION_RECHAZAR.equalsIgnoreCase(accion)) ) {
					pedido.setEstado(EstadoPedido.getEstadoPedidoPorChar(accion.charAt(0)));
					pedido.setSinc(Sinc.N);
					pedido.setUltAct(new Fecha(Fecha.AMDHMS));
					resultado = gpwStLoc.modificarEstadoPedido(pedido);
					if(resultado > 0) {
						response.setContentType("text/plain");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write("success");
					} else {
						response.getWriter().write("warning");
					}
				/*caso pedido en estado PENDIENTE, para ANULAR*/
				} else if( EstadoPedido.P.equals(pedido.getEstado()) && ACCION_ANULAR.equalsIgnoreCase(accion) ) {
					pedido.setEstado(EstadoPedido.A);
					pedido.setSinc(Sinc.N);
					pedido.setUltAct(new Fecha(Fecha.AMDHMS));
					resultado = gpwStLoc.modificarEstadoPedido(pedido);
					if(resultado > 0) {
						response.setContentType("text/plain");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write("success");
					} else {
						response.getWriter().write("warning");
					}
				/*caso NO SOPORTADO*/
				} else {
					response.setStatus(500);
					response.getWriter().write("Pedido en error o su estado no permite modificacion.");
				}
			} else {
				response.setStatus(500);
				response.getWriter().write("Han surgido errores al obtener el pedido.");
			}
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletPedido > processRequestPOST_existente: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletPedido > processRequestPOST_existente: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	tipoPedido = request.getParameter("tipoPedido");
    	if(TIPO_PEDIDO_NUEVO.equals(tipoPedido)) {
    		processRequestPOST_nuevo(request, response);
    	} else if(TIPO_PEDIDO_EXISTENTE.equals(tipoPedido)) {
    		processRequestPOST_existente(request, response);
    	} else {
    		response.setStatus(500);
			response.getWriter().write("Implementacion desconocida para ServletPedido");
    	}
    }

}
