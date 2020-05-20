package msg_src;

public class MsgEmitirFichero extends Mensaje {
	
	private String ruta_filename;
	private String id_user;
	private String filename;
	
	public MsgEmitirFichero(String ruta_filename,String filename, String id_user) {
		super(null, null, "MENSAJE_EMITIR_FICHERO");
		this.ruta_filename=ruta_filename;
		this.id_user=id_user;
		this.filename=filename;
		// TODO Auto-generated constructor stub
	}
	public String getIdUsuario(){
		return this.id_user;
	}
	
	public String getRutaFilename() {
		return this.ruta_filename;
	}
	
	public String getFilename() {
		return this.filename;
	}
	
}
