package msg_src;

import java.util.ArrayList;

public class MsgErrorConexion extends Mensaje {
	
	
	public MsgErrorConexion(String ip_origen,String ip_destino) {
		super(ip_origen,ip_destino,"MENSAJE_ERROR_CONEXION");		
	}
	


}
