package gpw.db.constantes;

public interface CnstQryUsuario {

	public static final String QRY_LOGIN = "SELECT nom_usu, passwd FROM usr_web WHERE nom_usu = ? AND passwd = ?";
	
	public static final String QRY_INSERT_USR = "INSERT INTO usr_dsk (nom_usu, passwd) VALUES (?, ?)";
	
	public static final String QRY_UPDATE_USR = "UPDATE usr_dsk SET passwd = ? WHERE nom_usu = ?";
	
	public static final String QRY_DELETE_USR = "DELETE FROM usr_dsk WHERE nom_usu = ?";
	
}
