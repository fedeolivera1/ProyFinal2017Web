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
													+ "AND p.fecha_reg::date BETWEEN ? AND ?";
	
	public static final String QRY_SELECT_PJ_NOSINC = "SELECT pj.rut, pj.nombre, pj.razon_social, pj.bps, pj.bse, pj.es_prov, p.direccion, p.puerta, p.solar, "
														+ "p.manzana, p.km, p.complemento, p.telefono, p.celular, p.email, p.fecha_reg, p.tipo, "
														+ "p.id_loc, p.origen, p.sinc, p.ult_act "
													+ "FROM pers_juridica pj "
													+ "INNER JOIN persona p "
													+ "ON pj.rut = p.id_persona "
													+ "WHERE p.sinc = 'N'"
													+ "AND p.fecha_reg::date BETWEEN ? AND ?";
	
	public static final String QRY_UPDATE_PERS_SINC = "UPDATE persona SET sinc = ?, ult_act = ? "
													+ "WHERE id_persona = ?";
	
	public static final String QRY_SELECT_PERS_GENERIC = "SELECT tipo FROM persona WHERE id_persona = ?";
}
