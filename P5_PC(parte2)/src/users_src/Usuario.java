package users_src;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable{
	
	private String id_usuario;
	private String ip_usuario;
	private ArrayList<String> ficheros;
	private ArrayList<String> rutas_ficheros;
	
	public Usuario(String id, String ip, ArrayList<String> ficheros) {
		this.id_usuario = id;
		this.ip_usuario = ip;
		this.ficheros = ficheros;
		this.rutas_ficheros= new ArrayList<String>();
	}
	
	
	public Usuario(Usuario u) {
		
		 this.id_usuario = u.id_usuario;
	     this.ip_usuario= u.ip_usuario;
	     this.ficheros = new ArrayList<String>(u.ficheros);
	     this.rutas_ficheros= new ArrayList<String>(u.rutas_ficheros);
	     
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
	
	public ArrayList<String> getRutasFicheros() {
		return rutas_ficheros;
	}
	public void addFile(String filename,String ruta_filename) {
		this.ficheros.add(filename);
		this.rutas_ficheros.add(ruta_filename);
	}
}
