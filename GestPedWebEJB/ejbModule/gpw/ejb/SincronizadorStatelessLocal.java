package gpw.ejb;

import javax.ejb.Local;

import gpw.ws.datatypes.pedido.ParamObtPedidosNoSinc;
import gpw.ws.datatypes.pedido.ParamRecPedidosASinc;
import gpw.ws.datatypes.pedido.ResultObtPedidosNoSinc;
import gpw.ws.datatypes.pedido.ResultRecPedidosASinc;
import gpw.ws.datatypes.persona.ParamObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ParamRecPersonasASinc;
import gpw.ws.datatypes.persona.ResultObtPersonasNoSinc;
import gpw.ws.datatypes.persona.ResultRecPersonasASinc;
import gpw.ws.datatypes.producto.ParamRecProductosASinc;
import gpw.ws.datatypes.producto.ResultRecProductosASinc;

@Local
public interface SincronizadorStatelessLocal {
	
	public String servicioFuncional();

	public ResultObtPersonasNoSinc obtPersonasNoSinc(ParamObtPersonasNoSinc param);
	public ResultRecPersonasASinc recPersonasASinc(ParamRecPersonasASinc param);
	
	public ResultRecProductosASinc recProductosASinc(ParamRecProductosASinc param);

	public ResultObtPedidosNoSinc obtPedidosNoSinc(ParamObtPedidosNoSinc param);
	public ResultRecPedidosASinc recPedidosASinc(ParamRecPedidosASinc param);
}
