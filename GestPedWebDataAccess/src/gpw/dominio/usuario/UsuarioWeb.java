package gpw.dominio.usuario;

import java.io.Serializable;

public class UsuarioWeb implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nomUsu;
	private String pass;
	
	//Constructores
	public UsuarioWeb(String nomUsur, String pass){
		this.nomUsu = nomUsur;
		this.pass = pass;
	}
	
	public UsuarioWeb() {
	}
	
	//Set y get
	
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
	
	@Override
	public String toString() {
	    return nomUsu;
	}

}
