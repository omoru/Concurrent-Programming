package msg_src;

import java.util.ArrayList;

import users_src.Usuario;

public class MsgConfirmacionAddFile extends Mensaje {
	
	private String filename;
	
	
	public MsgConfirmacionAddFile(String ip_origen, String ip_dest, String filename) {
		super(ip_origen, ip_dest, "MENSAJE_CONFIRMACION_A�ADIR_ARCHIVO");
		this.filename=filename;
	}
	
	public String getFilename() {
		return this.filename;
	}


}
