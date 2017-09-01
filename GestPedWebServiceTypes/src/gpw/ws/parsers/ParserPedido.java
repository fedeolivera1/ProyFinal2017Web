package gpw.ws.parsers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.dominio.pedido.EstadoPedido;
import gpw.dominio.pedido.Pedido;
import gpw.dominio.pedido.PedidoLinea;
import gpw.dominio.producto.Producto;
import gpw.dominio.util.Origen;
import gpw.dominio.util.Sinc;
import gpw.exceptions.ParsersException;
import gpw.types.Fecha;
import gpw.ws.datatypes.pedido.ParamPedidoASinc;
import gpw.ws.datatypes.pedido.ParamPedidoLinea;
import gpw.ws.datatypes.pedido.ParamRecPedidosASinc;

public class ParserPedido {

	private static Logger logger = Logger.getLogger(ParserPedido.class);
	
	public static List<Pedido> parsePedido(ParamRecPedidosASinc param) throws ParsersException {
		List<Pedido> listaPedido = new ArrayList<>();
		try {
			if(param.getListaPedidosASinc() != null && !param.getListaPedidosASinc().isEmpty()) {
				for(ParamPedidoASinc paramPas : param.getListaPedidosASinc()) {
					Pedido pedido = new Pedido();
					pedido.setIdPersTemp(paramPas.getIdPersona());
					pedido.setFechaHora(new Fecha(paramPas.getFechaHora(), Fecha.AMDHMS));
					pedido.setEstado(EstadoPedido.getEstadoPedidoPorChar(paramPas.getEstado().charAt(0)));
					pedido.setFechaProg(paramPas.getFechaProg() != null ? new Fecha(paramPas.getFechaProg(), Fecha.AMD) : null);
					pedido.setHoraProg(paramPas.getHoraProg() != null ? new Fecha(paramPas.getHoraProg(), Fecha.HMS) : null);
					pedido.setOrigen(Origen.getOrigenPorChar(paramPas.getOrigen().charAt(0)));
					pedido.setTotal(paramPas.getTotal());
					pedido.setSinc(Sinc.getSincPorChar(paramPas.getSinc().charAt(0)));
					pedido.setUltAct(new Fecha(paramPas.getUltAct(), Fecha.AMDHMS));
					for(ParamPedidoLinea paramPl : paramPas.getListaPedidoLinea()) {
						PedidoLinea pl = new PedidoLinea(pedido);
						Producto prod = new Producto();
						prod.setIdProducto(paramPl.getIdProducto());
						pl.setProducto(prod);
						pl.setCantidad(paramPl.getCantidad());
						pl.setPrecioUnit(paramPl.getPrecioUnit());
						pedido.getListaPedidoLinea().add(pl);
					}
					listaPedido.add(pedido);
				}
			} else {
				logger.info("No hay pedidos MODIFICADOS en el sist DSK para sincronizar.");
			}
		} catch (Exception e) {
			logger.error("Excepcion al parsear en ParserPedido: ", e);
			throw new ParsersException(e);
		}
		return listaPedido;
	}
}
