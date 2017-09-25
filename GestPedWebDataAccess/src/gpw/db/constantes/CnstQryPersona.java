package gpw.db.constantes;

public interface CnstQryPersona {

	public static final String QRY_SELECT_PF_XID = "SELECT pf.documento, pf.id_tipo_doc, pf.apellido1, pf.apellido2, pf.nombre1, pf.nombre2, pf.fecha_nac, pf.sexo, "
														+ "p.direccion, p.puerta, p.solar, p.manzana, p.km, p.complemento, p.telefono, p.celular, "
														+ "p.email, p.fecha_reg, p.tipo, p.id_loc, p.origen, p.sinc, p.ult_act "
													+ "FROM pers_fisica pf "
													+ "INNER JOIN persona p "
													+ "on pf.documento = p.id_persona "
													+ "where p.id_persona = ?";
	
	public static final String QRY_SELECT_PJ_XID = "SELECT pj.rut, pj.nombre, pj.razon_social, pj.bps, pj.bse, pj.es_prov, p.direccion, p.puerta, p.solar, "
														+ "p.manzana, p.km, p.complemento, p.telefono, p.celular, p.email, p.fecha_reg, p.tipo, "
														+ "p.id_loc, p.origen, p.sinc, p.ult_act "
													+ "FROM pers_juridica pj "
													+ "INNER JOIN persona p "
													+ "on pj.rut = p.id_persona "
													+ "WHERE pj.rut = ?";
	
	public static final String QRY_SELECT_PF_NOSINC = "SELECT pf.documento, pf.id_tipo_doc, pf.apellido1, pf.apellido2, pf.nombre1, pf.nombre2, pf.fecha_nac, pf.sexo, "
														+ "p.direccion, p.puerta, p.solar, p.manzana, p.km, p.complemento, p.telefono, p.celular, "
														+ "p.email, p.fecha_reg, p.tipo, p.id_loc, p.origen, p.sinc, p.ult_act "
													+ "FROM pers_fisica pf "
													+ "INNER JOIN persona p "
													+ "ON pf.documento = p.id_persona "
													+ "WHERE p.sinc = 'N' "
													+ "AND p.ult_act::date BETWEEN ? AND ?";
	
	public static final String QRY_SELECT_PJ_NOSINC = "SELECT pj.rut, pj.nombre, pj.razon_social, pj.bps, pj.bse, pj.es_prov, p.direccion, p.puerta, p.solar, "
														+ "p.manzana, p.km, p.complemento, p.telefono, p.celular, p.email, p.fecha_reg, p.tipo, "
														+ "p.id_loc, p.origen, p.sinc, p.ult_act "
													+ "FROM pers_juridica pj "
													+ "INNER JOIN persona p "
													+ "ON pj.rut = p.id_persona "
													+ "WHERE p.sinc = 'N'"
													+ "AND p.ult_act::date BETWEEN ? AND ?";
	
	public static final String QRY_SELECT_PERS_GENERIC = "SELECT tipo FROM persona WHERE id_persona = ?";
	
	public static final String QRY_INSERT_PERS = "INSERT INTO persona "
													+ "(id_persona, direccion, puerta, solar, manzana, km, complemento, telefono, celular, email, fecha_reg, tipo, id_loc, origen, sinc, ult_act) "
													+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public static final String QRY_UPDATE_PERS_SINC = "UPDATE persona SET sinc = ?, ult_act = ? "
													+ "WHERE id_persona = ?";
	
	public static final String QRY_INSERT_PF = "INSERT INTO pers_fisica "
													+ "(documento, id_tipo_doc, apellido1, apellido2, nombre1, nombre2, fecha_nac, sexo) "
													+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String QRY_INSERT_PJ = "INSERT INTO pers_juridica "
													+ "(rut, nombre, razon_social, bps, bse, es_prov) "
													+ "VALUES (?, ?, ?, ?, ?, ?)";
	
	public static final String QRY_UPDATE_PERS = "UPDATE persona "
													+ "SET id_persona = ?, direccion = ?, puerta = ?, solar = ?, manzana = ?, km = ?, complemento = ?, telefono = ?, celular = ?, email = ?, fecha_reg = ?, id_loc = ?, origen = ?, sinc = ?, ult_act = ? "
													+ "WHERE id_persona = ?";//INFO: tipo no va a proposito, no se puede cambiar tipo de persona
	
	public static final String QRY_UPDATE_PF = "UPDATE pers_fisica "
													+ "SET id_tipo_doc = ?, apellido1 = ?, apellido2 = ?, nombre1 = ?, nombre2 = ?, fecha_nac = ?, sexo = ? "
													+ "WHERE documento = ?";
	
	public static final String QRY_UPDATE_PJ = "UPDATE pers_juridica "
													+ "SET nombre = ?, razon_social = ?, bps = ?, bse = ?, es_prov = ? "
													+ "WHERE rut = ?";
	
	public static final String QRY_CHECK_EXIST_PERS = "SELECT (1) AS existe FROM persona WHERE id_persona = ?";
}
