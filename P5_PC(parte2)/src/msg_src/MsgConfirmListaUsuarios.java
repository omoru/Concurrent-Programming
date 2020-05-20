package msg_src;

import java.util.ArrayList;

import users_src.Usuario;

public class MsgConfirmListaUsuarios extends Mensaje {
	
	private ArrayList<Usuario> info_usuarios;
	
	public MsgConfirmListaUsuarios(String origen, String dest,ArrayList<Usuario> info_usuarios) {
		super(origen, dest, "MENSAJE_CONFIRMACION_LISTA_USARIOS");
		this.info_usuarios=info_usuarios;
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Usuario> getInfo_usuarios() {
		return (this.info_usuarios);
	}

}
