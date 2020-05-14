package msg_src;

public class MsgConfirmacionCerrarConexion extends Mensaje{
	
	private String id_usuario;
	public MsgConfirmacionCerrarConexion(String origen, String dest,String id_usuario) {
		super(origen, dest, "MENSAJE_CONFIRMACION_CERRAR_CONEXION");
		this.id_usuario=id_usuario;
		// TODO Auto-generated constructor stub
	}
	public String getIdUsuario() {
		return id_usuario;
	}

}
