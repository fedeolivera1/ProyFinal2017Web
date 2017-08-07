package gpw.ejb.result.parsers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.dominio.pedido.Pedido;
import gpw.dominio.pedido.PedidoLinea;
import gpw.exceptions.EjbException;
import gpw.types.Fecha;
import gpw.ws.datatypes.pedido.ResultObtPedidosNoSinc;
import gpw.ws.datatypes.pedido.ResultPedidoLinea;
import gpw.ws.datatypes.pedido.ResultPedidoNoSinc;

public class ParserResultSincPedido {

	private static Logger logger = Logger.getLogger(ParserResultSincPedido.class);
	
	public static ResultObtPedidosNoSinc parseResultObtPedidosNoSinc(List<Pedido> listaPedidoNoSinc) throws EjbException {
		logger.debug("Se ingresa a manejar el resultado para ResultObtPedidosNoSinc...");
		ResultObtPedidosNoSinc result = null;
		try {
			if(listaPedidoNoSinc != null && !listaPedidoNoSinc.isEmpty()) {
				result = new ResultObtPedidosNoSinc();
				List<ResultPedidoNoSinc> listaResultPedidoNoSinc = new ArrayList<>();
				for(Pedido pedido : listaPedidoNoSinc) {
					ResultPedidoNoSinc resultPns = new ResultPedidoNoSinc();
					resultPns.setIdPersona(pedido.getPersona().getIdPersona());
					resultPns.setFechaHora(pedido.getFechaHora().getAsXMLGregorianCalendar(Fecha.AMDHMS));
					resultPns.setEstado(pedido.getEstado().getEstadoPedido());
					resultPns.setFechaProg(pedido.getFechaProg() != null ? pedido.getFechaProg().getAsXMLGregorianCalendar(Fecha.AMD) : null);
					resultPns.setHoraProg(pedido.getHoraProg() != null ? pedido.getHoraProg().getHoraAsXMLGregorianCalendar() : null);
					resultPns.setOrigen(String.valueOf(pedido.getOrigen().getAsChar()));
					resultPns.setSubTotal(pedido.getSubTotal());
					resultPns.setIva(pedido.getIva());
					resultPns.setTotal(pedido.getTotal());
					resultPns.setSinc(String.valueOf(pedido.getSinc().getAsChar()));
					resultPns.setUltAct(pedido.getUltAct().getAsXMLGregorianCalendar(Fecha.AMDHMS));
					for(PedidoLinea pl : pedido.getListaPedidoLinea()) {
						ResultPedidoLinea rpl = new ResultPedidoLinea();
						rpl.setIdProducto(pl.getProducto().getIdProducto());
						rpl.setCantidad(pl.getCantidad());
						rpl.setIva(pl.getIva());
						rpl.setPrecioUnit(pl.getPrecioUnit());
						resultPns.getListaPedidoLinea().add(rpl);
					}
					listaResultPedidoNoSinc.add(resultPns);
				}
				result.setListaPedidoNoSinc(listaResultPedidoNoSinc);
			}
		} catch (Exception e) {
			logger.error("Excepcion generica al parsear datos en 'MgrResultSincPedido' > manageResultObtPersonasNoSinc: " + e.getMessage());
			throw new EjbException(e);
		}
		return result;
	}
}
