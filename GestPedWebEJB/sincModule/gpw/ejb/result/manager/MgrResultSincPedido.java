package gpw.ejb.result.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import gpw.dominio.pedido.Pedido;
import gpw.dominio.pedido.PedidoLinea;
import gpw.exceptions.EjbException;
import gpw.ws.datatypes.pedido.ResultObtPedidosNoSinc;
import gpw.ws.datatypes.pedido.ResultPedidoLinea;
import gpw.ws.datatypes.pedido.ResultPedidoNoSinc;

public class MgrResultSincPedido {

	private static Logger logger = Logger.getLogger(MgrResultSincPedido.class);
	
	public static ResultObtPedidosNoSinc manageResultObtPedidosNoSinc(List<Pedido> listaPedidoNoSinc) throws EjbException {
		logger.debug("Se ingresa a manejar el resultado para ResultObtPedidosNoSinc...");
		ResultObtPedidosNoSinc result = null;
		try {
			if(listaPedidoNoSinc != null && !listaPedidoNoSinc.isEmpty()) {
				result = new ResultObtPedidosNoSinc();
				List<ResultPedidoNoSinc> listaResultPedidoNoSinc = new ArrayList<>();
				for(Pedido pedido : listaPedidoNoSinc) {
					ResultPedidoNoSinc resultPns = new ResultPedidoNoSinc();
					resultPns.setIdPersona(pedido.getPersona().getIdPersona());
					resultPns.setFechaHora(pedido.getFechaHora().getAsXMLGregorianCalendar());
					resultPns.setEstado(pedido.getEstado().getEstadoPedido());
					resultPns.setFechaProg(pedido.getFechaProg() != null ? pedido.getFechaProg().getAsXMLGregorianCalendar() : null);
					resultPns.setHoraProg(pedido.getHoraProg() != null ? pedido.getHoraProg().getHoraAsXMLGregorianCalendar() : null);
					resultPns.setOrigen(String.valueOf(pedido.getOrigen().getAsChar()));
					resultPns.setSubTotal(pedido.getSubTotal());
					resultPns.setIva(pedido.getIva());
					resultPns.setTotal(pedido.getTotal());
					resultPns.setSinc(String.valueOf(pedido.getSinc().getAsChar()));
					resultPns.setUltAct(pedido.getUltAct().getAsXMLGregorianCalendar());
					resultPns.setUltAct(pedido.getUltAct().getAsXMLGregorianCalendar());
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
