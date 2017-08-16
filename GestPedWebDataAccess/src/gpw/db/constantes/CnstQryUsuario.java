package gpw.db.constantes;

public interface CnstQryUsuario {

	public static final String QRY_LOGIN = "SELECT nom_usu, id_persona FROM usr_web WHERE nom_usu = ? AND passwd = ?";
	
	public static final String QRY_INSERT_USR = "INSERT INTO usr_web (nom_usu, passwd, id_persona) VALUES (?, ?, ?)";
	
	public static final String QRY_UPDATE_USR = "UPDATE usr_web SET passwd = ? WHERE nom_usu = ?";
	
	public static final String QRY_DELETE_USR = "DELETE FROM usr_web WHERE nom_usu = ?";
	
}
