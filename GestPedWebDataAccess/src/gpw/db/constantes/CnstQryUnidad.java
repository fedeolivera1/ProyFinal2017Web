package gpw.db.constantes;

public interface CnstQryUnidad {

	public static final String QRY_SELECT_UNI_X_ID = "SELECT id_unidad, nombre, sinc, activo "
													+ "FROM unidad "
													+ "WHERE id_unidad = ? AND activo = 1";

	public static final String QRY_SELECT_UNI = "SELECT id_unidad, nombre, sinc,activo "
													+ "FROM unidad "
													+ "WHERE activo = 1 "
													+ "ORDER BY id_unidad";

	public static final String QRY_INSERT_UNI = "INSERT INTO unidad (id_unidad, nombre, sinc, activo) VALUES (?, ?, ?, ?)";

	public static final String QRY_UPDATE_UNI = "UPDATE unidad SET nombre = ?, sinc = ? WHERE id_unidad = ?";

//public static final String QRY_UPDATE_SINC_UNI = "UPDATE unidad SET sinc = ? WHERE id_unidad = ?";

	public static final String QRY_DELETE_UNI = "UPDATE unidad SET activo = ? WHERE id_unidad = ?";
	
	public static final String QRY_CHECK_EXIST_UNIDAD = "SELECT (1) AS existe FROM unidad WHERE id_unidad = ? AND activo = 1";
}
