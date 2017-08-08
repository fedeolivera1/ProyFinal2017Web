package gpw.db.constantes;

public interface CnstQryPedido {

	public static final String QRY_SELECT_PEDIDO_NO_SINC = "SELECT p.id_persona, p.fecha_hora, p.estado, p.fecha_prog, p.hora_prog, p.origen, "
													+ "p.sub_total, p.iva, p.total, p.sinc, p.ult_act "
												+ "FROM pedido p "
												+ "WHERE p.estado IN ('P', 'M') "
												+ "AND (p.fecha_hora::date BETWEEN ? AND ?) "
												+ "AND sinc = 'N' "
												+ "ORDER BY p.fecha_hora DESC";
	
	public static final String QRY_INSERT_PEDIDO = "INSERT INTO pedido "
												+ "(id_persona, fecha_hora, estado, fecha_prog, hora_prog, origen, "
													+ "sub_total, iva, total, sinc, ult_act) "
												+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String QRY_UPDATE_PEDIDO = "UPDATE pedido SET estado = ?, fecha_prog = ?, hora_prog = ?, sub_total = ?, iva = ?, total = ?, sinc = ?, ult_act = ? "
												+ "WHERE id_persona = ? "
												+ "AND fecha_hora = ? ";
	
	public static final String QRY_DELETE_PEDIDO = "DELETE FROM pedido WHERE id_persona = ? AND fecha_hora = ? ";
	
	public static final String QRY_CHK_EXIST_PEDIDO = "SELECT (1) FROM pedido where id_persona = ? AND fecha_hora = ?";
	
	public static final String QRY_UPDATE_SINC_PEDIDO = "UPDATE pedido SET sinc = ? "
												+ "WHERE id_persona = ? "
												+ "AND fecha_hora = ?";
}
