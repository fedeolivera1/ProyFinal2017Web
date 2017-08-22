package gpw.db.constantes;

public interface CnstQryPedidoLinea {

	public static final String QRY_SELECT_PL = "SELECT pl.id_persona, pl.fecha_hora, pl.id_producto, pl.cantidad, pl.precio_unit "
												+ "FROM pedido_linea pl "
												+ "INNER JOIN pedido p "
												+ "ON pl.id_persona = p.id_persona "
												+ "AND pl.fecha_hora = p.fecha_hora "
												+ "WHERE pl.id_persona = ? "
												+ "AND pl.fecha_hora = ?";
	
	public static final String QRY_INSERT_PL = "INSERT INTO pedido_linea "
												+ "(id_persona, fecha_hora, id_producto, cantidad, precio_unit) "
												+ "VALUES (?, ?, ?, ?, ?)";
	
	public static final String QRY_DELETE_PL = "DELETE FROM pedido_linea WHERE id_persona = ? AND fecha_hora = ?";
	
}
