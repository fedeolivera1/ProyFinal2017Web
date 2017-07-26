package gpw.db.constantes;

public interface CnstQryUnidad {

	public static final String QRY_SELECT_UNI_X_ID = "SELECT id_unidad, nombre FROM unidad WHERE id_unidad = ?";
	
	public static final String QRY_SELECT_UNI = "SELECT id_unidad, nombre FROM unidad ORDER BY id_unidad";
	
	public static final String QRY_INSERT_UNI = "INSERT INTO unidad (nombre) VALUES (?)";
	
	public static final String QRY_DELETE_UNI = "DELETE FROM unidad WHERE id_unidad = ?";
	
	public static final String QRY_CHECK_EXIST_UNIDAD = "SELECT (1) AS existe FROM unidad WHERE id_unidad = ?";
}
