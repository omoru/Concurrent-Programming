package users_src;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable{
	
	private String id_usuario;
	private String ip_usuario;
	private ArrayList<String> ficheros;
	
	public Usuario(String id, String ip, ArrayList<String> ficheros) {
		this.id_usuario = id;
		this.ip_usuario = ip;
		this.ficheros = ficheros;
	}
	public String getIdUsuario() {
		return id_usuario;
	}
	
	public String getIpUsuario() {
		return ip_usuario;
	}

	public ArrayList<String> getFicheros() {
		return ficheros;
	}
}
