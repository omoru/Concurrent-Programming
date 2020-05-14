package msg_src;

public class MsgPedirFichero extends Mensaje {
	
	private String filename;
	private String id_usuario;
	public MsgPedirFichero(String id_usuario,String origen, String dest, String filename) {
		super(origen, dest, "MENSAJE_PEDIR_FICHERO");
		this.filename=filename;
		this.id_usuario=id_usuario;
	}
	public String getFilename() {
		return filename;
	}
	
	public String getIdUsuario(){
		return this.id_usuario;
	}
	

}
