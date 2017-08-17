package gpw.db.constantes;

public interface CnstQryUsuario {

	public static final String QRY_LOGIN = "SELECT nom_usu FROM usr_web WHERE nom_usu = ? AND passwd = ?";
	
	public static final String QRY_OBT_USR = "SELECT nom_usu, id_persona FROM usr_web WHERE nom_usu = ?";
	
	public static final String QRY_OBT_USR_PERSACT = "SELECT id_persona FROM usr_web WHERE nom_usu = ?";
	
	public static final String QRY_INSERT_USR = "INSERT INTO usr_web (nom_usu, passwd, id_persona) VALUES (?, ?, ?)";
	
	public static final String QRY_UPDATE_USR_SP = "UPDATE usr_web SET id_persona = ? WHERE nom_usu = ?";
	
	public static final String QRY_UPDATE_USR_CP = "UPDATE usr_web SET passwd = ?, id_persona = ? WHERE nom_usu = ?";
	
	public static final String QRY_DELETE_USR = "DELETE FROM usr_web WHERE nom_usu = ?";
	
}
