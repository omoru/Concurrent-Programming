package msg_src;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import users_src.Usuario;


public abstract class Mensaje implements Serializable {
	
	private String ip_origen;
	private String ip_destino;
	private String msg;
	
	public Mensaje(String origen,String dest, String msg) {

		this.ip_origen = origen;
		this.ip_destino=dest;
		this.msg = msg;
	}
	
	public String getIPOrigen() {
		return this.ip_origen;		
	}
	
	public String getIPDestino() {
		return this.ip_destino;
	}
	public String getMensaje() {
		return this.msg;
	}
	
	public String getIdUsuario(){
		System.out.println("Operacion no soportada para este tipo de mensaje");
		return null;
	}
	
	public ArrayList<String> getFicheros()   {
		System.out.println("Operacion no soportada para este tipo de mensaje");
		return null;
	}
	public ObjectInputStream getF_in()   {
		System.out.println("Operacion no soportada para este tipo de mensaje");
		return null;
	}

	public ObjectOutputStream getF_out()   {
		System.out.println("Operacion no soportada para este tipo de mensaje");
		return null;
	}
	public ArrayList<Usuario> getInfo_usuarios()   {
		System.out.println("Operacion no soportada para este tipo de mensaje");
		return null;
	}
	public String getFilename() {
		System.out.println("Operacion no soportada para este tipo de mensaje");
		return null;
	}
	
	public int getPuertoPropio() {
		System.out.println("Operacion no soportada para este tipo de mensaje");
		return (Integer) null;
	}
	
	public String getMyIP() {
		System.out.println("Operacion no soportada para este tipo de mensaje");
		return null;
	}

}
