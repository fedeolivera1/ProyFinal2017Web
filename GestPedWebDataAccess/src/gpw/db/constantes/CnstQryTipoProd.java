package gpw.db.constantes;

public interface CnstQryTipoProd {

	public static final String QRY_SELECT_TIPOPROD_X_ID = "SELECT id_tipo_prod, descripcion, sinc, activo "
													+ "FROM tipo_prod "
													+ "WHERE id_tipo_prod = ? "
													+ "AND activo = 1";
	
	public static final String QRY_SELECT_TIPOPROD = "SELECT id_tipo_prod, descripcion, sinc, activo "
													+ "FROM tipo_prod "
													+ "WHERE activo = 1 "
													+ "ORDER BY id_tipo_prod";
	
	public static final String QRY_INSERT_TIPOPROD = "INSERT INTO tipo_prod (id_tipo_prod, descripcion, sinc, activo) VALUES (?, ?, ?, ?)";
	
	public static final String QRY_UPDATE_TIPOPROD = "UPDATE tipo_prod SET descripcion = ?, sinc = ? WHERE id_tipo_prod = ?";
	
//	public static final String QRY_UPDATE_SINC_TIPOPROD = "UPDATE tipo_prod SET sinc = ? WHERE id_tipo_prod = ?";
	
	public static final String QRY_DELETE_TIPOPROD = "DELETE FROM tipo_prod WHERE id_tipo_prod = ?";
	
	public static final String QRY_CHECK_EXIST_TIPOPROD = "SELECT (1) AS existe FROM tipo_prod WHERE id_tipo_prod = ? AND activo = 1";
	
}
