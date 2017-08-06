package gpw.ws.parsers;

import org.apache.log4j.Logger;

import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.exceptions.ParsersException;
import gpw.types.Fecha;
import gpw.ws.datatypes.persona.ResultPersonaFisica;
import gpw.ws.datatypes.persona.ResultPersonaJuridica;

public class ParserPersona {

	private static Logger logger = Logger.getLogger(ParserPersona.class);
	
	public static ResultPersonaFisica parsePersonaFisica(PersonaFisica pf) throws ParsersException {
		ResultPersonaFisica rpf = null;
		try {
			if(pf != null) {
				rpf = new ResultPersonaFisica();
				rpf.setDocumento(pf.getDocumento());
				rpf.setTipoDoc(pf.getTipoDoc().getIdTipoDoc());
				rpf.setTipoDocDesc(pf.getTipoDoc().getNombre());
				rpf.setApellido1(pf.getApellido1());
				rpf.setApellido2(pf.getApellido2());
				rpf.setNombre1(pf.getNombre1());
				rpf.setNombre2(pf.getNombre2());
				rpf.setFechaNac(pf.getFechaNac().getAsXMLGregorianCalendar(Fecha.AMD));
				rpf.setSexo(String.valueOf(pf.getSexo().getAsChar()));
				rpf.setDocumentoAnt(null);
				//persona
				rpf.setDireccion(pf.getDireccion());
				rpf.setPuerta(pf.getPuerta());
				rpf.setSolar(pf.getSolar());
				rpf.setManzana(pf.getManzana());
				rpf.setKm(pf.getKm());
				rpf.setComplemento(pf.getComplemento());
				rpf.setTelefono(pf.getTelefono());
				rpf.setCelular(pf.getCelular());
				rpf.setEmail(pf.getEmail());
				rpf.setFechaReg(pf.getFechaReg().getAsXMLGregorianCalendar(Fecha.AMD));
				rpf.setTipoPers(String.valueOf(pf.getTipoPers().getAsChar()));
				rpf.setLocalidad(pf.getLocalidad().getIdLocalidad());
				rpf.setOrigen(String.valueOf(pf.getOrigen().getAsChar()));
				rpf.setSinc(String.valueOf(pf.getSinc().getAsChar()));
				rpf.setUltAct(pf.getUltAct().getAsXMLGregorianCalendar(Fecha.AMDHMS));
			}
		} catch (Exception e) {
			logger.error("Excepcion al parsear en ParserPersona: ", e);
			throw new ParsersException(e);
		}
		return rpf;
	}
	
	public static ResultPersonaJuridica parsePersonaJuridica(PersonaJuridica pj) throws ParsersException {
		ResultPersonaJuridica rpj = null;
		try {
			if(pj != null) {
				rpj = new ResultPersonaJuridica();
				rpj.setRut(pj.getRut());
				rpj.setNombre(pj.getNombre());
				rpj.setRazonSocial(pj.getRazonSocial());
				rpj.setBps(pj.getBps());
				rpj.setBse(pj.getBse());
				rpj.setEsProv(pj.getEsProv());
				rpj.setRutAnt(null);
				//persona
				rpj.setDireccion(pj.getDireccion());
				rpj.setPuerta(pj.getPuerta());
				rpj.setSolar(pj.getSolar());
				rpj.setManzana(pj.getManzana());
				rpj.setKm(pj.getKm());
				rpj.setComplemento(pj.getComplemento());
				rpj.setTelefono(pj.getTelefono());
				rpj.setCelular(pj.getCelular());
				rpj.setEmail(pj.getEmail());
				rpj.setFechaReg(pj.getFechaReg().getAsXMLGregorianCalendar(Fecha.AMD));
				rpj.setTipoPers(String.valueOf(pj.getTipoPers().getAsChar()));
				rpj.setLocalidad(pj.getLocalidad().getIdLocalidad());
				rpj.setOrigen(String.valueOf(pj.getOrigen().getAsChar()));
				rpj.setSinc(String.valueOf(pj.getSinc().getAsChar()));
				rpj.setUltAct(pj.getUltAct().getAsXMLGregorianCalendar(Fecha.AMDHMS));
			}
		} catch (Exception e) {
			logger.error("Excepcion al parsear en ParserPersona: ", e);
			throw new ParsersException(e);
		}
		return rpj;
	}
}
