package msg_src;

import java.util.ArrayList;

public class MsgErrorConexion extends Mensaje {
	
	private ArrayList<String> ficheros;
	public MsgErrorConexion(String ip_origen,String ip_destino,ArrayList<String> ficheros) {
		super(ip_origen,ip_destino,"MENSAJE_ERROR_CONEXION");
		this.ficheros = ficheros;
		
	}
	
	public ArrayList<String> getFicheros() {
		return ficheros;
		
	}

}
