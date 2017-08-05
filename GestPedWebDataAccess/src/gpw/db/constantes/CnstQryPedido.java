package gpw.db.constantes;

public interface CnstQryPedido {

	public static final String QRY_SELECT_PEDIDO_NO_SINC = "SELECT p.id_persona, p.fecha_hora, p.estado, p.fecha_prog, p.hora_prog, p.origen, "
													+ "p.sub_total, p.iva, p.total, p.sinc, p.ult_act "
												+ "FROM pedido p "
												+ "WHERE p.estado IN ('P', 'M') "
												+ "AND (p.fecha_hora::date BETWEEN ? AND ?) "
												+ "ORDER BY p.fecha_hora DESC";
	
}
