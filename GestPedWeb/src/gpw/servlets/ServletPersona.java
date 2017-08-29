package gpw.servlets;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

public class ServletPersona extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ServletPersona.class);

	/**
	 * metodo que obtiene la persona a partir del usuario logueado
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequestGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String usr = (String) session.getAttribute("usuario");
			if(usr != null) {
				GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
				UsuarioWeb usuario = gpwStLoc.obtenerUsuario(usr);
				final Gson gson = new Gson();
				final Type tipoUsr = new TypeToken<UsuarioWeb>(){}.getType();
				final String usrJson = gson.toJson(usuario, tipoUsr);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(usrJson);
			} else {
				response.getWriter().write("nodata");
			}
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletPersona > processRequestGET: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletPersona > processRequestGET: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}
	
	/**
	 * metodo que mantiene la persona a partir de los parametros ingresados
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void processRequestPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	        String nombrePj = request.getParameter("nombrePj");
	        String razonSoc = request.getParameter("razonSoc");
	        String bps = request.getParameter("bps");
	        String bse = request.getParameter("bse");
	        String esProvStr = request.getParameter("esProv");
	        Boolean esProv = (esProvStr != null ? Boolean.valueOf(esProvStr) : false);
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
	        	pj.setNombre(nombrePj);
	        	pj.setRazonSocial(razonSoc);
	        	pj.setBps(bps);
	        	pj.setBse(bse);
	        	pj.setEsProv(esProv);
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
	        
	        HttpSession session = request.getSession();
	        String usrSession = (String) session.getAttribute("usuario");
	        Integer resultado = 0;
	        Boolean existePersona = false;
	        if(usrSession != null && usrSession.equalsIgnoreCase(usr.getNomUsu())) {
	        	//usuario existente, modifica
	        	Boolean modifPasswd = false;
	        	if(passwdReg1 != null && !passwdReg1.equals("")) {
	        		usr.setPass(passwdReg1);
	        		modifPasswd = true;
	        	}
	        	resultado = gpwStLoc.modificarUsuario(usr, modifPasswd);
	        } else {
	        	//usuario nuevo, agrega y setea sesion
	        	existePersona = gpwStLoc.checkExistPersona(usr.getPersona().getIdPersona()); 
	        	if(!existePersona) {
	        		usr.setPass(passwdReg1);
	        		resultado = gpwStLoc.guardarUsuario(usr);
	        		session.setAttribute("usuario", usr.getNomUsu());
	        		session.setAttribute("id", session.getId());
	        	}
	        }
	        
			if(resultado > 0) {
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("success");
	        } else {
	        	if(existePersona) {
	        		response.getWriter().write("exists");
	        	} else {
	        		response.getWriter().write("warning");
	        	}
	        }
		} catch (PersistenciaException e) {
			logger.fatal("Excepcion en ServletPersona > processRequestPOST: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.fatal("Excepcion genérica en ServletPersona > processRequestPOST: " + e.getMessage(), e);
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequestPOST(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequestGET(request, response);
	}
}
