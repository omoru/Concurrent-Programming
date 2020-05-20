package msg_src;

import java.util.ArrayList;

import users_src.Usuario;

public class MsgConfirmacionAddFile extends Mensaje {
		
	public MsgConfirmacionAddFile(String ip_origen, String ip_dest) {
		super(ip_origen, ip_dest, "MENSAJE_CONFIRMACION_AÑADIR_ARCHIVO");
	}
	



}
