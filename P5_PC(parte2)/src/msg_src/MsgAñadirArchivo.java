package msg_src;

public class MsgA�adirArchivo extends Mensaje {

	private String filename;
	private String ruta_filename;
	private String id_usuario;
	public MsgA�adirArchivo(String ip_origen, String ip_dest, String filename, String ruta_filename,String id_usuario) {
		super(ip_origen, ip_dest, "MENSAJE_A�ADIR_ARCHIVO");
		this.filename=filename;
		this.id_usuario=id_usuario;
		this.ruta_filename=ruta_filename;
		// TODO Auto-generated constructor stub
	}

	public String getFilename() {
		return this.filename;
	}
	public String getIDusuario() {
		return this.id_usuario;
	}

	public String getRuta_filename() {
		return ruta_filename;
	}
}
