package server_src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import msg_src.Mensaje;
import msg_src.MsgConfirmListaUsuarios;
import msg_src.MsgConfirmacionCerrarConexion;
import msg_src.MsgConfirmacionConexion;
import msg_src.MsgEmitirFichero;
import msg_src.MsgPreparadoSC;
import users_src.FlujosUsuario;
import users_src.Usuario;

public class OyenteClient extends Thread {
	
	private Socket socket;
	private ObjectOutputStream f_out;//flujo salida hacia cliente
	private ObjectInputStream f_in; // flujo entrada a servidor
	
	public OyenteClient(Socket s) {
		
		try {
			this.socket = s;
			this.f_out= new ObjectOutputStream(socket.getOutputStream());
			this.f_in = new  ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("PROBLEMA EN LA CREACION DE OYENTE CLIENTE");
			e.printStackTrace();
		}
		
	}
	
	
	public void run() {
		
		Mensaje m = null;
		while(true) {
			try {
				
				m = (Mensaje) f_in.readObject();
				
				switch(m.getMensaje()) {
					case "MENSAJE_CONEXION":{
						//info usuario
						Usuario u = new Usuario(m.getIdUsuario(),m.getIPOrigen(),m.getFicheros());
						//id y canales usuario
						FlujosUsuario fu= new FlujosUsuario(m.getIdUsuario(),f_out,f_in);
						Server.añadirUsuario(u);
						Server.añadirFlujosUsuario(fu);
						//enviamos mensaje confirmacion
						f_out.writeObject(new MsgConfirmacionConexion(m.getIPDestino(),m.getIPOrigen()));
						break;
					}
					case "MENSAJE_LISTA_USARIOS":{
						System.out.println("Cliente "+ m.getIdUsuario()+" ha solicitado info usuarios");
						f_out.writeObject(new MsgConfirmListaUsuarios(m.getIPDestino(), m.getIPOrigen(),Server.getUsersInfo()));
						break;
					}
					case "MENSAJE_CERRAR_CONEXION":{
						System.out.println("Servidor cerrando conexion con " + m.getIdUsuario());
						f_out.writeObject(new MsgConfirmacionCerrarConexion(m.getIPDestino(),m.getIPOrigen(),m.getIdUsuario()));
						Server.deleteInfoUsuario(m.getIdUsuario());
						Server.deleteFlujosUsuario(m.getIdUsuario());
						f_out.close();
						return;
					}
					case "MENSAJE_PEDIR_FICHERO":{
						String id_user = Server.getOwnerFile(m.getFilename());
						ObjectOutputStream f_out2 = Server.getOutputStreamOC(id_user);
						f_out2.writeObject(new MsgEmitirFichero(m.getFilename(),m.getIdUsuario()));
						break;
					}
					case "MENSAJE_PREPARADO_CLIENTESERVIDOR":{
						ObjectOutputStream f_out1 = Server.getOutputStreamOC(m.getIdUsuario());
						f_out1.writeObject(new MsgPreparadoSC(m.getMyIP(),m.getPuertoPropio()));
						break;
					}
				}
								
			} catch (Exception e) {
				System.out.println("Algo falla en un OyenteClient,cerrando su conexion");
				Server.deleteInfoUsuario(m.getIdUsuario());
				Server.deleteFlujosUsuario(m.getIdUsuario());
				try {
					
					f_out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return;
			}
		}
		
	}

}
