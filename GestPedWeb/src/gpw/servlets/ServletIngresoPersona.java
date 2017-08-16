package gpw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import gpw.dominio.persona.Localidad;
import gpw.dominio.persona.PersonaFisica;
import gpw.dominio.persona.PersonaJuridica;
import gpw.dominio.persona.Sexo;
import gpw.dominio.persona.TipoDoc;
import gpw.dominio.persona.TipoPersona;
import gpw.dominio.usuario.UsuarioWeb;
import gpw.ejb.GpWebStatelessLocal;
import gpw.ejblookup.LookUps;
import gpw.exceptions.PersistenciaException;
import gpw.types.Fecha;

public class ServletIngresoPersona extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ServletIngresoPersona.class);

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String emailReg = request.getParameter("emailReg");
	        String passwdReg1 = request.getParameter("passwdReg1");
	        String tipoPers = request.getParameter("tipoPers");
	        String tipoDocStr = request.getParameter("tipoDoc");
	        Integer tipoDoc = (tipoDocStr != null ? Integer.valueOf(tipoDocStr) : null);
	        Long idPersona = Long.valueOf(request.getParameter("idPersona"));
	        //PF
	        String nombrePf1 = request.getParameter("nombrePf1");
	        String nombrePf2 = request.getParameter("nombrePf2");
	        String apellidoPf1 = request.getParameter("apellidoPf1");
	        String apellidoPf2 = request.getParameter("apellidoPf2");
	        String sexo = request.getParameter("sexo");
	        String fNac = request.getParameter("fNac");
	        //PJ
	        String nombre = request.getParameter("nombre");
	        String razonSoc = request.getParameter("razonSoc");
	        String bps = request.getParameter("bps");
	        String bse = request.getParameter("bse");
	        //PERS
	        String direccion = request.getParameter("direccion");
	        String puerta = request.getParameter("puerta");
	        String solar = request.getParameter("solar");
	        String manzana = request.getParameter("manzana");
	        String kmStr = request.getParameter("km");
	        Float km = (kmStr != null && !kmStr.equalsIgnoreCase("") ? Float.valueOf(kmStr) : null);
	        String comp = request.getParameter("comp");
	        String telefono = request.getParameter("telefono");
	        String celular = request.getParameter("celular");
	        Integer localidad = Integer.valueOf(request.getParameter("localidad"));
	        
	        Fecha fechaAct = new Fecha(Fecha.AMDHMS);
	        GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
	        UsuarioWeb usr = new UsuarioWeb();
	        usr.setNomUsu(emailReg);
	        usr.setPass(passwdReg1);
	        if(TipoPersona.F.getAsChar() == tipoPers.charAt(0)) {
	        	TipoDoc td = gpwStLoc.obtenerTipoDocPorId(tipoDoc);
	        	PersonaFisica pf = new PersonaFisica();
	        	pf.setTipoPers(TipoPersona.F);
	        	pf.setTipoDoc(td);
	        	pf.setDocumento(idPersona);
	        	pf.setApellido1(apellidoPf1);
	        	pf.setApellido2(apellidoPf2);
	        	pf.setNombre1(nombrePf1);
	        	pf.setNombre2(nombrePf2);
	        	pf.setFechaNac(new Fecha(fNac, Fecha.DMA));
	        	pf.setSexo(Sexo.getSexoPorChar(sexo.charAt(0)));
	        	usr.setPersona(pf);
	        } else {
	        	PersonaJuridica pj = new PersonaJuridica();
	        	pj.setTipoPers(TipoPersona.J);
	        	pj.setRut(idPersona);
	        	pj.setNombre(nombre);
	        	pj.setRazonSocial(razonSoc);
	        	pj.setBps(bps);
	        	pj.setBse(bse);
	        	usr.setPersona(pj);
	        }
	        usr.getPersona().setDireccion(direccion);
	        usr.getPersona().setPuerta(puerta);
	        usr.getPersona().setSolar(solar);
	        usr.getPersona().setManzana(manzana);
	        usr.getPersona().setKm(km);
	        usr.getPersona().setComplemento(comp);
	        usr.getPersona().setTelefono(telefono);
	        usr.getPersona().setCelular(celular);
	        usr.getPersona().setEmail(emailReg);
	        Localidad loc = gpwStLoc.obtenerLocalidadPorId(localidad);
	        usr.getPersona().setLocalidad(loc);
	        usr.getPersona().setFechaReg(fechaAct);
	        usr.getPersona().setUltAct(fechaAct);
	        
			Integer resultado = gpwStLoc.guardarUsuario(usr);
			if(resultado > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("usr", usr.getNomUsu());
				
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				//ajax mode
				response.getWriter().write("success");
	        } else {
	        	response.getWriter().write("error");
	        }
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletIngresoPersona > processRequest: " + e.getMessage(), e);
			response.getWriter().write("error");
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletIngresoPersona > processRequest: " + e.getMessage(), e);
			response.getWriter().write("error");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
