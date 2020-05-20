package msg_src;

import java.util.ArrayList;

public class MsgConexion extends Mensaje {

	private String id_usuario;

	public MsgConexion(String ip_origen, String ip_dest,String id_usuario) {
		super(ip_origen,ip_dest, "MENSAJE_CONEXION");
		this.id_usuario= id_usuario;
	}
	
	
	public String getIdUsuario() {
		return id_usuario;
	}
	

	
	
}
