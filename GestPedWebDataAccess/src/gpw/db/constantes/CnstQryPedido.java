package gpw.db.constantes;

public interface CnstQryPedido {

	public static final String QRY_SELECT_PEDIDO_XID = "SELECT p.id_persona, p.fecha_hora, p.estado, p.fecha_prog, p.hora_prog, p.origen, "
													+ "p.total, p.sinc, p.ult_act "
												+ "FROM pedido p "
												+ "WHERE p.id_persona = ? "
												+ "AND p.fecha_hora = ? ";
	
	public static final String QRY_SELECT_PEDIDO = "SELECT p.id_persona, p.fecha_hora, p.estado, p.fecha_prog, p.hora_prog, p.origen, "
													+ "p.total, p.sinc, p.ult_act "
												+ "FROM pedido p "
												+ "WHERE (p.id_persona = ? OR -1 = ?) "
												+ "AND p.estado = ? "
												+ "AND (p.fecha_hora::date BETWEEN ? AND ?) "
												+ "ORDER BY p.fecha_hora DESC";
	
	public static final String QRY_SELECT_PEDIDO_NO_SINC = "SELECT p.id_persona, p.fecha_hora, p.estado, p.fecha_prog, p.hora_prog, p.origen, "
													+ "p.total, p.sinc, p.ult_act "
												+ "FROM pedido p "
												+ "WHERE p.estado IN ('P', 'F', 'X') "
												+ "AND (p.fecha_hora::date BETWEEN ? AND ?) "
												+ "AND sinc = 'N' "
												+ "ORDER BY p.fecha_hora DESC";
	
	public static final String QRY_INSERT_PEDIDO = "INSERT INTO pedido "
												+ "(id_persona, fecha_hora, estado, fecha_prog, hora_prog, origen, "
													+ "total, sinc, ult_act) "
												+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String QRY_UPDATE_PEDIDO = "UPDATE pedido SET estado = ?, fecha_prog = ?, hora_prog = ?, total = ?, sinc = ?, ult_act = ? "
												+ "WHERE id_persona = ? "
												+ "AND fecha_hora = ? ";
	
	public static final String QRY_UPDATE_ESTADO_PEDIDO = "UPDATE pedido SET estado = ?, sinc = ?, ult_act = ? "
												+ "WHERE id_persona = ? "
												+ "AND fecha_hora = ? ";
	
	public static final String QRY_DELETE_PEDIDO = "DELETE FROM pedido WHERE id_persona = ? AND fecha_hora = ? ";
	
	public static final String QRY_CHK_EXIST_PEDIDO = "SELECT (1) FROM pedido where id_persona = ? AND fecha_hora = ?";
	
	public static final String QRY_UPDATE_SINC_PEDIDO = "UPDATE pedido SET sinc = ?, ult_act = ? "
												+ "WHERE id_persona = ? "
												+ "AND fecha_hora = ?";
}
