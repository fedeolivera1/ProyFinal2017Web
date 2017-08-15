package gpw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	        Float km = (kmStr != null ? Float.valueOf(kmStr) : null);
	        String complemento = request.getParameter("complemento");
	        String telefono = request.getParameter("telefono");
	        String celular = request.getParameter("celular");
	        Integer localidad = Integer.valueOf(request.getParameter("localidad"));
	        
	        UsuarioWeb usr = new UsuarioWeb();
	        usr.setNomUsu(emailReg);
	        usr.setPass(passwdReg1);
	        if(TipoPersona.F.equals(tipoPers)) {
	        	PersonaFisica pf = new PersonaFisica();
	        	pf.setTipoPers(TipoPersona.F);
	        	TipoDoc td = new TipoDoc();
	        	td.setIdTipoDoc(tipoDoc);
	        	pf.setTipoDoc(td);
	        	pf.setDocumento(idPersona);
	        	pf.setApellido1(apellidoPf1);
	        	pf.setApellido2(apellidoPf2);
	        	pf.setNombre1(nombrePf1);
	        	pf.setNombre2(nombrePf2);
	        	pf.setFechaNac(new Fecha(fNac, Fecha.DMA));
	        	pf.setSexo(Sexo.getSexoPorChar(sexo.charAt(0)));
	        	//pers
	        	pf.setDireccion(direccion);
	        	pf.setPuerta(puerta);
	        	pf.setSolar(solar);
	        	pf.setManzana(manzana);
	        	pf.setKm(km);
	        	pf.setComplemento(complemento);
	        	pf.setTelefono(telefono);
	        	pf.setCelular(celular);
	        	pf.setEmail(emailReg);
	        	Localidad loc = new Localidad();
	        	loc.setIdLocalidad(localidad);
	        	
	        	usr.setPersona(pf);
	        } else {
	        	PersonaJuridica pj = new PersonaJuridica();
	        	pj.setTipoPers(TipoPersona.J);
	        	pj.setRut(idPersona);
	        	pj.setNombre(nombre);
	        	pj.setRazonSocial(razonSoc);
	        	pj.setBps(bps);
	        	pj.setBse(bse);
	        	//pers
	        	pj.setDireccion(direccion);
	        	pj.setPuerta(puerta);
	        	pj.setSolar(solar);
	        	pj.setManzana(manzana);
	        	pj.setKm(km);
	        	pj.setComplemento(complemento);
	        	pj.setTelefono(telefono);
	        	pj.setCelular(celular);
	        	pj.setEmail(emailReg);
	        	Localidad loc = new Localidad();
	        	loc.setIdLocalidad(localidad);
	        	
	        	usr.setPersona(pj);
	        }
	        
	        GpWebStatelessLocal gpwStLoc = LookUps.lookUpGpWebStateless();
			Integer resultado = gpwStLoc.guardarUsuario(usr);
		} catch (PersistenciaException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
