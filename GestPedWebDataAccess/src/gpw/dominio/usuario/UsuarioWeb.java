package gpw.dominio.usuario;

import java.io.Serializable;

import gpw.dominio.persona.Persona;

public class UsuarioWeb implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nomUsu;
	private String pass;
	private Persona persona;
	

	public UsuarioWeb(String nomUsur, String pass){
		this.nomUsu = nomUsur;
		this.pass = pass;
	}
	
	public UsuarioWeb() {
	}

	
	public String getNomUsu() {
		return nomUsu;
	}
	public void setNomUsu(String nomUsu) {
		this.nomUsu = nomUsu;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return nomUsu;
	}

}
