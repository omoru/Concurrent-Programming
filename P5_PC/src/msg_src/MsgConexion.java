package msg_src;

import java.util.ArrayList;

public class MsgConexion extends Mensaje {

	private String id_usuario;
	private ArrayList<String> ficheros;
	
	public MsgConexion(String ip_origen, String ip_dest,String id_usuario,ArrayList<String> ficheros) {
		super(ip_origen,ip_dest, "MENSAJE_CONEXION");
		this.id_usuario= id_usuario;
		this.ficheros=ficheros;
	}
	
	
	public String getIdUsuario() {
		return id_usuario;
	}
	
	public ArrayList<String> getFicheros() {
		return ficheros;
		
	}
	
	
}
