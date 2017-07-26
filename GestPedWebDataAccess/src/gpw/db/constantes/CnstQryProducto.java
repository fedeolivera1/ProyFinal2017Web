package gpw.db.constantes;

public interface CnstQryProducto {

	public static final String QRY_INSERT_PROD = "INSERT INTO producto "
										+ "(id_tipo_prod, codigo, nombre, descripcion, stock_min, apl_iva, id_unidad, cant_unidad, precio, sinc, ult_act, activo) "
										+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String QRY_UPDATE_PROD = "UPDATE producto SET id_tipo_prod = ?, codigo = ?, nombre = ?, descripcion = ?, stock_min = ?, apl_iva = ?, "
										+ "id_unidad = ?, cant_unidad = ?, precio = ?, sinc = ?, ult_act = ? "
										+ "WHERE id_producto = ?";
	
	public static final String QRY_DESACT_PROD = "UPDATE producto SET activo = ? WHERE id_producto = ?";
	
	public static final String QRY_CHECK_EXIST_PROD = "SELECT (1) AS existe FROM producto WHERE id_producto = ?";
}
