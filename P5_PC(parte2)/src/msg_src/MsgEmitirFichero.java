package msg_src;

public class MsgEmitirFichero extends Mensaje {
	
	private String filename;
	private String id_user;
	
	public MsgEmitirFichero(String filename, String id_user) {
		super(null, null, "MENSAJE_EMITIR_FICHERO");
		this.filename=filename;
		this.id_user=id_user;
		// TODO Auto-generated constructor stub
	}
	public String getIdUsuario(){
		return this.id_user;
	}
	
	public String getFilename() {
		return this.filename;
	}

}
