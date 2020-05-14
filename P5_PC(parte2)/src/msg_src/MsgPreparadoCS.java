package msg_src;

public class MsgPreparadoCS extends Mensaje {
	
	private int puerto_propio;
	private String id_user_destination;
	private String my_ip;

	
	public MsgPreparadoCS(String id_user_destination, String my_ip, int puerto_propio) {
		super(null, null, "MENSAJE_PREPARADO_CLIENTESERVIDOR");
		this.id_user_destination=id_user_destination;
		this.my_ip = my_ip;
		this.puerto_propio= puerto_propio;
		
		// TODO Auto-generated constructor stub
	}
	
	public String getIdUsuario(){
		return this.id_user_destination;
	}
	
	public int getPuertoPropio() {
		return this.puerto_propio;
	}
	
	public String getMyIP() {
		return this.my_ip;
	}

	
}
