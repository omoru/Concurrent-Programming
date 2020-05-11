package msg_src;

public class MsgPreparadoSC extends Mensaje{
	
	private String ip_conexion;
	private int puerto_conexion;
	public MsgPreparadoSC(String ip_conexion,int puerto_conexion) {
		super(null, null, "MENSAJE_PREPARADO_SERVIDORCLIENTE");
		this.ip_conexion=ip_conexion;
		this.puerto_conexion=puerto_conexion;
	}
	public int getPuertoPropio() {
		return this.puerto_conexion;
	}
	
	public String getMyIP() {
		return this.ip_conexion;
	}

}
