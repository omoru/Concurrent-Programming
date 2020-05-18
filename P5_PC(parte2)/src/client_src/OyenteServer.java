package client_src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import client_src.Client;
import msg_src.Mensaje;
import msg_src.MsgConexion;
import msg_src.MsgConfirmListaUsuarios;
import msg_src.MsgConfirmacionCerrarConexion;
import msg_src.MsgEmitirFichero;
import msg_src.MsgErrorConexion;
import msg_src.MsgPreparadoCS;
import msg_src.MsgPreparadoSC;
import users_src.FlujosUsuario;
import users_src.Usuario;

public class OyenteServer extends Thread{
	
	private Socket socket;
	private ObjectInputStream f_in; // flujo entrada a cliente
	private Client client;
	public OyenteServer(Socket s,Client client) {
		try {
			this.client=client;
			this.socket = s;
			this.f_in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("PROBLEMA EN LA CREACION DE OYENTESERVER");
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		
		while(true) {
			Mensaje m;		
				try {
					 m = (Mensaje)f_in.readObject();
					switch(m.getMensaje()) {
						case "MENSAJE_CONFIRMACION_CONEXION":{
							System.out.println("Conexion realizada con server");
							client.getSemaphore().release();
							break;
						}
						case "MENSAJE_ERROR_CONEXION":{
							reintentarConexion((MsgErrorConexion) m);
							break;
						}
						case "MENSAJE_CONFIRMACION_LISTA_USARIOS":{
							System.out.println("Se ha recibido la información de los usuarios");
							printInfoUsuarios(((MsgConfirmListaUsuarios) m).getInfo_usuarios());
							break;
						}
						case "MENSAJE_EMITIR_FICHERO":{
							enviarArchivo((MsgEmitirFichero) m);
							break;
						}
						case "MENSAJE_PREPARADO_SERVIDORCLIENTE":{
							recibirArchivo((MsgPreparadoSC) m);
							break;
						}
						case "MENSAJE_CONFIRMACION_CERRAR_CONEXION":{
							System.out.println("Adios "+ ((MsgConfirmacionCerrarConexion) m).getIdUsuario());
							socket.close();
							f_in.close();
							return;
							
						}
						default:
	                        System.out.println("Mensaje no identificado");break;
					}
				}
				catch (Exception e) {
					System.out.println("Se ha cortado la conexion del server");
					try {
						socket.close();
						f_in.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
					return;
				}
				
		
		}
		
	}
	
	
	private void reintentarConexion(MsgErrorConexion msg) {
		client.changeUserName();
		client.sendMensaje(new MsgConexion(client.getIP(),msg.getIPOrigen(), client.get_idUsuario(),msg.getFicheros()));
	}
	
	private void enviarArchivo(MsgEmitirFichero msg) throws IOException {
		ServerSocket s= new ServerSocket(0); // Si metemos 0 como puerto, el socket se encarga de buscar un puerto abierto en el que establece su escucha
		s.setReuseAddress(true);
		Mensaje mm = new MsgPreparadoCS(msg.getIdUsuario(),client.getIP(),s.getLocalPort(),
				msg.getFilename());
		client.sendMensaje(mm);
		new Emisor(s.accept(),msg.getFilename(),msg.getIdUsuario()).start();//peerEmisor
	}
	
	private void recibirArchivo(MsgPreparadoSC msg) {
		String ip_dest= msg.getMyIP();
		int  puerto_dest=msg.getPuertoPropio();
		new Receptor(ip_dest,puerto_dest,client.get_idUsuario(),msg.getFilename()).start();//peerReceptor
	}
	
	
	private void printInfoUsuarios(ArrayList<Usuario> usuarios) {
		System.out.println("TABLA INFORMACION USUARIOS");
		for(Usuario u: usuarios) {
			System.out.println("ID: " + u.getIdUsuario());
			System.out.println("Ficheros: " + u.getFicheros() +'\n');
		}
		
	}


}
